package geteway.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProfileRequest {
    private String id;
    private String name;
    private String address;
    private Integer age;
    private Integer weight;
    private String hobby;
}
