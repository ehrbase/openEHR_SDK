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
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.Link;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.InstructionDetails;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvBoolean;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvEHRURI;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.DvURI;
import com.nedap.archie.rm.datavalues.TermMapping;
import com.nedap.archie.rm.datavalues.encapsulated.DvEncapsulated;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.DvCount;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import com.nedap.archie.rm.generic.Attestation;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyRelated;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.LocatableRef;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.TerminologyId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import com.nedap.archie.rmutil.InvariantUtil;
import com.nedap.archie.terminology.OpenEHRTerminologyAccess;
import com.nedap.archie.terminology.TermCode;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.ehrbase.client.classgenerator.shareddefinition.State;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;
import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.model.ProportionType;
import org.ehrbase.webtemplate.model.WebTemplateComparisonSymbol;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.model.WebTemplateInterval;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.model.WebTemplateValidation;
import org.threeten.extra.PeriodDuration;

public class ExampleGeneratorConfig {

    private static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    static final Set<String> UNSUPPORTED = Stream.of(
            Archetyped.class,
            FeederAudit.class,
            Link.class,
            Participation.class
        ).map(ExampleGeneratorConfig::getRmType)
        .collect(Collectors.toSet());

    private static final Map<ChronoUnit, String> DURATION_CHRONO_UNITS;
    public static final String LANGUAGE = "language";

    static {
        Map<ChronoUnit, String> chronoUnits = new EnumMap<>(ChronoUnit.class);
        Stream.of(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.WEEKS, ChronoUnit.DAYS, ChronoUnit.HOURS, ChronoUnit.MINUTES, ChronoUnit.SECONDS)
                .forEach(u -> chronoUnits.put(u, u.toString().substring(0, u.toString().length() - 1).toLowerCase()));
        DURATION_CHRONO_UNITS = Collections.unmodifiableMap(chronoUnits);
    }

    boolean doHandle(RMObject rmObject, WebTemplateNode node, WebTemplateNode parent) {

        var handlerMethod = Handlers.getObjectHandlers().get(rmObject.getClass());

        if (handlerMethod == null) {
            return false;

        } else {
            try {
                switch (handlerMethod.getParameters().length) {
                    case 2:
                        handlerMethod.invoke(null, rmObject, node);
                        break;
                    case 3:
                        handlerMethod.invoke(null, rmObject, node, parent);
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected parameter count: " + handlerMethod);
                }
            } catch (IllegalAccessException | InvocationTargetException | RuntimeException  e) {
               throw new RuntimeException("At " + node.getAqlPath() + ": " + e.getMessage(), e);
            }
            return true;
        }
    }

    private static Optional<WebTemplateInputValue> firstInputValue(WebTemplateNode node, String suffix) {
        return selectInputValue(getInput(node, suffix), 0.);
    }

    private static Optional<WebTemplateInputValue> selectInputValue(Optional<WebTemplateInput> input, double t) {
        return input
                .map(WebTemplateInput::getList)
                .filter(l -> !l.isEmpty())
                .map(l -> {
                    int i = determineIndex(t, l.size());
                    return(l.get(i));
                });
    }

    private static double fromValidation(double defaultValue, Optional<WebTemplateValidation> input, double t) {
        return input
                .map(WebTemplateValidation::getRange)
                .map(r -> {
                    Number min = ((Number) r.getMin());
                    Number max = ((Number) r.getMax());
                    if (ObjectUtils.allNull(min, max)) {
                        return null;
                    }
                    double minVal;
                    if (min != null) {
                        minVal = min.doubleValue();
                        if (r.getMinOp() == WebTemplateComparisonSymbol.GT) {
                            minVal++;
                        }
                    } else {
                        minVal = max.doubleValue() - defaultValue;
                    }
                    double maxVal;
                    if (max != null) {
                        maxVal = max.doubleValue();
                        if (r.getMaxOp() == WebTemplateComparisonSymbol.LT) {
                            maxVal--;
                        }
                    } else {
                        maxVal = minVal + defaultValue;
                    }
                    return Math.max(minVal, Math.min(maxVal, Math.round(t * maxVal + (1 - t) * minVal)));
                })
                .orElse(defaultValue);
    }

