package com.gamedoora.backend.projectservices.config;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class RestTemplateConfig {
    @Bean("withoutSslVerify")
    RestTemplate restTemplateNoSslVerify() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        RestTemplate restTemplate = new RestTemplate(getRequestFactory());
        return restTemplate;
    }

    @Bean("default")
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    private ClientHttpRequestFactory getRequestFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(getSslFactory()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }

    @Bean("noVerifySsl")
    SSLConnectionSocketFactory getSslFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] x509Certificates, String s) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        return csf;
    }
}
