package geteway.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PaggingResponse {
    private Boolean last;
    private Boolean first;
    private Integer totalPages;
    private Long totalElements;
    private Integer size;
    private Integer numberOfElements;
    private List<ProfileResponse> data;
}
