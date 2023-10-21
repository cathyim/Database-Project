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

public class MatchesInsert {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ResultTracker";

	static final String USER = "root";

	// insert password
	static final String PASSWORD = "Aa!!15751575";

	public static void main(String[] args) {

		String filePath = "/Users/catherineyim/Desktop/databases/matches.csv";
		Connection conn = null;
		
	      try{
	            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	            String sql= " INSERT IGNORE into matches values (?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement= conn.prepareStatement(sql);
	            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

	            String line = null;
	            while ((line=lineReader.readLine())!=null){
	                String[] records=line.split(",");

	                for(int i=0; i<records.length; i++){
	                    records[i] = records[i].replace("\"", "");
	                }
	                String matches=records[0];
	                String date=records[1];
	                String tournament_id=records[2];
	                String playerA_id = records[3];
	                String playerB_id = records[4];
	                String playerA_score = records[5];
	                String playerB_score = records[6];
	                String is_offline = records[7];
	                

	                statement.setInt(1, Integer.parseInt(tournament_id));
	                statement.setDate(2, Date.valueOf(date));
	                statement.setInt(3, Integer.parseInt(tournament_id));
	                statement.setInt(4, Integer.parseInt(playerA_id));
	                statement.setInt(5, Integer.parseInt(playerB_id));
	                statement.setInt(6, Integer.parseInt(playerA_score));
	                statement.setInt(7, Integer.parseInt(playerB_score));
	                statement.setBoolean(8, Boolean.valueOf(is_offline));

	                statement.executeUpdate();
	            }
	            lineReader.close();

	            conn.close();
	            System.out.println("Earnings data has been inserted successfully.\n");
	        }
	        catch (Exception exception){
	            exception.printStackTrace();
	        }
	    }
	}