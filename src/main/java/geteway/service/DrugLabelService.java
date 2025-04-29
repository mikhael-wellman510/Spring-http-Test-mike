package geteway.service;

import geteway.dto.DrugLabelResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DrugLabelService {

    CompletableFuture<?>findAllDrugLabel();
}
