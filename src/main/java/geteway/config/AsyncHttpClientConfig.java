package geteway.config;

import io.grpc.netty.shaded.io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.Duration;

@Configuration
public class AsyncHttpClientConfig {

    @Bean
    public AsyncHttpClient asyncHttpClient() throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
        // 1) Load truststore dari classpath (AMAN di JAR/prod)
        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        try (InputStream is = new ClassPathResource("gateway-truststore.p12").getInputStream()) {
            trustStore.load(is, "changeit".toCharArray());
        }

        // 2) Init TrustManagerFactory
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        // 3) Build NETTY SslContext (bukan javax.net.ssl.SSLContext)
        SslContext nettySsl = SslContextBuilder
                .forClient()
                .trustManager(tmf)
                .build();

        // 4) Build AsyncHttpClient dengan SslContext
        DefaultAsyncHttpClientConfig cfg = new DefaultAsyncHttpClientConfig.Builder()
                .setSslContext(nettySsl)
                .setReadTimeout(Duration.ofMillis(30_000))
                .setConnectTimeout(Duration.ofMillis(10_000))
                .build();

        return new DefaultAsyncHttpClient(cfg);
    }



}
