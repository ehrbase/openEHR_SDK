package org.ehrbase.client.openehrclient.defaultrestclient;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ehrbase.client.Integration;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

// TODO: Remove @Ignore and find a way to execute the following tests during the build process.
@Ignore
@Category(Integration.class)
public class DefaultRestClientTestIT {

    @Test
    public void testBasicAuthentication() throws URISyntaxException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("ehrbase-user", "SuperSecretPassword"));
        HttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        OpenEhrClient openEhrClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider, httpClient);
        UUID ehrID = openEhrClient.ehrEndpoint().createEhr();
        Assert.assertNotNull(ehrID);
    }

    @Test(expected = WrongStatusCodeException.class)
    public void testBasicAuthenticationWrongPassword() throws URISyntaxException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("ehrbase-user", "WrongPassword"));
        HttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        OpenEhrClient openEhrClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider, httpClient);
        openEhrClient.ehrEndpoint().createEhr();
    }

    @Test
    public void testWithProxy() throws URISyntaxException {
        HttpHost proxy = new HttpHost("192.168.10.101", 3128);
        HttpClient httpClient = HttpClientBuilder.create()
                .setProxy(proxy)
                .build();

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        OpenEhrClient openEhrClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://192.168.10.201:8080/ehrbase/rest/openehr/v1/")), templateProvider, httpClient);
        UUID ehrID = openEhrClient.ehrEndpoint().createEhr();
        Assert.assertNotNull(ehrID);
    }
}
