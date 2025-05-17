package geteway.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class OrderResponse {
    private String orderId;
    private String productName;
    private CustomerResponse customerResponse;
}
