package org.example;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.example.Persistence.ConnectionPool;
import org.example.Thymeleaf.ThymeleafConfig;

public class Main {
    private static final String USER = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "databasename";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {

        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing

        app.get("/", ctx -> ctx.render("index.html"));
    }
}