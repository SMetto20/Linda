package org.example;

import org.sql2o.Connection;

import java.util.List;

public class Endangered implements Database {
    private int id;
    private  String name;
    private  String age;
    private String health;
public Endangered(String name, String age, String health ){
    this.name=name;
    this.age=age;
    this.health=health;


}

public  String getName(){
    return name;
}
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animal (name, age, health ) VALUES (:name, :age, :health)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .executeUpdate();
        }
    }
    public static List<Sightngs> all() {
        String sql = "SELECT * FROM animal";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightngs.class);
        }

    }
 public static final String healthy = "animal is of good health";
    public static final String okay= "animal is not sick";
    public static final String ill= "animal is sick";

//    public boolean endangered (){
//        if
//    }
    public int getId(){
        return id;
    }
}
