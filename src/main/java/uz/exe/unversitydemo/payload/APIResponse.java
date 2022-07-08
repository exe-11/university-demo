package uz.exe.unversitydemo.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.springframework.http.HttpStatus.OK;

@Getter
@Setter
@JsonInclude(NON_NULL)
public final class APIResponse implements Response{

    private String message;

    private Date timestamp;

    private String error;

    private int statusCode;

    private Object body;


    private APIResponse() {
    }

    public static APIResponse success(Object body){
        APIResponse response = new APIResponse();
        response.message = OK.name();
        response.statusCode = OK.value();
        response.body = body;
        return response;
    }

    public static APIResponse error(String message, HttpStatus status){
        APIResponse response = new APIResponse();
        response.message = message;
        response.statusCode = status.value();
        response.error = status.name();
        response.timestamp = new Date();
        return response;
    }
}
