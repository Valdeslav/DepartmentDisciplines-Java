package dao.sqlImpl;

import java.sql.Connection;

public abstract class BaseDaoMySqlImpl {
    private Connection connection;

    protected Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
