package geteway.RaceConditionExample;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private String id;
    private String name;
    private Integer qty;
}
