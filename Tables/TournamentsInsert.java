import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentsInsert {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ResultTracker";

	static final String USER = "root";

	// insert password
	static final String PASSWORD = "Aa!!15751575";

	public static void main(String[] args) {

		String filePath = "/Users/catherineyim/Desktop/databases/earnings.csv";
		Connection conn = null;
		
	      try{
	            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	            String sql= " INSERT IGNORE into tournaments values (?, ?, ?, ?)";
	            PreparedStatement statement= conn.prepareStatement(sql);
	            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

	            String line = null;
	            while ((line=lineReader.readLine())!=null){
	                String[] records=line.split(",");

	                for(int i=0; i<records.length; i++){
	                    records[i] = records[i].replace("\"", "");
	                }
	                String tournament_id=records[0];
	                String tournament_name=records[1];
	                String region=records[2];
	                String major = records[3];
	                

	                statement.setInt(1, Integer.parseInt(tournament_id));
	                statement.setString(2, tournament_name);
	                statement.setString(3, region);
	                statement.setBoolean(4, Boolean.valueOf(major));

	                statement.executeUpdate();
	            }
	            lineReader.close();

	            conn.close();
	            System.out.println("Tournaments data has been inserted successfully.\n");
	        }
	        catch (Exception exception){
	            exception.printStackTrace();
	        }
	    }
	}