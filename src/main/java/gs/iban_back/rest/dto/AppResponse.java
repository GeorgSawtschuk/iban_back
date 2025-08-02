package gs.iban_back.rest.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class AppResponse<T> {
    boolean success;
    String message;
    T data;
}
