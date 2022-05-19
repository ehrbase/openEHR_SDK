package org.ehrbase.validation.terminology;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/*
 * Copyright (c) 2022 Vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.nedap.archie.rm.datavalues.DvCodedText;

public class ExternalTerminologyValidationServerTest {

    @Ignore("This test runs against ontoserver sample inteance. It is deactivated until we have a test FHIR terminology server and the architecture allows to run Spring integration tests.")
    @Test
    public void shouldRetrieveValueSet() {
        FhirTerminologyValidation tsserver = null;
        try {
          tsserver = new FhirTerminologyValidation("https://r4.ontoserver.csiro.au/fhir/", true);
        } catch (Exception e) {
            Assert.fail();
        }
        
        TerminologyParam tp = TerminologyParam.ofServiceApi("//hl7.org/fhir/R4");
          tp.setOperation("expand");
          tp.setParameter("http://hl7.org/fhir/ValueSet/surface");

        List<DvCodedText> result = tsserver.expand(tp);
        result.forEach((e) -> System.out.println(e.getValue()));
        // 1: Buccal
        assertThat(result.get(0).getDefiningCode().getCodeString()).isEqualTo("B");
        assertThat(result.get(0).getValue()).isEqualTo("Buccal");
        // 2: Distal
        assertThat(result.get(1).getDefiningCode().getCodeString()).isEqualTo("D");
        assertThat(result.get(1).getValue()).isEqualTo("Distal");
        // 3: Distoclusal
        assertThat(result.get(2).getDefiningCode().getCodeString()).isEqualTo("DO");
        assertThat(result.get(2).getValue()).isEqualTo("Distoclusal");
        // 4: Distoincisal
        assertThat(result.get(3).getDefiningCode().getCodeString()).isEqualTo("DI");
        assertThat(result.get(3).getValue()).isEqualTo("Distoincisal");

        assertThat(result.size()).isEqualTo(11);
    }

    @Ignore("Requires SSL configuration")
    @Test
    public void expandValueSetUsingSsl() throws GeneralSecurityException, IOException {
        File keystoreFile = Paths.get("<path to keystore>").toFile();
        File truststoreFile = Paths.get("<path to truststore>").toFile();
      
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keystoreFile, "<store pdw>".toCharArray(), "<key pwd>".toCharArray())
                .loadTrustMaterial(truststoreFile, "<store pwd>".toCharArray(), TrustAllStrategy.INSTANCE)
                .build();

        HttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        FhirTerminologyValidation tsserver = null;
        
        try {
            tsserver = new FhirTerminologyValidation("https://r4.ontoserver.csiro.au/fhir/", true, httpClient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TerminologyParam tp = TerminologyParam.ofServiceApi("//hl7.org/fhir/R4");
          tp.setOperation("expand");
          tp.setParameter("https://www.netzwerk-universitaetsmedizin.de/fhir/ValueSet/frailty-score");
        
        List<DvCodedText> result = tsserver.expand(tp);
        result.forEach((e) -> System.out.println(e.getValue()));
        // 1: Very Severely Frail
        assertThat(result.get(0).getDefiningCode().getCodeString()).isEqualTo("8");
        assertThat(result.get(0).getValue()).isEqualTo("Very Severely Frail");
        // 2: Severely Frail
        assertThat(result.get(1).getDefiningCode().getCodeString()).isEqualTo("7");
        assertThat(result.get(1).getValue()).isEqualTo("Severely Frail");
        // 3: Terminally Ill
        assertThat(result.get(2).getDefiningCode().getCodeString()).isEqualTo("9");
        assertThat(result.get(2).getValue()).isEqualTo("Terminally Ill");
        // 4: Vulnerable
        assertThat(result.get(3).getDefiningCode().getCodeString()).isEqualTo("4");
        assertThat(result.get(3).getValue()).isEqualTo("Vulnerable");

        assertThat(result.size()).isEqualTo(9);
    }
}
