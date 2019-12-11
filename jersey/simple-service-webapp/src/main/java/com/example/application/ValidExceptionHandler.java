package com.example.application;

import com.example.param.RestResponse;
import org.glassfish.grizzly.http.util.HttpStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

/**
 * 说明：异常处理
 * @author carter
 * 创建时间： 2019年12月11日 1:58 下午
 **/
@Provider
public class ValidExceptionHandler implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {

        final ConstraintViolationException violationException = (ConstraintViolationException) exception;
        final String errorMsg = violationException.getConstraintViolations().stream()
                .map(item -> item.getPropertyPath().toString().concat("(").concat(item.getMessage()).concat(")"))
                .collect(Collectors.joining(";"));

        return Response.ok()
                .type(MediaType.APPLICATION_JSON)
                .entity(RestResponse.builder()
                        .code(HttpStatus.BAD_REQUEST_400.getStatusCode())
                        .message(errorMsg).build())
                .build();
    }
}
