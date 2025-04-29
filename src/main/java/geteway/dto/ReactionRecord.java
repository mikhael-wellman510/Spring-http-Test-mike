package geteway.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ReactionRecord {
    private String reactions;
    private String productBrandNew;
    private String consumerGender;
}
