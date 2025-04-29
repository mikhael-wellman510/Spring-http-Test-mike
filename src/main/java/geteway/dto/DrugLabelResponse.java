package geteway.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class DrugLabelResponse {

        private String city;
        private String country;
        private String reasonForRecall;
        private String productDescription;
        private String productQuantity;



}
