package org.example;

import org.sql2o.Connection;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static java.time.LocalTime.now;

public class Sightngs {
    private int id;
    private String location;
    private String ranger;

    public Sightngs(String location, String ranger) {
        this.location = location;
        this.ranger = ranger;


    }

    public void save2() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings ( location , ranger, sightings) VALUES ( :location,:ranger, now())";
            con.createQuery(sql)
                    .addParameter("ranger", this.ranger)
                    .addParameter("location", this.location)
                    .executeUpdate();
        }
    }

    public static List<Sightngs> all() {
        String sql = "SELECT * FROM sightings";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightngs.class);
        }
    }
}
