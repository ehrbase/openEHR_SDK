package org.ehrbase.serialisation.matrix;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FromCompositionWalker;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
public class CompositionToMatrixWalker extends FromCompositionWalker<WalkerDto> {

    private List<String> resolveTo = List.of("COMPOSITION", "OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION");

    @Override
    protected WalkerDto extract(Context<WalkerDto> context, WebTemplateNode child, boolean isChoice, Integer i) {
        WalkerDto next = new WalkerDto(context.getObjectDeque().peek());



        if( child.getNodeId() != null&& findTypeName(child.getNodeId()) != null &&resolveTo.contains(findTypeName(child.getNodeId()))){
            Resolve nextResolve = new Resolve();
            nextResolve.setArchetypeId(child.getNodeId());
            nextResolve.setPathFromRoot( child.getAqlPathDto().removeStart(next.getCurrentResolve().getPathFromRoot()));
            if (i != null){
                nextResolve.setCount(i);
            }
            next.updateResolve(nextResolve);

        } else if (i != null) {

               next.setCurrentIndex(new Index(next.getCurrentIndex()));
               next.getCurrentIndex().incrementIndex();
               next.getCurrentIndex().setRepetition(i);
               next.getMatrix().get(next.getCurrentResolve()).put(next.getCurrentIndex(), new LinkedHashMap<>());

        }

        return next;
    }

    @Override
    protected void preHandle(Context<WalkerDto> context) {

        WebTemplateNode node = context.getNodeDeque().peek();

        if(node.getChildren().isEmpty()){

            WalkerDto walkerDto = context.getObjectDeque().peek();

            AqlPath relativ = node.getAqlPathDto().removeStart(walkerDto.getCurrentResolve().getPathFromRoot());

          walkerDto.getMatrix().get(walkerDto.getCurrentResolve()).get(walkerDto.getCurrentIndex()).put(relativ,context.getRmObjectDeque().peek());

        }

    }

    @Override
    protected void postHandle(Context<WalkerDto> context) {}

    static String findTypeName(String atCode) {
        String typeName = null;

        if (atCode.contains("openEHR-EHR-")) {

            typeName = StringUtils.substringBetween(atCode, "openEHR-EHR-", ".");
        } else if (atCode.startsWith("at")) {
            typeName = null;
        } else {
            typeName = atCode;
        }
        return typeName;
    }
}
