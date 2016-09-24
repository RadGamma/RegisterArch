package common;

import sql.SqlConnect;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommonController {
	
  public static void main(String[] args) throws Exception {
	  
	// start of insert and select   
	SqlConnect dao = new SqlConnect();
    //dao.readDataBase();
    
    // start csv file reader
    String csvFile = "C:/Users/Frazee/Desktop/BankPhase2/Huntington_TotalFile.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    String [] input = null;

    try {

        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String [] csvInput = line.split(cvsSplitBy);
            input = csvInput.clone();
            
            System.out.println("Country " + csvInput[0]) ;

        }

        dao.readDataBase(input);
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // end csv file read
    
  }

} 
