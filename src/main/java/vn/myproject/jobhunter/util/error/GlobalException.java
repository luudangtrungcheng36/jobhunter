package vn.myproject.jobhunter.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.myproject.jobhunter.domain.RestResponse;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<RestResponse<Object>> handleIdException(IdInvalidException idException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(status.value());
        res.setError(idException.getMessage());
        res.setMessage("Call API fail");
        res.setData(null);

        return ResponseEntity.status(status).body(res);
    }
}
