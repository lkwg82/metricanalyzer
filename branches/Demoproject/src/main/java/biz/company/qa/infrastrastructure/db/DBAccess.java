/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    private static final String user = "db";
    private static final String password = "company";
    private static final String database = "eu-company-db";
    private final String host;
    /**
     * until now only mysql
     */
    private boolean driverSelected = false;
    private Connection connection;

    public DBAccess(final String host) {
        this.host = host;
    }

    public DBAccess useMySQL() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        driverSelected = true;
        return this;
    }

    public DBAccess connect() throws ClassNotFoundException, SQLException {
        if (!driverSelected) {
            useMySQL();
        }
        String connString = "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + password;
        connection = DriverManager.getConnection(connString);
        return this;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * @return the connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            connect();
        }
        return connection;
    }

}