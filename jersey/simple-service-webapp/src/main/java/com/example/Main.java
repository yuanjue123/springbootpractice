package com.example;

import com.example.application.ExceptionHandler;
import com.example.application.ResponseHandler;
import com.example.application.ValidExceptionHandler;
import com.example.config.ObjectMapperResolver;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;
import java.io.IOException;
import java.net.URI;

/**
 * 说明：提供命令行的方式启动web服务，并进行快速测试,快速开发
 * @author carter
 * 创建时间： 2019年12月04日 17:29
 **/
@ApplicationPath("/*")
public class Main extends ResourceConfig {

    public Main() {
        super();

        super.setApplicationName("simple-service-webapp");
        packages(Main.class.getPackage().getName());
        register(ObjectMapperResolver.class);
        register(JacksonFeature.withExceptionMappers());
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK,true);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE,true);
        register(ResponseHandler.class);
        register(ExceptionHandler.class);
        register(ValidExceptionHandler.class);


    }

    /**
     * @return 配置本地的ip, 端口，应用的contextPath,可以写死，也可以用通过命令行传递，优先命令行
     */
    public static String getBaseUri() {
        return System.getProperty("jersey.base.uri", "http://localhost:8080/webapi/");
    }

    /**
     * @return 创建一个简单http的服务器，方便快速接口测试
     */
    public static HttpServer startServer() {

        ResourceConfig resourceConfig = new Main();

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(getBaseUri()), resourceConfig);

    }

    /**
     * 启动主方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        final HttpServer httpServer = startServer();

        System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl\nHit enter to stop it...", getBaseUri()));

        try {
            final int read = System.in.read();
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpServer.shutdownNow();

    }


}
