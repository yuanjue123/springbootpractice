package com.example.application;

import com.example.param.RestResponse;
import org.glassfish.grizzly.http.util.HttpStatus;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Objects;

/**
 * 说明：统一的响应过滤器
 * @author carter
 * 创建时间： 2019年12月11日 2:19 下午
 **/
@Provider
public class ResponseHandler implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {


        responseContext.setStatus(HttpStatus.OK_200.getStatusCode());


        final Object responseContextEntity = responseContext.getEntity();


        if (Objects.isNull(responseContextEntity)) {
            responseContext.setEntity(RestResponse.builder().code(HttpStatus.OK_200.getStatusCode()).message("success").build());
            return;
        }

        if (responseContextEntity instanceof RestResponse) {
            responseContext.setEntity(responseContextEntity);
            return;
        }

        responseContext.setEntity(RestResponse.builder()
                .code(HttpStatus.OK_200.getStatusCode())
                .message("success")
                .data(responseContextEntity)
                .build());


    }
}
