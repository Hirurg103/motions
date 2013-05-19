package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtils {
	private static Connection defaultConnection = FirebirdConnection.getInstance();
	
	static {
		if(defaultConnection == null) {
			System.out.println("Unable to connect to the dafault database");
			System.exit(0);
		}
	}

	public static Connection createConnection(String driver, String url, String username, String password) {
		Connection connection = null;
		try {
			Class.forName(driver);	
		} catch(ClassNotFoundException e) {
			System.out.println("Firebird JCA-JDBC driver not found in class path");
			e.printStackTrace();
		}
        try {
	        if ((username == null) || (password == null) || (username.trim().length() == 0) || (password.trim().length() == 0)) {
	            connection = DriverManager.getConnection(url);
	        } else {
	            connection = DriverManager.getConnection(url, username, password);
	        }
        } catch(SQLException e) {
        	System.out.println("Unable to establish a connection through the driver manager.");
        	showSQLException(e);
        	e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
        	System.out.println("Unable to close a connection.");
        	showSQLException(e);
            e.printStackTrace();
        }
    }


    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
        	System.out.println("Unable to close a statement.");
        	showSQLException(e);
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
        	System.out.println("Unable to close a result set.");
        	showSQLException(e);
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            showSQLException(e);
        }
    }

    public static List<Map<String, Object>> map(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

        try {
            if (rs != null) {
                ResultSetMetaData meta = rs.getMetaData();
                int numColumns = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (int i = 1; i <= numColumns; ++i) {
                        String name = meta.getColumnName(i);
                        Object value = rs.getObject(i);
                        row.put(name, value);
                    }
                    results.add(row);
                }
            }
        }
        finally {
            close(rs);
        }
        
        return results;
    }

    public static List<Map<String, Object>> query(Connection connection, String sql, List<Object> parameters) {
        List<Map<String, Object>> results = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = connection.prepareStatement(sql);
            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            rs = ps.executeQuery();
            results = map(rs);
            printStatement(sql, parameters);
        } catch(SQLException e) {
        	System.out.println("Unable to submit a static SQL query.");
        	e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
        }

        return results;
    }
    
    public static List<Map<String, Object>> query(Connection connection, String sql) { return query(connection, sql, new ArrayList<Object>()); }
    
    public static List<Map<String, Object>> query(String sql, List<Object> parameters) { return query(defaultConnection, sql, parameters); }
    
    public static List<Map<String, Object>> query(String sql) { return query(defaultConnection, sql, new ArrayList<Object>()); }

    public static int update(Connection connection, String sql, List<Object> parameters) {
        int numRowsUpdated = 0;

        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(sql);

            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }

            numRowsUpdated = ps.executeUpdate();
            printStatement(sql, parameters);
        } catch(SQLException e) {
        	System.out.println("Unable to submit a SQL update.");
        	e.printStackTrace();
        } finally {
            close(ps);
        }

        return numRowsUpdated;
    }
    
    public static int update(Connection connection, String sql) { return update(connection, sql, new ArrayList<Object>()); }
    
    public static int update(String sql, List<Object> parameters) { return update(defaultConnection, sql, parameters); }
    
    public static int update(String sql) { return update(defaultConnection, sql); }
    
    public static List<Map<String, Object>> execute(Connection connection, String sql, List<Object> parameters, String[] columnNames) {
    	List<Map<String, Object>> results = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try {
    		ps = /*columnNames.length == 0 ? connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS) :*/ connection.prepareStatement(sql, columnNames);
    		int i = 0;
    		for(Object o:parameters) ps.setObject(++i, o);
    		
    		ps.execute();
    		rs = ps.getGeneratedKeys();
    		results = map(rs);
    		printStatement(sql, parameters);
    	} catch(SQLException e) {
    		System.out.println("Unable to execute sql");
    		showSQLException(e);
    		e.printStackTrace();
    	} finally {
    		close(rs);
    		close(ps);
    	}
    	return results;
    }
    
    public static List<Map<String, Object>> execute(Connection connection, String sql, String[] columnNames) {
    	return execute(connection, sql, new ArrayList<Object>(), columnNames);
    }
    
    public static List<Map<String, Object>> execute(Connection connection, String sql, List<Object> parameters) {
    	return execute(connection, sql, parameters, new String[] {});
    }
    
    public static List<Map<String, Object>> execute(Connection connection, String sql) {
    	return execute(connection, sql, new ArrayList<Object>(), new String[] {});
    }
    
    public static List<Map<String, Object>> execute(String sql, List<Object> parameters, String[] columnNames) {
    	return execute(defaultConnection, sql, parameters, columnNames);
    }
    
    public static List<Map<String, Object>> execute(String sql, String[] columnNames) {
    	return execute(sql, new ArrayList<Object>(), columnNames);
    }
    
    public static List<Map<String, Object>> execute(String sql, List<Object> parameters) {
    	return execute(sql, parameters, new String[] {});
    }
    
    public static List<Map<String, Object>> execute(String sql) {
    	return execute(sql, new ArrayList<Object>(), new String[] {});
    }
    
    public static void printStatement(String sql, List<Object> parameters) {
    	System.out.println(sql + "@" + parameters);
    }
    
    /**
     * Display an SQLException which has occurred in this application.
     * @param e SQLException
     */
    private static void showSQLException(SQLException e) {
        /* Notice that a SQLException is actually a chain of SQLExceptions,
         * let's not forget to print all of them
         */
        java.sql.SQLException next = e;
        while (next != null) {
            System.out.println(next.getMessage());
            System.out.println("Error Code: " + next.getErrorCode());
            System.out.println("SQL State: " + next.getSQLState());
            next = next.getNextException();
        }
    }

	public static Connection getDefaultConnection() { return defaultConnection; }

	public static void setDefaultConnection(Connection defaultConnection) { DatabaseUtils.defaultConnection = defaultConnection; }
}