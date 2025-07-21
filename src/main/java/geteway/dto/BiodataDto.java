package geteway.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class BiodataDto {

    private Long id;
    private String name;
    private String address;
    private String hobby;
    private String collage;
    private Integer age;
}
