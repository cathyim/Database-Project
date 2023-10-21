import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayersInsert {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ResultTracker";

	static final String USER = "root";

	// insert password
	static final String PASSWORD = "Aa!!15751575";

	public static void main(String[] args) {

		String filePath = "/Users/catherineyim/Desktop/databases/players.csv";
		Connection conn = null;
		
	      try{
	            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	            String sql= " INSERT IGNORE into player values (?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement= conn.prepareStatement(sql);
	            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

	            String line = null;
	            while ((line=lineReader.readLine())!=null){
	                String[] records=line.split(",");

	                for(int i=0; i<records.length; i++){
	                    records[i] = records[i].replace("\"", "");
	                }
	                
	                

	                String player_id= records[0];
	                String tag=records[1];
	                String real_name=records[2];
	                String nationality = records[3];
	                String birthday = records[4];
	                String game_race = records[5];
	                

	                statement.setInt(1, Integer.parseInt(player_id));
	                statement.setString(2, tag);
	                statement.setString(3,real_name);
	                statement.setDate(4, Date.valueOf(birthday));
	                statement.setString(5, game_race);

	                statement.executeUpdate();
	            }
	            lineReader.close();

	            conn.close();
	            System.out.println("Players data has been inserted successfully.\n");
	        }
	        catch (Exception exception){
	            exception.printStackTrace();
	        }
	    }
	}