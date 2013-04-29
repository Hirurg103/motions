package db;

import java.sql.Connection;

public class FirebirdConnection {
	private static volatile Connection firebirdConnection = null;
	private static final Object object = new Object();
	private FirebirdConnection() {}
	
	public static Connection getInstance() {
		if(firebirdConnection == null) {
			synchronized (object) {
				if(firebirdConnection == null) {
					firebirdConnection = DatabaseUtils.createConnection("org.firebirdsql.jdbc.FBDriver", "jdbc:firebirdsql:embedded:/home/dmitry/study/10_semestre/diploma/application/database/motions.fdb?lc_ctype=UTF8", "sysdba", "masterkey");
				}
			}
		}
		return firebirdConnection;
	}
}
