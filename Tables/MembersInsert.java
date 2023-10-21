import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembersInsert {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ResultTracker";

	static final String USER = "root";

	// insert password
	static final String PASSWORD = "Aa!!15751575";

	public static void main(String[] args) {

		String filePath = "/Users/catherineyim/Desktop/databases/members.csv";
		Connection conn = null;
		
	      try{
	            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	            String sql= " INSERT IGNORE into members values (?, ?, ?, ?)";
	            PreparedStatement statement= conn.prepareStatement(sql);
	            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

	            String line = null;
	            while ((line=lineReader.readLine())!=null){
	                String[] records=line.split(",");

	                for(int i=0; i<records.length; i++){
	                    records[i] = records[i].replace("\"", "");
	                }
	                
	                

	                String player_id=records[0];
	                String team_id=records[1];
	                String start_date=records[2];
	                String end_date = records[3];
	                

	                statement.setInt(1, Integer.parseInt(player_id));
	                statement.setInt(2, Integer.parseInt(team_id));
	                statement.setDate(3, Date.valueOf(start_date));
	                
	                
	    			if (records.length < 4) {
						statement.setNull(4,  Types.DATE);
					}
					else {
						statement.setDate(4, Date.valueOf(end_date));
		 
					}
				
					statement.executeUpdate();
	            }
	            lineReader.close();

	            conn.close();
	            System.out.println("Members data has been inserted successfully.\n");
	        }
	        catch (Exception exception){
	            exception.printStackTrace();
	        }
	    }
	}