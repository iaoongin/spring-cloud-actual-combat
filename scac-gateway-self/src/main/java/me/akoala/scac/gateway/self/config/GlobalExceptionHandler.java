package me.akoala.scac.gateway.self.config;


import me.akoala.scac.common.api.ApiException;
import me.akoala.scac.common.api.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author XHX-PC
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handler(Exception e) {
        if (e instanceof ApiException) {
            return R.just(((ApiException) e).getMessageCode());
        }
        return R.fail();
    }

}
