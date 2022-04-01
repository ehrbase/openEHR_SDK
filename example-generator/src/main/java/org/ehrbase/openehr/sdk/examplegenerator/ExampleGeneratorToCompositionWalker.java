/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.openehr.sdk.examplegenerator;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Instruction;
import com.nedap.archie.rm.datastructures.*;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import org.apache.commons.lang3.ObjectUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.ToCompositionWalker;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.ehrbase.openehr.sdk.examplegenerator.ContextUtil.*;

public class ExampleGeneratorToCompositionWalker extends ToCompositionWalker<ExampleGeneratorConfig> {

    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private static final Logger log = LoggerFactory.getLogger(ExampleGeneratorToCompositionWalker.class);

    private static void log(String message, Object ... parameters) {
        log.trace(message, parameters);
    }

    @Override
    protected ExampleGeneratorConfig extract(Context<ExampleGeneratorConfig> context, WebTemplateNode child, boolean isChoice, Integer i) {
        if (peekObject(context).doSkip(peekNode(context), child)) {
            log("skip extract {}", child.getRmType());
            return null;
        }
        log("extract {}", child.getRmType());
        if (isChoice) {
            ExampleGeneratorConfig config = peekObject(context);
            if (config.isChooseChoice(peekNode(context), child, Optional.ofNullable(i).orElse(0))) {
                //NOOP
            } else {
                return null;
            }
        }
        return peekObject(context);
    }

    int level = 0;

    @Override
    protected void preHandle(Context<ExampleGeneratorConfig> context) {

        var child = peekNode(context);
        var parent = peekNode(context, 1);

        if (peekObject(context).doSkip(parent, child)) {
            log("skip {} {} {}", child.getRmType(), child.getName(), child.getAqlPath());
            return;
        }
        log("{}> handle {} {} {}", ++level, child.getRmType(), child.getName(), child.getAqlPath());

        RMObject rmObject = peekRmObject(context);

        peekObject(context).doHandle(rmObject, child, parent);
    }

    @Override
    protected void postHandle(Context<ExampleGeneratorConfig> context) {
        RMObject rmObject = peekRmObject(context);

        if(rmObject instanceof Locatable) {
            ((Locatable) rmObject).setArchetypeNodeId(peekNode(context).getNodeId());
        }

        if(rmObject instanceof Element) {
            ((Element) rmObject).setNullFlavour(null);
            ((Element) rmObject).setNullReason(null);
        }

        if(rmObject instanceof Instruction) {
            ((Instruction) rmObject).setExpiryTime(null);
        }

        if(rmObject instanceof DvInterval) {
            // fix order of bounds
            var interval = (DvInterval) rmObject;

            if (ObjectUtils.allNotNull(interval.getLower(), interval.getUpper())
                && interval.getLower().compareTo(interval.getUpper()) > 0)
            {
                var t = interval.getLower();
                interval.setLower(interval.getUpper());
                interval.setUpper(t);
            }

            Optional.of(interval)
                    .ifPresent(iv -> {
                        iv.setUpperUnbounded(iv.getUpper() == null);
                        iv.setLowerUnbounded(iv.getLower() == null);
                    });
        }

        if(rmObject instanceof IntervalEvent) {
            // fix order of bounds
            var interval = (IntervalEvent) rmObject;
            if (!interval.mathFunctionValid()) {
                // remove nonsensical math function
                interval.setMathFunction(null);
            }
        }

        super.postHandle(context);
        log("<{} finished {}", level--, rmObject.getClass().getSimpleName());
    }

    @Override
    protected int calculateSize(Context<ExampleGeneratorConfig> context, WebTemplateNode childNode) {
        int size;
         if (ExampleGeneratorConfig.UNSUPPORTED.contains(childNode.getRmType())) {
             log("skipping (via size 0): {}", childNode.getRmType());
             return 0;
         } else if (peekObject(context).getChoices(peekNode(context), childNode).isEmpty()) {
            size = 1;
             log("simple size {}: {}", size, childNode.getRmType());
         } else {
            size = peekObject(context).findSizeForChoices(peekNode(context), childNode);
             log("calculated size {}: {}", size, childNode.getRmType());
        }

        if (size < childNode.getMin()) {
            log("fixed min size");
            return childNode.getMin();
        }
        if (childNode.getMax() >= 0 && size > childNode.getMax()) {
            log("fixed max size");
            return childNode.getMax();
        }

        return size;
    }
}
