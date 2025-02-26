package geteway.RaceConditionExample;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductResponse {
    private String id;
    private String name;
    private Integer qty;
}
