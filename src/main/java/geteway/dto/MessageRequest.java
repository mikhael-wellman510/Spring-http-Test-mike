package geteway.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class MessageRequest {
    private String name;
    private String alamat;
    private String bodyMessage;
}
