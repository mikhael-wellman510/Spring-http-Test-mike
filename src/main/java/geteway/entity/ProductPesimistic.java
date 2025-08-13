package geteway.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_pesimistic")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductPesimistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer stock;
}
