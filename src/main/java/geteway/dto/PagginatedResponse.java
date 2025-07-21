package geteway.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor

@Builder(toBuilder = true)
@ToString
public class PagginatedResponse {
    private List<?> data;
    private int page;
    private int size;
    private int totalPages;
    private long totalData;
    private boolean hasNext = false;
    private boolean hasPrevious = false;

    public PagginatedResponse(List<?> data, int page, int size, Long totalData) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalData = totalData;
        this.totalPages = (int) Math.ceil((double) totalData / size);
        this.hasNext = page < totalPages - 1;
        this.hasPrevious = page > 1;
    }
}
