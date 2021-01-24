package jtools.tools.services.database;

import java.util.Properties;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Database{
    private final Properties properties;

    public Database(Properties properties){
        this.properties = properties;
    }

    public Connection createConnection() throws SQLException{
        return DriverManager.getConnection(
            this.getURL(), this.getUser(), this.getPassword()
        );
    }

    public void prepare() throws SQLException{

    }

    public void execute(String query) {

    }

    public Properties getProperties() {
        return this.properties;
    }

    public String getPassword(){
        return this.getProperties().getProperty("db.password");
    }

    public String getUser(){
        return this.getProperties().getProperty("db.user");
    }

    public String getDatabase(){
        return this.getProperties().getProperty("db.database");
    }

    public String getHost(){
        return this.getProperties().getProperty("db.host");
    }

    public String getURL(){
        return String.format("jdbc:postgresql://%s/%s", this.getHost(),this.getDatabase());
    }
}
