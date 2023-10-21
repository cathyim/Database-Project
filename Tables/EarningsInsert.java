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

public class EarningsInsert {

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
	            String sql= " INSERT IGNORE into player values (?, ?, ?, ?)";
	            PreparedStatement statement= conn.prepareStatement(sql);
	            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

	            String line = null;
	            while ((line=lineReader.readLine())!=null){
	                String[] records=line.split(",");

	                for(int i=0; i<records.length; i++){
	                    records[i] = records[i].replace("\"", "");
	                }

	                String tournament_id=records[0];
	                String player_id=records[1];
	                String prize_money=records[2];
	                String player_position = records[3];
	                

	                statement.setInt(1, Integer.parseInt(tournament_id));
	                statement.setString(2, player_id);
	                statement.setInt(3, Integer.parseInt(prize_money));
	                statement.setString(4, player_position);

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