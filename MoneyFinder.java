import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MoneyFinder {
  public static void main(String[] args) {

    if (args.length == 0 || args.length > 1) {
      System.out.println("Error: You must have 1 filename\n");
    } else {
        try {
          String filePath = args[0];
          System.out.println("Opening file " + filePath);
	
          BufferedReader buff = new BufferedReader(new FileReader(filePath));
          
          String fileLine;
          while ((fileLine = buff.readLine()) != null) {
            System.out.println(fileLine);
          }
          buff.close();
          
          
       } catch (IOException e) {
           System.out.println("There was an error reading your file\n");
           
         }
    }
  }
}
    

    
