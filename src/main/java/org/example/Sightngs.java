package org.example;

import org.sql2o.Connection;

import static java.time.LocalTime.now;

public class Sightngs {
    private String location;
    private String ranger;


    public Sightngs(String location, String ranger){
        this.location=location;
        this.ranger=ranger;
    }
    public void save2() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings ( location , ranger, sighting) VALUES ( :location,:ranger, :now())";
            con.createQuery(sql)
                    .addParameter("ranger", this.ranger)
                    .addParameter("location", this.location)
                    .addParameter("sighting", now())
                    .executeUpdate();
        }
    }
}
