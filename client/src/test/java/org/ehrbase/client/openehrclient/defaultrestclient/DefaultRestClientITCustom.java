package org.ehrbase.client.openehrclient.defaultrestclient;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
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
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Category(Integration.class)
public class DefaultRestClientITCustom {

    public static final String DEFAULT_USER = "ehrbase-user";

    public static final String DEFAULT_PASSWORD = "SuperSecretPassword";

    @Test
    public void testBasicAuthenticationDefaultConfig() throws URISyntaxException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASSWORD));
        HttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        OpenEhrClient openEhrClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider, httpClient);
        UUID ehrID = openEhrClient.ehrEndpoint().createEhr();
        Assert.assertNotNull(ehrID);
    }

    @Test(expected = WrongStatusCodeException.class)
    public void testBasicAuthenticationWrongPasswordDefaultConfig() throws URISyntaxException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(DEFAULT_USER, "WrongPassword"));
        HttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        OpenEhrClient openEhrClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider, httpClient);
        openEhrClient.ehrEndpoint().createEhr();
    }

    @Test
    public void testBasicAuthenticationHttpHeader() throws URISyntaxException {
        String auth = DEFAULT_USER + ":" + DEFAULT_PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION, "Basic " + new String(encodedAuth));

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        DefaultRestClient defaultRestClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider);
        VersionUid versionUid = defaultRestClient.httpPost(defaultRestClient.getConfig().getBaseUri().resolve("ehr/"), null, headers);
        Assert.assertNotNull(versionUid);
    }

    @Test(expected = WrongStatusCodeException.class)
    public void testBasicAuthenticationWrongPasswordHttpHeader() throws URISyntaxException {
        String auth = DEFAULT_USER + ":WrongPassword";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION, "Basic " + new String(encodedAuth));

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        DefaultRestClient defaultRestClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider);
        defaultRestClient.httpPost(defaultRestClient.getConfig().getBaseUri().resolve("ehr/"), null, headers);
    }


    @Test
    @Ignore("Requires prior configuration of a HTTP proxy and a DNS server")
    public void testWithProxy() throws URISyntaxException {
        HttpHost proxy = new HttpHost("proxy.home", 3128);
        HttpClient httpClient = HttpClientBuilder.create()
                .setProxy(proxy)
                .build();

        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        OpenEhrClient openEhrClient = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://ehrbase.home:8080/ehrbase/rest/openehr/v1/")), templateProvider, httpClient);
        UUID ehrID = openEhrClient.ehrEndpoint().createEhr();
        Assert.assertNotNull(ehrID);
    }
}
