import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


public class Collections {
	
	public static void main(String[] args) {

		Collections myCollection = new Collections();
		//String booksFile = args[0];
                String booksNChapters = "booksAndChapters.txt";
                
                Map myMap = myCollection.readMap(booksNChapters);
                myCollection.displayMap(myMap);

		//List<String> booksList = myCollection.readList(booksFile);
		//myCollection.displayList(booksList);

	}

	public List<String> readList(String file) {
		String fileLine;
		List<String> books = new ArrayList<String>();
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));

			while ((fileLine = buff.readLine()) != null) {
				books.add(fileLine);
			}

		} catch (Exception e) {}

		return books;

	}

	public void displayList(List<String> theList) {
		for (String book: theList) {
			System.out.println(book);
		}
	}
        
        public Map readMap(String fileName) {
            Map<String, String> map = new HashMap();
            String fileLine;
            String[] parts = {};
                    
            try {
                    BufferedReader buff = new BufferedReader(new FileReader(fileName));
                    
                    int i = 0;
                    while ((fileLine = buff.readLine()) != null) {
                        parts = fileLine.split(":");
                        map.put(parts[0],parts[1]);
                    }
                        
		} catch (Exception e) {}

		return map;
            
        }
        
        public void displayMap(Map m) {            
            Iterator<Map.Entry> entries;
            entries = m.entrySet().iterator();
            
            while(entries.hasNext()) {
                Map.Entry entry = entries.next();
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }

}