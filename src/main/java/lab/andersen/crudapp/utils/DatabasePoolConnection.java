package lab.andersen.crudapp.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabasePoolConnection {
    static ComboPooledDataSource cpds = new ComboPooledDataSource();

    static {
        try {
            cpds.setDriverClass(Constants.JDBC_DRIVER);
            cpds.setJdbcUrl(Constants.DATABASE_URL);
            cpds.setUser(Constants.USER);
            cpds.setPassword(Constants.PASSWORD);
        } catch (
                PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setMaxStatements(180);
        cpds.setMaxStatementsPerConnection(180);
        cpds.setMinPoolSize(1);
        cpds.setAcquireIncrement(10);
        cpds.setMaxPoolSize(60);
        cpds.setMaxIdleTime(30);
    }

    public Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
