package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Terminal interface for executing queries on a MySQL instance.
 */
public class Main {
	public static void main(String[] args) {
		// Get connection info from user
		Scanner in = new Scanner(System.in);

		System.out.print("Enter server hostname: ");
		String hostname = in.nextLine().trim();
		
		System.out.print("Enter port: ");
		String port = in.nextLine().trim();
		
		System.out.print("Enter username: ");
		String username = in.nextLine().trim();
		
		System.out.print("Enter schema: ");
		String schema = in.nextLine().trim();
		
		System.out.print("Enter password: ");
		String password = in.nextLine().trim();
		
		System.out.print("Enter query to execute: ");
		String query = in.nextLine().trim();

		// Register MySQL JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("MySQL driver is not installed.");
			System.exit(1);
		}

		// Establish MySQL connection
		System.out.println("Establishing connection...");

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + schema, username, password);
			System.out.println("Connected to database.");
		} catch (SQLException e) {
			System.err.println("Error connecting to database. " + e.getMessage());
			System.exit(1);
		}

		// Execute query
		System.out.println("Executing query...");

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (SQLException e) {
			System.err.println("Error executing query. " + e.getMessage());
			System.exit(1);
		}

		System.out.println("Query completed.");

		// Output returned rows
		// ResultSet Reference:
		// https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/ResultSet.html
		try {
			// Number of columns
			int columns = resultSet.getMetaData().getColumnCount();
			System.out.println("Columns: " + columns);

			// Print column headings
			for (int i = 1; i <= columns; i++) {
				System.out.print(resultSet.getMetaData().getColumnLabel(i) + "    ");
			}
			System.out.println();

			// For each row returned
			while (resultSet.next()) {
				// Print each attribute in row
				for (int i = 1; i <= columns; i++) {
					// NOTE: SQL column indices start at 1
					System.out.print(resultSet.getString(i) + "    ");
				}

				System.out.println();
			}
		} catch (SQLException e) {
			System.err.println("Error processing query results. " + e.getMessage());
			System.exit(1);
		}

		// Clean up connection
		System.out.println("Cleaning up database connection...");

		try {
			resultSet.close();
			statement.close();
			resultSet.close();
			System.out.println("Done.");
		} catch (SQLException e) {
			System.err.println("Error cleaning up database connection. " + e.getMessage());
			System.exit(1);
		}
	}
}
