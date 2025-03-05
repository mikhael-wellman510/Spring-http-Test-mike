package geteway.service;

import geteway.dto.RumahSakitResponse;

import java.util.List;

public interface BigQueryService {

    List<RumahSakitResponse> findAllBigQuery();
}
