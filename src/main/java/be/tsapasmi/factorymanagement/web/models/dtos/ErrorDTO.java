package be.tsapasmi.factorymanagement.web.models.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO implements Serializable {

    private final LocalDateTime time;

    private final int status;

    private final Map<String, Object> errors;

    public static Builder builder(LocalDateTime time, int status){
        return new Builder(time, status);
    }

    public static class Builder {
        private final LocalDateTime receivedAt;
        private final int status;
        private final Map<String, Object> errors = new HashMap<>();

        public Builder(LocalDateTime receivedAt, int status) {
            this.receivedAt = receivedAt;
            this.status = status;
        }

        public Builder putError(String key, Object data){
            errors.put(key, data);
            return this;
        }

        public ErrorDTO build(){
            return new ErrorDTO(this.receivedAt, this.status, this.errors);
        }
    }
}
