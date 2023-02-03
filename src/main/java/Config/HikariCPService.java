package Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPService {

        private static final HikariConfig config = new HikariConfig();
        public static HikariCPService instance = null;
        private static HikariDataSource instanceDataSource = null;

        public static HikariCPService getInstance()
        {
        HikariCPService localInstance = instance;

        if(localInstance == null)
        {
            synchronized (HikariCPService.class)
            {
                localInstance = instance;
                if(localInstance==null)
                {
                    instance = localInstance = new HikariCPService();
                    initDataSource();
                }
            }
        }
        return localInstance;
    }
        public static void initDataSource()
        {
        System.out.println("Connection Pooling");
        config.setJdbcUrl(Constants.DB_URL);
        config.setUsername( Constants.DB_USERNAME);
        config.setPassword( Constants.DB_PASSWORD);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.setDriverClassName(Constants.DB_DRIVER);

        if(instanceDataSource==null)
            instanceDataSource = new HikariDataSource( config );
    }

        public Connection getConnection() throws SQLException {
                return instanceDataSource.getConnection();
        }
}
