package org.example;

import org.sql2o.Connection;

import java.util.List;

public class Endangered implements Database {

    private String name;
    private String age;
    private String health;
public Endangered(String name, String age, String health){
    this.name=name;
    this.age=age;
    this.health=health;
}

public String getName(){
    return name;
}
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO wildlife_test_database (name, age, health) VALUES (:name, :age : health)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .executeUpdate();
        }
    }
    public static List<Endangered> all() {
        String sql = "SELECT * FROM wildlife_test_database";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }

}
