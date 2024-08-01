package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.dto.condition.LogicalOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.MatchesCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.WhereCondition;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentVersionExpression;
import org.ehrbase.openehr.sdk.aql.dto.operand.DoublePrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;
import org.ehrbase.openehr.sdk.aql.dto.operand.MatchesOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.StringPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectClause;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectExpression;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.Query;
import org.ehrbase.openehr.sdk.response.dto.MetaData;
import org.ehrbase.openehr.sdk.response.dto.QueryResponseData;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class QueryTest {

    record TestConfig(int mappings, boolean withEhrId, boolean withMatchesTemplates) {}

    @Test
    void testQueryperformance() throws URISyntaxException, IOException, XmlException {

        DefaultRestClient openEhrClient = setupDefaultRestClient();

        Composition composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                        Composition.class);

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
                .getTemplate();

        Map<TestConfig, Long> resutlMap = new LinkedHashMap<>();

        Stream.of(false, true).forEach(withEhrId -> {
            Stream.of(false, true).forEach(withMatchesTemplates -> {
                IntStream.of(5, 10, 20, 40)
                        .forEach(i -> runtest(
                                template,
                                openEhrClient,
                                composition,
                                resutlMap,
                                new TestConfig(i, withEhrId, withMatchesTemplates)));
            });
        });

        System.out.println(resutlMap);
    }

    private static void runtest(
            OPERATIONALTEMPLATE template,
            DefaultRestClient openEhrClient,
            Composition composition,
            Map<TestConfig, Long> resutlMap,
            TestConfig testConfig) {
        ContainmentClassExpression compositionCon = new ContainmentClassExpression();
        compositionCon.setType("COMPOSITION");
        compositionCon.setIdentifier("c");

        List<WhereCondition> outherConditions = new ArrayList<>();
        List<WhereCondition> innerWhere = new ArrayList<>();
        UUID validEhrId = null;
        List<String> templateIds = new ArrayList<>();
        for (int j = 0; j < testConfig.mappings; j++) {

            String templateId = "Corona_Anamnese" + j;

            templateIds.add(templateId);
            template.getTemplateId().setValue(templateId);

            try {

                ((DefaultRestTemplateEndpoint) openEhrClient.templateEndpoint()).upload(template);
            } catch (RuntimeException e) {

                // NOP

            }

            UUID ehr = openEhrClient.ehrEndpoint().createEhr();
            if (j == 1) {
                validEhrId = ehr;
            }

            ComparisonOperatorCondition templateCondition = new ComparisonOperatorCondition();
            templateCondition.setValue(new StringPrimitive(templateId));
            templateCondition.setSymbol(ComparisonOperatorSymbol.EQ);
            IdentifiedPath statement = new IdentifiedPath();
            statement.setPath(AqlObjectPath.parse("archetype_details/template_id/value"));
            statement.setRoot(compositionCon);
            templateCondition.setStatement(statement);

            ComparisonOperatorCondition pathCondition = new ComparisonOperatorCondition();
            pathCondition.setValue(new DoublePrimitive(39.0));
            pathCondition.setSymbol(ComparisonOperatorSymbol.EQ);
            IdentifiedPath pathStatement = new IdentifiedPath();
            pathStatement.setPath(AqlObjectPath.parse("content[openEHR-EHR-SECTION.adhoc.v" + j
                    + " and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v" + (j + 1)
                    + "]/data[at000" + (j + 1) + "]/events[at0003]/data[at0001]/items[at000" + (j + 3) + "]/value"));
            pathStatement.setRoot(compositionCon);
            pathCondition.setStatement(pathStatement);

            LogicalOperatorCondition subCon = new LogicalOperatorCondition();
            subCon.setSymbol(LogicalOperatorCondition.ConditionLogicalOperatorSymbol.AND);
            subCon.setValues(List.of(templateCondition, pathCondition));

            innerWhere.add(subCon);

            for (int i = 0; i < 20; i++) {

                composition.setUid(null);
                composition.getArchetypeDetails().getTemplateId().setValue(templateId);
                openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);
            }
        }

        AqlQuery aqlQuery = new AqlQuery();

        ContainmentClassExpression ehrCon = new ContainmentClassExpression();
        ehrCon.setType("EHR");
        ehrCon.setIdentifier("e");
        aqlQuery.setFrom(ehrCon);

        aqlQuery.setSelect(new SelectClause());
        aqlQuery.getSelect().setStatement(new ArrayList<>());
        SelectExpression e = new SelectExpression();
        IdentifiedPath ehrId = new IdentifiedPath();
        ehrId.setRoot(ehrCon);
        ehrId.setPath(AqlObjectPath.parse("ehr_id/value"));
        e.setColumnExpression(ehrId);
        aqlQuery.getSelect().getStatement().add(e);

        ContainmentVersionExpression versionExpression = new ContainmentVersionExpression();

        versionExpression.setVersionPredicateType(ContainmentVersionExpression.VersionPredicateType.LATEST_VERSION);

        ehrCon.setContains(versionExpression);

        versionExpression.setContains(compositionCon);

        SelectExpression selectC = new SelectExpression();
        IdentifiedPath cEpression = new IdentifiedPath();
        cEpression.setRoot(compositionCon);
        selectC.setColumnExpression(cEpression);
        aqlQuery.getSelect().getStatement().add(selectC);
        aqlQuery.setLimit(20L);

        if (testConfig.withEhrId()) {

            ComparisonOperatorCondition ehrCondition = new ComparisonOperatorCondition();
            ehrCondition.setSymbol(ComparisonOperatorSymbol.EQ);
            ehrCondition.setValue(new StringPrimitive(validEhrId.toString()));
            ehrCondition.setStatement(ehrId);
            outherConditions.add(ehrCondition);
        }

        if (testConfig.withMatchesTemplates()) {
            MatchesCondition matchesCondition = new MatchesCondition();
            matchesCondition.setValues(templateIds.stream()
                    .map(StringPrimitive::new)
                    .map(MatchesOperand.class::cast)
                    .toList());

            IdentifiedPath statement = new IdentifiedPath();
            statement.setPath(AqlObjectPath.parse("archetype_details/template_id/value"));
            statement.setRoot(compositionCon);
            matchesCondition.setStatement(statement);

            outherConditions.add(matchesCondition);
        }

        LogicalOperatorCondition logicalOperatorCondition = new LogicalOperatorCondition();
        logicalOperatorCondition.setSymbol(LogicalOperatorCondition.ConditionLogicalOperatorSymbol.OR);
        logicalOperatorCondition.setValues(innerWhere);

        outherConditions.add(logicalOperatorCondition);

        if (outherConditions.size() == 1) {
            aqlQuery.setWhere(outherConditions.get(0));
        } else {

            LogicalOperatorCondition and = new LogicalOperatorCondition();
            and.setSymbol(LogicalOperatorCondition.ConditionLogicalOperatorSymbol.AND);
            and.setValues(outherConditions);
            aqlQuery.setWhere(and);
        }

        StopWatch watch = new StopWatch();

        watch.start();
        QueryResponseData queryResponseData =
                openEhrClient.aqlEndpoint().executeRaw(Query.buildNativeQuery(aqlQuery.render()));
        watch.stop();
        resutlMap.put(testConfig, watch.getTime());
        System.out.println("Time Elapsed: " + watch.getTime());

        System.out.println("AQL:" + queryResponseData.getQuery());
        System.out.println(
                "size:" + queryResponseData.getMeta().getAdditionalProperty(MetaData.AdditionalProperty.resultSize));
    }

    public static DefaultRestClient setupDefaultRestClient() throws URISyntaxException {
        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        DefaultRestClient client =
                new DefaultRestClient(new OpenEhrClientConfig(ehrBaseAPIEndpoint()), templateProvider);
        templateProvider.listTemplateIds().stream()
                .forEach(t -> client.templateEndpoint().ensureExistence(t));
        return client;
    }

    protected static URI ehrBaseAPIEndpoint() {
        return URI.create("http://%s:%d/ehrbase/".formatted("localhost", 8080));
    }
}
