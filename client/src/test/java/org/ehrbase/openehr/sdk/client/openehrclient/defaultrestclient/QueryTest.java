package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.collections.functors.NOPClosure;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.dto.condition.LogicalOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.WhereCondition;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentVersionExpression;
import org.ehrbase.openehr.sdk.aql.dto.operand.DoublePrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;
import org.ehrbase.openehr.sdk.aql.dto.operand.LongPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.StringPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectClause;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectExpression;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.Query;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.Record2;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.response.dto.MetaData;
import org.ehrbase.openehr.sdk.response.dto.QueryResponseData;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.openehr.schemas.v1.impl.TEMPLATEIDImpl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

class QueryTest {

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

        ContainmentClassExpression compositionCon = new ContainmentClassExpression();
        compositionCon.setType("COMPOSITION");
        compositionCon.setIdentifier("c");

        List<WhereCondition> whereConditionList = new ArrayList<>();

        for (int j = 0; j < 20; j++) {

            String templateId = "Corona_Anamnese" + j;
            template.getTemplateId().setValue(templateId);

            try {

                ((DefaultRestTemplateEndpoint) openEhrClient.templateEndpoint()).upload(template);
            } catch (RuntimeException e) {

                // NOP

            }

            var ehr = openEhrClient.ehrEndpoint().createEhr();

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
            pathStatement.setPath(AqlObjectPath.parse("content[openEHR-EHR-SECTION.adhoc.v"+j+" and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v"+(j+1)+"]/data[at000"+(j+1)+"]/events[at0003]/data[at0001]/items[at000"+(j+3)+"]/value"));
            pathStatement.setRoot(compositionCon);
            pathCondition.setStatement(pathStatement);

            LogicalOperatorCondition subCon = new LogicalOperatorCondition();
            subCon.setSymbol(LogicalOperatorCondition.ConditionLogicalOperatorSymbol.AND);
            subCon.setValues(List.of(templateCondition,pathCondition));

            whereConditionList.add(subCon);

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
        LogicalOperatorCondition logicalOperatorCondition = new LogicalOperatorCondition();
        logicalOperatorCondition.setSymbol(LogicalOperatorCondition.ConditionLogicalOperatorSymbol.OR);
        logicalOperatorCondition.setValues(whereConditionList);
        aqlQuery.setWhere(logicalOperatorCondition);
        StopWatch watch = new StopWatch();

        watch.start();
        QueryResponseData queryResponseData =
                openEhrClient.aqlEndpoint().executeRaw(Query.buildNativeQuery(aqlQuery.render()));
        watch.stop();
        System.out.println("Time Elapsed: " + watch.getTime());

        System.out.println("AQL:"+queryResponseData.getQuery());
        System.out.println("size:"+queryResponseData.getMeta().getAdditionalProperty(MetaData.AdditionalProperty.resultSize));
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
