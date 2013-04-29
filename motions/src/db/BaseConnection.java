package db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseConnection {
	protected final String DEFAULT_DRIVER = "";
	protected final String DEFAULT_URL = "";
	protected final String DEFAULT_USERNAME = "admin";
	protected final String DEFAULT_PASSWORD = "admin";

    protected String driver;
    protected String url;
    protected String username;
    protected String password;
    protected Connection connection;
    
    public BaseConnection(String driver, String url, String username, String password) {
    	this.connection = DatabaseUtils.createConnection(driver, url, username, password);
    }
    
    public List<Map<String, Object>> query(String sql, List<Object> parameters) {
    	return DatabaseUtils.query(connection, sql, parameters);
    }
    
    public List<Map<String, Object>> query(String sql) {
    	return query(sql, new ArrayList<Object>());
    }
    
    public int update(String sql, List<Object> parameters) {
    	return DatabaseUtils.update(connection, sql, parameters);
    }
    
    public int update(String sql) {
    	return update(sql, new ArrayList<Object>());
    }
}
