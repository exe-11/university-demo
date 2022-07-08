package uz.exe.unversitydemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.exe.unversitydemo.payload.APIResponse;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    private ResponseEntity<APIResponse> handleDataNotFoundException(DataNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.error(exception.getMessage(),HttpStatus.NOT_FOUND));
    }
}
