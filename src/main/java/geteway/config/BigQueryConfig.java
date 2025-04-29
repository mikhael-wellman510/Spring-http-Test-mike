package geteway.config;


import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@Configuration
public class BigQueryConfig {

    @Bean
    public BigQuery bigQuery() throws IOException {

        return BigQueryOptions.newBuilder().setCredentials(ServiceAccountCredentials.fromStream(
                        new FileInputStream("C:\\Users\\Mikhael Wellman\\Documents\\MICROSERVICE\\gateway\\src\\main\\resources\\tokyo-guild-445605-n2-a8e7dd2c805f.json")

                )).build()
                .getService();
    }
}
