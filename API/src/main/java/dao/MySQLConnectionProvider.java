/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Provider;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * MySQLConnectionProvider is a class aimes to give a connection
 * provider to the mysql database
 */
class MySQLConnectionProvider implements Provider<Connection> {

    private DataSource ds = null;
    
    private static Provider<Connection> INSTANCE;
    
    private static final String PROPERTY_FILE = "database.properties";
    public static final String PROPERTY_URL = "dburl";
    public static final String PROPERTY_DRIVER = "dbdriver";
    public static final String PROPERTY_USER = "dbuser";
    public static final String PROPERTY_PASSWORD = "dbpassword";

    /**
     * @effetcs Makes this be a new MySQLConnectionProvider
     */
    private MySQLConnectionProvider() {
        ds = loadConnection();
    }
    
    public static Provider<Connection> getInstance() {
        if(INSTANCE==null)
            INSTANCE = new MySQLConnectionProvider();
        
        return INSTANCE;
    }

    /**
     * @pre DataSource ds is not null
     * @return a new connection to the database
     */
    @Override
    public Connection get() {
        
        try {
            return ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * @return the newly created connection
     */
    private static DataSource loadConnection() {
        try {
            DataSource source = getDataSource();
            
            Connection conn = source.getConnection();

            
            return source;
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnectionProvider.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * @return a newly created created and configured data source
     */
    private static DataSource getDataSource() {
        Properties properties = getProperties();
        
        String url = properties.getProperty(PROPERTY_URL);
        String driver = properties.getProperty(PROPERTY_DRIVER);
        String userName = properties.getProperty(PROPERTY_USER);
        String userPassword = properties.getProperty(PROPERTY_PASSWORD);
        
        DataSource source = createDataSource(url, driver, userName, 
                userPassword);
        
        return source;
    }
    
    /**
     * @return return the property object from the database configuration file
     */
    private static Properties getProperties() {
        Properties properties = new Properties();
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTY_FILE);
        
        try {
            properties.load(propertiesFile);
        } catch (IOException ex) {
            Logger.getLogger(MySQLConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return properties;
    }
    
    /**
     * @pre all params are not null
     * @param url the url to connect to the database
     * @param driver the database driver
     * @param userName the database username
     * @param userPassword the database password
     * @return a new data source with the given informations
     */
    private static DataSource createDataSource(String url, String driver, 
            String userName, String userPassword) {
        BasicDataSource source = new BasicDataSource();
        source.setUrl(url);
        source.setDriverClassName(driver);
        source.setUsername(userName);
        source.setPassword(userPassword);
        source.setDefaultAutoCommit(Boolean.TRUE);
        
        return source;
    }
}
