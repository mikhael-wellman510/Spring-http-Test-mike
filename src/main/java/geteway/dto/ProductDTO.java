package geteway.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductDTO {

    private String id;
    private String productName;
    private String storeName;
    private Double price;
    private LocalDateTime createdAt;
}
