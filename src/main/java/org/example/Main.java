package org.example;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class Main {
    public int id;
    public static String name;
    public static String  age;
    public static String health;
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildife_test_datatabase";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "123");


        get("/", (request, response) -> {
            name = request.queryParams("name");
            age= request.queryParams("age");
            health =request.queryParams("health");
            Endangered animal = new Endangered (name,age,health);
            animal.save();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animal",animal);
            return new ModelAndView(new HashMap(), "home.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
