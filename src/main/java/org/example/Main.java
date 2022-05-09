package org.example;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
public class Main {
    public int id;
    public static String name;
    public static String  age;
    public static String health;
    public  static String location;
    public static String all;
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "123");
        get("/", (request, response) -> {

            return new ModelAndView(new HashMap(), "start.hbs");

        }, new HandlebarsTemplateEngine());

        get("/form", (request, response) -> {
            name = request.queryParams("name");
            age = request.queryParams("age");
            health =request.queryParams("health");
            location = request.queryParams("location");
            Endangered animal = new Endangered (name,age,health,location);
            animal.save();

            Map<String, Object> model = new HashMap<String, Object>();
//            List myEndangeredArrayList = Endangered.all();
//            model.put("myEndangeredArrayList", myEndangeredArrayList);
            model.put("animal",animal);

            return new ModelAndView(new HashMap(), "form.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
