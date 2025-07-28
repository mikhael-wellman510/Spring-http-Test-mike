package geteway.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PageResponse<T> {

    private List<T>content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
