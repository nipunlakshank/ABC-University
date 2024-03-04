package com.abc.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nipun
 */
public class MySQL implements AutoCloseable {

    private static final String USERNAME;
    private static final String PASSWORD;
    private static final String DATABASE;
    private Connection conn;
    private PreparedStatement stmt;
    private Map<String, Savepoint> savepoints;

    //<editor-fold defaultstate="collapsed" desc=" Set Variables for Database Connection ">
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        USERNAME = "root";
        PASSWORD = "Nipun@db";
        DATABASE = "abc_db";
    }

    //</editor-fold>
    private Map<String, Savepoint> getSavepoints() {
        if (savepoints == null) {
            savepoints = new HashMap<>();
        }
        return savepoints;
    }

    public MySQL createConnection() throws SQLException, ClassNotFoundException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE, USERNAME, PASSWORD);
        conn.setAutoCommit(false);
        return this;
    }

    public MySQL createIUDStatement(String query) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return this;
    }

    public MySQL createSelectStatement(String query) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        stmt = conn.prepareStatement(query);

        return this;
    }

    public MySQL setInt(int index, int value) throws SQLException {
        stmt.setInt(index, value);
        return this;
    }

    public MySQL setString(int index, String value) throws SQLException {
        stmt.setString(index, value);
        return this;
    }

    public MySQL setDouble(int index, double value) throws SQLException {
        stmt.setDouble(index, value);
        return this;
    }

    public MySQL setBoolean(int index, Boolean value) throws SQLException {
        stmt.setBoolean(index, value);
        return this;
    }

    public MySQL setDate(int index, Date date) throws SQLException {
        stmt.setDate(index, date);
        return this;
    }

    public MySQL executeUpdate() throws SQLException {
        stmt.executeUpdate();
        return this;
    }

    public MySQL setSavePoint(String name) throws SQLException {
        Savepoint savepoint = conn.setSavepoint(name);
        getSavepoints().put(name, savepoint);
        return this;
    }

    public ResultSet executeQuery() throws SQLException {
        return stmt.executeQuery();
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return stmt.getGeneratedKeys();
    }

    public void commit() throws SQLException {
        conn.commit();
        stmt.closeOnCompletion();
    }

    public void rollback() throws SQLException {
        conn.rollback();
    }

    public void rollback(String savepointName) throws SQLException {
        if (savepoints.containsKey(savepointName)) {
            conn.rollback(savepoints.get(savepointName));
            return;
        }
        throw new NoSuchFieldError("No savepoint found with name: \"" + savepointName + "\" in current transaction.");
    }

    @Override
    public void close() throws Exception {
        stmt.close();
        conn.close();
    }

}
