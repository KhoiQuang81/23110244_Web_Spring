package vn.iotstar.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder // Tao Getter Setter
@NoArgsConstructor // Constructor khong tham so
@AllArgsConstructor // Constructor tat ca tham so
@FieldDefaults(level = AccessLevel.PRIVATE) // khong xac dinh thi la private
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    private int code = 1000;
    private String message;
    private T result;
}