    private static int determineIndex(double t, int size) {
        return Math.min(Math.max(0, (int) (t * size)), size - 1);
    }

    private static Optional<WebTemplateInput> getInput(WebTemplateNode node, String suffix) {
        return Optional.ofNullable(node)
                .map(WebTemplateNode::getInputs)
                .stream()
                .flatMap(List::stream)
                .filter(e -> Objects.equals(suffix, e.getSuffix()))
                .findFirst();
    }

    private static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.of(2022, 2, 3, 4, 5, 6);

    public static class Handlers {

        private static Set<String> HANDLED_RM_TYPES;

        private static Map<Class<? extends RMObject>, Method> HANDLERS_BY_RM_OBJECT;

        static Set<String> getHandledRmTypes() {
            if (HANDLERS_BY_RM_OBJECT == null) {
                readHandlers();
            }
            return HANDLED_RM_TYPES;
        }

        static Map<Class<? extends RMObject>, Method> getObjectHandlers() {
            if (HANDLERS_BY_RM_OBJECT == null) {
                readHandlers();
            }
            return HANDLERS_BY_RM_OBJECT;
        }

        private static void readHandlers() {

            HANDLERS_BY_RM_OBJECT = Arrays.stream(Handlers.class.getDeclaredMethods())
                    .filter(m -> m.getName().startsWith("handle"))
                    .filter(m -> m.getParameters().length == 2 || m.getParameters().length == 3)
                    .collect(Collectors.toMap(m -> (Class<? extends RMObject>) m.getParameters()[0].getType(), m -> m));

            HANDLED_RM_TYPES =  HANDLERS_BY_RM_OBJECT.keySet().stream()
                    .map(ExampleGeneratorConfig::getRmType)
                    .collect(Collectors.toSet());
        }

        private Handlers() {
            //NOOP
        }

        static void handleObjectRef(ObjectRef value, WebTemplateNode node) {
            // https://specifications.openehr.org/releases/BASE/latest/base_types.html#_object_ref_class
            value.setNamespace("unknown");
            value.setType("ANY");
            //XXX assumes ObjectRef<ObjectId> or ObjectRef<GenericId>; see https://github.com/ehrbase/ehrbase/issues/656
            value.setId(new GenericId(generateUuid(node).toString(), "scheme"));
        }

        static void handleHierObjectId(HierObjectId value, WebTemplateNode node) {
            value.setValue(generateUuid(node).toString());
        }

        static void handlePartyIdentified(PartyIdentified value, WebTemplateNode node) {
            value.setName("DOE, John");
        }

        static void handleInstructionDetails(InstructionDetails value, WebTemplateNode node) {
            value.setActivityId("activities[at0001]");
            value.setInstructionId(new LocatableRef(new HierObjectId(generateUuid(node).toString()), "unknown", "INSTRUCTION", ""));
        }

        static void handleIsmTransition(IsmTransition value, WebTemplateNode node) {

            // determine permitted current states
            final Set<State> states = getInput(node.findChildById("current_state").orElseThrow(), "code")
                    .map(WebTemplateInput::getList)
                    .stream()
                    .flatMap(List::stream)
                    .map(cs -> {
                        String stateName = cs.getValue();
                        State state = Arrays.stream(State.values())
                                .filter(s -> s.getCode().equals(stateName)).findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateName));
                        return state;
                    })
                    .collect(Collectors.toSet());

            if (states.isEmpty()) {
                states.addAll(Arrays.asList(State.values()));
            }

            // determine permitted transitions
            Set<Transition> transitions = getInput(node.findChildById("transition").orElseThrow(), "code")
                    .map(WebTemplateInput::getList)
                    .stream()
                    .flatMap(List::stream)
                    .map(cs -> {
                        String transitionName = cs.getValue();
                        Transition transition = Arrays.stream(Transition.values())
                                .filter(s -> s.getCode().equals(transitionName)).findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Unknown transition: " + transitionName));
                        return transition;
                    })
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            if (transitions.isEmpty()) {
                transitions = EnumSet.allOf(Transition.class);
                // filter transitions by permitted states
                transitions.removeIf(t -> !states.contains(t.getTargetState()));
            }

            //take first transition
            Transition transition = transitions.iterator().next();
            State currentState = transition.getTargetState();

