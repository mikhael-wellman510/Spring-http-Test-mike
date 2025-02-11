package geteway.util;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ApiResponse {


    private Boolean success;
    private String message;
    private Object data;


    public static ApiResponse success( String message ,Object data ){

        return ApiResponse.builder()
                .success(Boolean.TRUE)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse error(Integer status ,String message ){

        return ApiResponse.builder()
                .success(Boolean.FALSE)
                .message(message)
                .data(null)
                .build();
    }
}
