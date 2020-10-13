package org.ehrbase.client.openehrclient.defaultrestclient;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ehrbase.client.Integration;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Category(Integration.class)
public class DefaultRestClientTestIT {

    @Test
    public void testBasicAuthentication() throws URISyntaxException {
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("test", "Azerty#1234"));
//
//        HttpClient httpClient = HttpClientBuilder.create()
//                .setDefaultCredentialsProvider(credentialsProvider)
//                .build();
//
//        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
//        OpenEhrClient openEhrClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider, httpClient);
//
//        UUID ehrID = openEhrClient.ehrEndpoint().createEhr();
//        Assert.assertNotNull(ehrID);
    }
}