            value.setCurrentState(currentState.toCodedText());
            value.setTransition(transition.toCodedText());
        }

        static void handleCodePhrase(CodePhrase value, WebTemplateNode node, WebTemplateNode parent) {

            Optional<WebTemplateInput> codeIn = getInput(node, "code");

            selectInputValue(codeIn, 0.)
                    .map(c -> Pair.of(codeIn.map(WebTemplateInput::getTerminology).orElse(null), c.getValue()))
                    .or(() -> {
                        Optional<OpenEHRTerminology> ehrTerminology = OpenEHRTerminology.lookup(parent, node.getId());

                        String codeString;
                        String terminology;
                        if (ehrTerminology.isPresent()) {
                            var terms = ehrTerminology.get().getAllTerms();

                            if (terms.isEmpty()) {
                                switch (ehrTerminology.get().openEHRGroup) {
                                    case "languages":
                                        terminology = "openehr";
                                        codeString = "de";
                                        break;
                                    default:
                                        terminology = "TODO";
                                        codeString = "TODO";
                                }
                            } else {
                                int i = determineIndex(0., terms.size());
                                var term = terms.get(i);

                                codeString = term.getCodeString();
                                terminology = term.getTerminologyId();
                            }

                        } else {
                            terminology = codeIn.map(WebTemplateInput::getTerminology).orElse(null);
                            if ("snomed-ct".equalsIgnoreCase(terminology)) {
                                codeString = "254626006";
                            } else {
                                codeString = "42";
                            }
                        }

                        return Optional.of(Pair.of(terminology, codeString));
                    })
                    .ifPresent(t -> {
                        value.setCodeString(t.getRight());
                        value.setTerminologyId(new TerminologyId(Optional.ofNullable(t.getLeft()).orElse(null)));
                    });
        }

        static void handleDvBoolean(DvBoolean value, WebTemplateNode node) {
            Optional<WebTemplateInput> input = getInput(node, null);
            selectInputValue(input, 0.).ifPresentOrElse(
                c -> value.setValue(Boolean.valueOf(c.getValue())),
                () -> value.setValue(true));
        }

        static void handleDvCount(DvCount value, WebTemplateNode node) {
            value.setMagnitude(Math.round(fromValidation(42, getInput(node, null).map(WebTemplateInput::getValidation), .5)));
        }

        static void handleDvDate(DvDate value, WebTemplateNode node) {
            value.setValue(DEFAULT_DATE_TIME.toLocalDate());
        }

        static void handleDvDateTime(DvDateTime value, WebTemplateNode node) {
            value.setValue(DEFAULT_DATE_TIME);
        }

        static void handleDvDuration(DvDuration value, WebTemplateNode node) {
            List<Pair<ChronoUnit, WebTemplateInterval>> constraints = DURATION_CHRONO_UNITS.entrySet().stream()

                    .map(e -> getInput(node, e.getValue())
                            .map(WebTemplateInput::getValidation)
                            .map(WebTemplateValidation::getRange)
                            .filter(r -> ObjectUtils.anyNotNull(r.getMin(), r.getMax()))
                            .map(r -> Pair.of(e.getKey(), r))
                    ).flatMap(Optional::stream)
                    .collect(Collectors.toList());

            final PeriodDuration min;
            {
                PeriodDuration pd;
                var minConstraints = constraints.stream()
                        .filter(p -> Objects.nonNull(p.getValue().getMin()))
                        .collect(Collectors.toList());

                if (minConstraints.isEmpty()) {
                    pd = null;
                } else {
                    pd = PeriodDuration.ZERO;
                    for (Pair<ChronoUnit, WebTemplateInterval> p : minConstraints) {
                        int amount = ((Integer) p.getValue().getMin()).intValue();
                        if (p.getValue().getMinOp() == WebTemplateComparisonSymbol.GT) {
                            amount++;
                        }
                        pd = pd.plus(p.getKey().getDuration().multipliedBy(amount));
                    }
                }
                min = pd;
            }

            final PeriodDuration max;
            {
                PeriodDuration pd;
                var maxConstraints = constraints.stream()
                        .filter(p -> Objects.nonNull(p.getValue().getMax()))
                        .collect(Collectors.toList());

                if (maxConstraints.isEmpty()) {
                    pd = null;
                } else {
                    pd = PeriodDuration.ZERO;
                    for (Pair<ChronoUnit, WebTemplateInterval> p : maxConstraints) {
                        int amount = ((Integer) p.getValue().getMax()).intValue();
                        if (p.getValue().getMaxOp() == WebTemplateComparisonSymbol.LT) {
                            amount--;
                        }
                        pd = pd.plus(p.getKey().getDuration().multipliedBy(amount));
                    }
                }
                max = pd;
            }

            TemporalAmount durationValue = ObjectUtils.firstNonNull(min, max, Duration.ofHours(42));
            value.setValue(durationValue);
        }

        static void handleDvEHRURI(DvEHRURI value, WebTemplateNode node) {
            value.setValue(URI.create("ehr:/."));
        }

        static void handleDvIdentifier(DvIdentifier value, WebTemplateNode node) {
            value.setId("dev/null");
        }

        static void handleDvMultimedia(DvMultimedia value, WebTemplateNode node) {
            value.setUri(new DvURI("https://www.example.com/sample"));
            CodePhrase mediaType = new CodePhrase();
            mediaType.setCodeString("video/H261");
            mediaType.setTerminologyId(new TerminologyId("IANA_media-types"));
            value.setMediaType(mediaType);
            value.setSize(504903212);
        }

        static void handleDvOrdinal(DvOrdinal value, WebTemplateNode node) {

            Optional<WebTemplateInput> input = getInput(node, null);

            selectInputValue(input, 0.).ifPresentOrElse(code -> {
                value.setValue(code.getOrdinal().longValue());
                value.setSymbol(new DvCodedText(code.getLabel(), new CodePhrase(new TerminologyId(input.map(WebTemplateInput::getTerminology).orElse("external")), code.getValue())));
            }, () ->  {
                value.setValue(42L);
                value.setSymbol(new DvCodedText("kg", new CodePhrase(new TerminologyId("external"), "kg")));
            });
        }

        static void handleDvParsable(DvParsable value, WebTemplateNode node) {
            value.setValue("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Hello World</title></head><body>Hello World!</body></html>");
            value.setFormalism("text/html");
        }

        static void handleDvProportion(DvProportion value, WebTemplateNode node) {
            // check type
            ProportionType type = firstInputValue(node, "type")
                    .map(WebTemplateInputValue::getValue)
                    .map(s -> Arrays.stream(ProportionType.values())
                            .filter(v -> Integer.toString(v.getId()).equals(s)).findFirst().get())
                    .orElse(ProportionType.FRACTION);

            value.setType((long) type.getId());

            value.setNumerator(fromValidation(42, getInput(node, "numerator").map(WebTemplateInput::getValidation), .5));

            switch (type) {
                case RATIO:
                case FRACTION:
                case INTEGER_FRACTION:
                    value.setDenominator(fromValidation(3, getInput(node, "denominator").map(WebTemplateInput::getValidation), .5));
                    break;
                case UNITARY:
                    value.setDenominator(1.);
                    break;
                case PERCENT:
                    value.setDenominator(100.);
                    break;
            }

            switch (type) {
                case INTEGER_FRACTION:
                case UNITARY:
                    value.setNumerator(Math.floor(value.getNumerator()));
                    value.setDenominator(Math.floor(value.getDenominator()));
                    break;
                default:
                    //NOOP
            }
        }

        static void handleDvQuantity(DvQuantity value, WebTemplateNode node) {
            String unit = firstInputValue(node, "unit")
                    .map(v -> {
                        value.setMagnitude(fromValidation(42., Optional.of(v).map(WebTemplateInputValue::getValidation), .5));
                        return v;
                    })
                    .map(WebTemplateInputValue::getValue)
                    .orElseGet(() -> {
                        value.setMagnitude(22.);
                        return "mm";
            });
            value.setUnits(unit);
        }

        static void handleDvCodedText(DvCodedText value, WebTemplateNode node, WebTemplateNode parent) {
            Optional<WebTemplateInput> codeIn = getInput(node, "code");

            selectInputValue(codeIn, 0.)
            .map(c -> Triple.of(c.getLabel(), codeIn.map(WebTemplateInput::getTerminology).orElse(null), c.getValue()))
            .or(() -> {
                Optional<OpenEHRTerminology> ehrTerminology = OpenEHRTerminology.lookup(parent, node.getId());

                String text;
                String codeString;
                String terminology;
                if (ehrTerminology.isPresent()) {
                    var terms = ehrTerminology.get().getAllTerms();

                    int i = determineIndex(0., terms.size());
                    var term = terms.get(i);

                    terminology = term.getTerminologyId();
                    codeString = term.getCodeString();
                    text = term.getDescription();

                } else {
                    terminology = codeIn.map(WebTemplateInput::getTerminology).orElse(null);
                    if ("snomed-ct".equalsIgnoreCase(terminology)) {
                        text = "adenocarcinoma of lung";
                        codeString = "254626006";
                    } else {
                        text = "No example for termínology " + codeIn.map(WebTemplateInput::getTerminology).map(t -> "'" + t + "'").orElse(null) + " available";
                        codeString = "42";
                    }
                }

                return Optional.of(Triple.of(text, terminology, codeString));
            })
            .ifPresent(t -> {
                value.setValue(t.getLeft());
                value.setDefiningCode(
                    new CodePhrase(new TerminologyId(Optional.ofNullable(t.getMiddle()).orElse("external")), t.getRight()));
            });
        }

        //    DV_TEXT: 42
        static void handleDvText(DvText value, WebTemplateNode node, WebTemplateNode parent) {
            if (value instanceof DvCodedText) {
                handleDvCodedText((DvCodedText) value, node, parent);

            } else {
                Optional<OpenEHRTerminology> ehrTerminology = OpenEHRTerminology.lookup(parent, node.getId());
                if (ehrTerminology.isPresent()) {
                    var terms = ehrTerminology.get().getAllTerms();
                    int i = determineIndex(0., terms.size());
                    value.setValue(terms.get(i).getDescription());

                } else {
                    var in = getInput(node, null);
                    selectInputValue(in, 0.)
                            .map(WebTemplateInputValue::getLabel)
                            .or(() -> in.map(WebTemplateInput::getDefaultValue))
                            .filter(StringUtils::isNotEmpty)
                            .ifPresentOrElse(value::setValue, () -> value.setValue("Lorem ipsum"));
                }
            }
        }

        static void handleDvTime(DvTime value, WebTemplateNode node) {
            value.setValue(DEFAULT_DATE_TIME.toLocalTime());
        }

        static void handleDvURI(DvURI value, WebTemplateNode node) {
            value.setValue(URI.create("https://www.example.com/sample"));
        }
    }

    private static UUID generateUuid(WebTemplateNode node) {
        return UUID.nameUUIDFromBytes(node.getAqlPath().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * From all offered alternative rmTypes one has to be selected.
     * For now: cycle through the alternatives
     *
     * @param parent
     * @param child
     * @param i
     * @return
     */
    boolean isChooseChoice(WebTemplateNode parent, WebTemplateNode child, int i) {
        List<WebTemplateNode> choices = getChoices(parent, child);
        WebTemplateNode chosen = choices.get(i % choices.size());
        return chosen.getRmType().equals(child.getRmType());
    }

    /**
     * @param parent
     * @param child
     * @return number of alternative rmTypes to be chosen from
     */
    int findSizeForChoices(WebTemplateNode parent, WebTemplateNode child) {
        List<WebTemplateNode> choices = getChoices(parent, child);
        return choices.size() + 1;
    }

    List<WebTemplateNode> getChoices(WebTemplateNode parent, WebTemplateNode child) {
        List<WebTemplateNode> choices;
        if (parent.findChildById(child.getId()).orElseThrow().getRmType().equals(RmConstants.EVENT)) {
            choices = List.of(cloneWithRmType(child,
                    RmConstants.POINT_EVENT),
                    cloneWithRmType(child, RmConstants.INTERVAL_EVENT));
        } else {
            Map<String, List<WebTemplateNode>> choicesInChildren = parent.getChoicesInChildren();
            choices = choicesInChildren.get(child.getAqlPath(true));
        }
        return Optional.ofNullable(choices).orElse(List.of());
    }

    private static WebTemplateNode cloneWithRmType(WebTemplateNode child, String rmType) {
        WebTemplateNode clone = new WebTemplateNode(child);
        clone.setRmType(rmType);
        return clone;
    }

    boolean doSkip(WebTemplateNode parent, WebTemplateNode child) {

        RMTypeInfo parentTypeInfo = Optional.ofNullable(parent).map(WebTemplateNode::getRmType).map(ARCHIE_RM_INFO_LOOKUP::getTypeInfo).orElse(null);

    // Will be set by defaults
    if (parentTypeInfo != null
        && (Entry.class.isAssignableFrom(parentTypeInfo.getJavaClass())
            || Composition.class.isAssignableFrom(parentTypeInfo.getJavaClass()))
        && List.of(LANGUAGE, "composer").contains(child.getId())) {
      return true;
    }

        if (child.getRmType().equals(RmConstants.CODE_PHRASE)) {
            return child.getMin() == 0;
        }

        return UNSUPPORTED.contains(child.getRmType())
                || child.getId().equals("name")
                //If a Pathable is handled, all its attributes are handled there.
                || (parent != null && Handlers.HANDLED_RM_TYPES.contains(parent.getRmType()));

    }

    static String getRmType(Class<? extends RMObject> type) {
        return Optional.of(XmlType.class)
                .map(type::getAnnotation)
                .map(XmlType::name)
                .orElse(null);
    }

    /**
     * Derived from callers of InvariantUtil::belongsToTerminologyByOpenEHRId
     * Terminology openehr is mentioned in the inputs for the node.
     * The context is needed to determine which OpenEHRGroup is expected.
     */
    enum OpenEHRTerminology {
        ATTESTATION_REASON(Attestation.class, DvText.class, "reason", Attestation::getReason, Attestation::setReason, "attestation reason"),
        AUDIT_DETAILS_CHANGE_TYPE(AuditDetails.class, DvCodedText.class, "change_type", AuditDetails::getChangeType, AuditDetails::setChangeType, "audit change type"),
        COMPOSITION_CATEGORY(Composition.class, DvCodedText.class, "category", Composition::getCategory, Composition::setCategory, "composition category"),
        COMPOSITION_LANGUAGE(Composition.class, CodePhrase.class, LANGUAGE, Composition::getLanguage, Composition::setLanguage, "languages"),
        COMPOSITION_TERRITORY(Composition.class, CodePhrase.class, "territory", Composition::getTerritory, Composition::setTerritory, "countries"),
        ELEMENT_NULL_FLAVOURS(Element.class, DvCodedText.class, "null_flavour", Element::getNullFlavour, Element::setNullFlavour, "null flavours"),
        ENTRY_ENCODING(Entry.class, CodePhrase.class, "encoding", Entry::getEncoding, Entry::setEncoding, "character sets"),
        ENTRY_LANGUAGE(Entry.class, CodePhrase.class, LANGUAGE, Entry::getLanguage, Entry::setLanguage, "languages"),
        EVENT_CONTEXT_SETTING(EventContext.class, DvCodedText.class, "setting", EventContext::getSetting, EventContext::setSetting, "setting"),
        DV_ENCAPSULATED_LANGUAGE(DvEncapsulated.class, CodePhrase.class, LANGUAGE, DvEncapsulated::getLanguage, DvEncapsulated::setLanguage, "languages"),
        DV_ENCAPSULATED_CHARSET(DvEncapsulated.class, CodePhrase.class, "charset", DvEncapsulated::getCharset, DvEncapsulated::setCharset, "character sets"),
        DV_MULTIMEDIA_COMPRESSION_ALGORITHM(DvMultimedia.class, CodePhrase.class, "compression_algorithm", DvMultimedia::getCompressionAlgorithm, DvMultimedia::setCompressionAlgorithm, "compression algorithms"),
        DV_MULTIMEDIA_INTEGRITY_CHECK_ALGORITHM(DvMultimedia.class, CodePhrase.class, "integrity_check_algorithm", DvMultimedia::getIntegrityCheckAlgorithm, DvMultimedia::setIntegrityCheckAlgorithm, "integrity check algorithms"),
        DV_MULTIMEDIA_MEDIA_TYPE(DvMultimedia.class, CodePhrase.class, "media_type", DvMultimedia::getMediaType, DvMultimedia::setMediaType, "media types"),
        DV_ORDERED_NORMALSTATUS(DvOrdered .class, CodePhrase.class, "normal_status", DvOrdered ::getNormalStatus, DvOrdered ::setNormalStatus, "normal statuses"),
        DV_TEXT_LANGUAGE(DvText.class, CodePhrase.class, LANGUAGE, DvText::getLanguage, DvText::setLanguage, "languages"),
        DV_TEXT_ENCODING(DvText.class, CodePhrase.class, "encoding", DvText::getEncoding, DvText::setEncoding, "character sets"),
        ISM_TRANSITION_TRANSITION(IsmTransition.class, DvCodedText.class, "transition", IsmTransition:: getTransition, IsmTransition::setTransition, "instruction transitions"),
        INTERVAL_EVENT_MATH_FUNCTION(IntervalEvent.class, DvCodedText.class, "math_function", IntervalEvent::getMathFunction, IntervalEvent::setMathFunction, "event math function"),
        ISM_TRANSITION_CURRENT_STATE(IsmTransition.class, DvCodedText.class, "current_state", IsmTransition::getCurrentState, IsmTransition::setCurrentState, "instruction states"),
        PARTICIPATION_FUNCTION(Participation.class, DvText.class, "function", Participation::getFunction, Participation::setFunction, "participation function"),
        PARTICIPATION_MODE(Participation.class, DvCodedText.class, "mode", Participation::getMode, Participation::setMode, "participation mode"),
        PARTY_RELATED_RELATIONSHIP(PartyRelated.class, DvCodedText.class, "relationship", PartyRelated::getRelationship, PartyRelated::setRelationship, "subject relationship"),
        TERM_MAPPING_PURPOSE(TermMapping.class, DvCodedText.class, "purpose", TermMapping::getPurpose, TermMapping::setPurpose, "term mapping purpose"),
        //VERSION_LIFECYCLE_STATE(Version.class, DvCodedText.class, "lifecycleState", Version::getLifecycleState, Version::setLifecycleState, "version lifecycle state"),
        ;

        private final Class<? extends RMObject> parentType;
        private final Class<? extends RMObject> attributeType;
        private final String attribute;
        private final Function<? extends RMObject, ? extends RMObject> getter;
        private final BiConsumer<? extends RMObject, ? extends RMObject> setter;
        private final String openEHRGroup;

        <P extends RMObject, T extends RMObject> OpenEHRTerminology(Class<P> parentType, Class<T> attributeType, String attribute, Function<P, T> getter, BiConsumer<P, T> setter, String openEHRGroup) {
            this.parentType = parentType;
            this.attributeType = attributeType;
            this.attribute = attribute;
            this.getter = getter;
            this.setter = setter;
            this.openEHRGroup = openEHRGroup;
        }

        private static final Map<Pair<String, String>, OpenEHRTerminology> LOOKUP;

        static {
            ArchieRMInfoLookup rmInfoLookup = ArchieRMInfoLookup.getInstance();
            LOOKUP = Arrays.stream(values())
                    .flatMap(t -> {
                        RMTypeInfo typeInfo = rmInfoLookup.getTypeInfo(t.parentType);
                        return Stream.concat(Stream.of(typeInfo),
                                typeInfo.getAllDescendantClasses().stream())
                                        .map(ti -> Pair.of(ti, t));
                    })
                    .collect(Collectors.toMap(p -> Pair.of(p.getLeft().getRmName(), p.getRight().attribute), Pair::getRight));
        }

        static Optional<OpenEHRTerminology> lookup(WebTemplateNode parent, String attribute) {
            return Optional.ofNullable(LOOKUP.get(Pair.of(parent.getRmType(), attribute)));
        }

        public List<TermCode> getAllTerms() {
            OpenEHRTerminologyAccess terminology = OpenEHRTerminologyAccess.getInstance();
            if (this.attributeType == CodePhrase.class) {
                return terminology.getTermsByOpenEhrId(this.openEHRGroup, InvariantUtil.ENGLISH);
            } else {
                return terminology.getTermsByOpenEHRGroup(this.openEHRGroup, InvariantUtil.ENGLISH);
            }
        }
    }
}
