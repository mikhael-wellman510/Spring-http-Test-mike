package geteway.service;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import geteway.dto.RumahSakitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BigQueryServiceImpl implements BigQueryService {

    private final BigQuery bigQuery;


    @Override
    public List<RumahSakitResponse> findAllBigQuery() {

        try {
            String query = "SELECT agency_name ,street_address , city FROM `bigquery-public-data.cms_medicare.home_health_agencies_2013` LIMIT 30";

            QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration
                    .newBuilder(query).build();

            List<RumahSakitResponse> listRs = new ArrayList<>();

            TableResult res =bigQuery.query(queryJobConfiguration);

            for (FieldValueList val : res.iterateAll()){
                String agencyName =val.get("agency_name").getStringValue();
                String streetAddress = val.get("street_address").getStringValue();
                String city = val.get("city").getStringValue();

                listRs.add(RumahSakitResponse.builder()
                                .agencyName(agencyName)
                                .streetAddress(streetAddress)
                                .city(city)
                        .build());
            }

            return listRs;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
