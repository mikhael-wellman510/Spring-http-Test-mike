package geteway.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ChatMessage {
    private String sender;
    private String content;
    private LocalDateTime timestamp;
}
