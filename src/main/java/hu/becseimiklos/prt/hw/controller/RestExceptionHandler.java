package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.vo.ResponseVO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseVO> handleException(Exception e) {
        ResponseVO<Void> ret = new ResponseVO<>();
        String stackTrace = ExceptionUtils.getStackTrace(e);
        return new ResponseEntity<>(ret.setFailure(stackTrace), HttpStatus.OK);
    }

}
