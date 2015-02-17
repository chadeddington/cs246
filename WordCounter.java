import java.io.BufferedReader;
import java.io.FileReader;

public class WordCounter {

 public static void main(String[] args) {

   String filename = args[0];

   WordCounter mc = new WordCounter();
   mc.readFile(filename);
  }

  public void readFile(String filename) {
    String fileLine;
    
    try {
      System.out.println("Opening file '" + filename + "'...");
      BufferedReader buff = new BufferedReader(new FileReader(filename));

       while ((fileLine = buff.readLine()) != null) {
        int wc = 0;
          for (int i = 0; i < fileLine.length(); i++) {

            // Don't let i reach length
            if ((i + 1 ) != fileLine.length()) {
              if (fileLine.charAt(i) != ' ' && fileLine.charAt(i + 1) == ' ') {
                wc++;
              }
            }
          } 

          //if the file last word is not followed by a space
          if (fileLine.charAt(fileLine.length() - 1) != ' ')
          {
            wc++;
          }
          
          System.out.println(wc + ": " + fileLine);
          }
          buff.close();
       } catch (Exception e) {
          System.out.println("Error reading '" + filename + "'...\n");
       }
       
  }
}


