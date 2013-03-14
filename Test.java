import java.lang.*;
import java.sql.*;

class Test {
    public static void main(String[] args) {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
        } catch (java.lang.ClassNotFoundException e) {
            // A call to Class.forName() forces us to consider this exception
            System.out.println("Firebird JCA-JDBC driver not found in class path");
            System.out.println(e.getMessage());
        }
        System.out.println("Hello JDBC");
    }
}
