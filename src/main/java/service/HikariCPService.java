package service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import domain.Constants;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPService {

        private static HikariConfig config = new HikariConfig();
        private static HikariDataSource ds;

        static {
            config.setJdbcUrl(Constants.dbUrl);
            config.setUsername( Constants.dbUsername);
            config.setPassword( Constants.dbPassword );
            config.addDataSourceProperty( "cachePrepStmts" , "true" );
            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            ds = new HikariDataSource( config );
        }

        private HikariCPService() {}

        public static Connection getConnection() throws SQLException {
            return ds.getConnection();
        }
}
