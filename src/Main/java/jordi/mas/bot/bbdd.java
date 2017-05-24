package jordi.mas.bot;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jordimasmer on 27/04/2017.
 */

public class bbdd {

    static Connection c = null;
    static Statement stmt = null;
    static boolean connected = false;

    public  bbdd() throws SQLException, ClassNotFoundException {
        connect();
    }


    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://smcdb.ccj5m01jpnyw.eu-west-1.rds.amazonaws.com:5432/smcdata", "monlau", "monlau17");
        //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smc", "monlau", "monlau17");
        if (c != null) {
            if(!connected){
                System.out.println("[POSTGRE] Connected to database");
                connected = true;
            }
            //create();
            //createBBDD();
            //createSeq();
            //System.out.println("Tabla creada.");
            //insert();
            //select();
            //System.out.println("Fila insertada.");

        } else {
            System.out.println("Failed to make connection!");
        }
    }

    private static void insert() throws SQLException{
        c.setAutoCommit(false);
        stmt = c.createStatement();
        String date = getCurrentDate();
        System.out.println(date);
        String query = "INSERT INTO public.data("
                + "motoid, speed, maxspeed, avgspeed, battery, motortemp, batterytemp, latitude, longitude, frontalrotation, siderotation, glp, propane, naturalgas, co, butane, hydrogen, methane, co2, amonia, rad, temp, hum, height, pa, fecha) "
                + "VALUES (3, 100, 100, 60, 34, 40, 50, 41.58258, 2.279663, 15.30, 25.36, 20000, 20, 30, 5, 6, 1, 6, 10, 20, 0.20, 17, 25, 30, 1020, '"+date+"');";//'2016/02/15 13:30:10');";
        stmt.executeUpdate(query);
        stmt.close();
        c.commit();
    }

    private static void create() throws SQLException{
        c.setAutoCommit(false);
        stmt = c.createStatement();

        String query = "CREATE TABLE public.data ("
                + "motoid smallint NOT NULL, speed smallint, "
                + "maxspeed smallint, "
                + "avgspeed smallint, "
                + "battery smallint, "
                + "motortemp smallint, "
                + "batterytemp smallint, "
                + "latitude numeric, "
                + "longitude numeric, "
                + "frontalrotation numeric, "
                + "siderotation numeric, "
                + "glp integer,"
                + "propane smallint, "
                + "naturalgas smallint, "
                + "co smallint, "
                + "butane smallint, "
                + "hydrogen smallint, "
                + "methane smallint, "
                + "co2 smallint, "
                + "amonia smallint, "
                + "rad numeric, "
                + "temp smallint, "
                + "hum smallint, "
                + "height smallint, "
                + "pa smallint, "
                + "fecha text COLLATE pg_catalog.\"default\" NOT NULL, "
                + "id integer NOT NULL DEFAULT nextval('smcdata_id_seq'::regclass), "
                + "CONSTRAINT smcdata_pkey PRIMARY KEY (fecha)"
                + ") "
                + "WITH ( OIDS = FALSE) "
                + "TABLESPACE pg_default; "
                + "ALTER TABLE public.data OWNER to monlau;";

        stmt.execute(query);
        stmt.close();
        c.commit();
    }

    private static void createSeq() throws SQLException{
        c.setAutoCommit(false);
        stmt = c.createStatement();

        String query = "CREATE SEQUENCE public.smcdata_id_seq INCREMENT 1 START 10 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1; "
                + "ALTER SEQUENCE public.smcdata_id_seq OWNER TO monlau;";

        stmt.execute(query);
        stmt.close();
        c.commit();
    }

    private static void createBBDD() throws SQLException{
        c.setAutoCommit(false);
        stmt = c.createStatement();

        String query = "CREATE DATABASE smc WITH OWNER = postgres ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252' TABLESPACE = pg_default CONNECTION LIMIT = -1;";

        stmt.execute(query);
        stmt.close();
        c.commit();
    }

    public String[] selectLastFila() throws SQLException {
        stmt = c.createStatement();
        String sql = "SELECT * FROM data ORDER BY id DESC LIMIT 1; ";
        String[] valores = new String[27]; ;
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            for(int i = 0; i<27; i++){
                valores[i] = rs.getString(i+1);
            }
        }
        stmt.close();
        return valores;
    }

    private static String getCurrentDate(){


        return  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
    }

}
