import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

public class MyFileReader {

	URL url = getClass().getResource("words");
	File file = new File(url.getPath());
	
	public MyFileReader(ArrayList<String>categories){
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br1.readLine()) != null) {
				if(st.contains("_")){
					String cat = st.substring(1);
					categories.add(cat);
				}
			}
			br1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//---------------------------------------------------
		for(int i = 0; i < categories.size(); i++){
			System.out.println(categories.get(i));             // added to print the categories in test class
		}
		//---------------------------------------------------
	}
	
	public void fillWords(String category, ArrayList<String> words){
		try{
			BufferedReader br2 = new BufferedReader(new FileReader(file));
			String st;
			while((st = br2.readLine().toLowerCase()) != null){
				if (st.contains("_" + category.toLowerCase())) {
	                while ((st = br2.readLine()) != null) {
	                    if (st.contains("_")) {
	                        break;
	                    } else {
	                        words.add(st);
	                    }
	                }
	                break;
	            }
			}
			br2.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void printArrayLists(ArrayList<String>categories, ArrayList<String>words){
		for(int i = 0; i < categories.size(); i++){
			System.out.println(categories.get(i));
		}
		for(int i = 0; i < words.size(); i++){
			System.out.println(words.get(i));
		}
	}
}
