package org.example;

import org.sql2o.Connection;

import java.util.List;

public class Endangered implements Database {

    private  String name;
    private String age;
    private String health;
    private  String location;
public Endangered(String name, String age, String health, String location){
    this.name=name;
    this.age=age;
    this.health=health;
    this.location=location;
}

public  String getName(){
    return name;
}
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animal (name, age, health, location ) VALUES (:name, :age, :health, :location)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("location", this.location)
                    .executeUpdate();
        }
    }
    public static List<Endangered> all() {
        String sql = "SELECT * FROM animal";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }

}
