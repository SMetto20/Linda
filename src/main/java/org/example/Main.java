package org.example;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.security.Timestamp;
import java.util.*;

import static spark.Spark.*;

public class Main {
    public int id;
    public static String name;
    public static String  age;
    public static String health;
    public  static String location;
    public static String ranger;
    public static String endangered;
    public static Timestamp sighting;
    public static String all;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://ec2-34-236-94-53.compute-1.amazonaws.com:5432/dd556npq3t4siq";
        Sql2o sql2o = new Sql2o(connectionString, "lkzxmggrqevbct", "1d9585f5337694a7c6ad3b0a07c4c8c1a71aee360d40d27dbcd94bb0589a11a6");
        get("/", (request, response) -> {

            return new ModelAndView(new HashMap(), "start.hbs");

        }, new HandlebarsTemplateEngine());

        get("/form", (request, response) -> {
            name = request.queryParams("name");
            age = request.queryParams("age");
            health =request.queryParams("health");
            location = request.queryParams("location");
            ranger= request.queryParams("ranger");
            endangered=request.queryParams("endangered");

            Endangered animal = new Endangered (name,age,health);
            animal.save();

            Sightngs seen = new Sightngs(location, ranger);
            seen.save2();

            Date newDate = new Date();
            String date = newDate.toString();
//
            Map<String,Object> model = new HashMap<String, Object>();
//            List myEndangeredArrayList = Endangered.all();
//            model.put("myEndangeredArrayList", myEndangeredArrayList);
//            List<Sightngs> sightedAnimals = Sightngs.all();
            model.put("location",location);
            model.put("name",name);
            model.put("health",health);
            model.put("age",age);
            model.put("ranger",ranger);
            model.put("endangered", endangered);
            model.put("date",date);
//            model.put("sightedAnimals",sightedAnimals);

            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
        try{
            if(name == "");

        }catch (NullPointerException exception) {
            System.out.println("type required");

        }
        try{
            if(ranger == "");

        }catch (NullPointerException exception) {
            System.out.println("type required");
        }
    }

}
