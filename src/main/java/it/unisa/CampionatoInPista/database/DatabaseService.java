package it.unisa.CampionatoInPista.database;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
@Service
public class DatabaseService {
    private final DatabaseConnection connectionCampionatoInPista;

    public DatabaseService() {
        this.connectionCampionatoInPista = new DatabaseConnection();
    }

    public Connection getConnection() {
        return connectionCampionatoInPista.getConnection();
    }

    public void closeConnection() throws SQLException{
        try {
            connectionCampionatoInPista.closeConnessione();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}