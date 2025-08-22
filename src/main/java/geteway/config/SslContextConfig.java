package geteway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class SslContextConfig {

	@Bean
	public SSLContext sslContext() throws Exception {
		KeyStore trustStore = KeyStore.getInstance("PKCS12");
		try (InputStream is = new ClassPathResource("gateway-truststore.p12").getInputStream()) {
			trustStore.load(is, "changeit".toCharArray());
		}

		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(trustStore);

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, tmf.getTrustManagers(), null);

		return sslContext;
	}
}
