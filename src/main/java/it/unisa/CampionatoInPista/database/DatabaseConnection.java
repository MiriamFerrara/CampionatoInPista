package it.unisa.CampionatoInPista.database;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.sql.*;
@Component
public class DatabaseConnection {
    private Connection con;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/CampionatoInPista"
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
                    + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String pwd = "12345678";
            con = DriverManager.getConnection(url, username, pwd);
            System.out.println("Connessione Accettata.");

            /**
             * Shutdown Hook:
             * La chiamata a Runtime.getRuntime().addShutdownHook viene utilizzata per registrare un thread
             * che verrà eseguito prima della chiusura dell'applicazione.
             * In questo caso, il thread eseguirà il metodo closeConnection, responsabile della chiusura
             * corretta della connessione al database quando l'applicazione viene terminata.
             */
            Runtime.getRuntime().addShutdownHook(new Thread(this::closeConnection));
        } catch (Exception e) {
            System.out.println("Connessione Fallita");
        }
    }

    public Connection getConnection() {
        return con;
    }


public void closeConnection() {
    if (con != null) {
        try {
            con.close();
            System.out.println("Connessione chiusa correttamente.");
        } catch (SQLException e) {
            System.out.println("Errore durante la chiusura della connessione.");
            e.printStackTrace();
        }
    }
}
}