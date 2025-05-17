package geteway.Redis.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProfileDto {
    private Long id;
    private String name;
    private String nip;
    private LocalDateTime createdAt;
}
