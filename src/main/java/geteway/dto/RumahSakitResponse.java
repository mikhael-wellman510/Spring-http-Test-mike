package geteway.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class RumahSakitResponse {

    private String agencyName;
    private String streetAddress;
    private String city;

}
