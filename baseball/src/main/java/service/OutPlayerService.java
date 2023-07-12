package service;
import java.sql.Connection;

public class OutPlayerService {
    private Connection connection;
    public OutPlayerService(Connection connection) {
        this.connection = connection;
    }
}
