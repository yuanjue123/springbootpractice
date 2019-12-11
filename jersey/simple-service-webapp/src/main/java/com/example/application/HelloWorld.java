package com.example.application;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * helloworld 接口
 * @author carter
 */
@Log4j2
@WebServlet(value = "/helloWorld",loadOnStartup = 1)
public class HelloWorld extends HttpServlet {

    private static final long serialVersionUID = 1768961638115010461L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    private void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("hello world coming into servlet ... ");

        final String appName = req.getServletContext().getInitParameter("appName");

        log.info("appName:{}", appName);

        @Cleanup final PrintWriter writer = resp.getWriter();
        writer.write("hello world , servlet 4");

    }

}
