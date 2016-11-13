import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TreasureClasses {
	Random rand = new Random();
	private ArrayList<TreasureClass> tcList;
	private int index;			//index of treasureClass in ArrayList
	
	//TreasureClass, type holding each treasure class with their items
	private class TreasureClass {
				
		public String treasureClass;
		private ArrayList<String> items;
		
		public TreasureClass(String tc, String o1, String o2, String o3) {
			this.treasureClass = tc;
			items = new ArrayList<>(3);
			items.add(o1);
			items.add(o2);
			items.add(o3);
		}
		public String getRandomItem() {
			return items.get(rand.nextInt(3));
		}
	} //end of treasureClass

	public TreasureClasses(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = new Scanner(file);
		tcList = new ArrayList<>();
		
		while (scan.hasNext()) {
			String line = scan.nextLine();
			Scanner lineScan = new Scanner(line).useDelimiter("\\t");
			TreasureClass tc = new TreasureClass(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next());
			tcList.add(tc);
		}
	}
	
	//get a treasureclass string from monster, search for the loot items associated with treasureclass
	public String getLootItem(String tc) {
		for (int x = 0; x < tcList.size(); x++) {
			if (tc.compareTo(tcList.get(x).treasureClass) == 0) {
				index = x;
			}
		}
		//check if loot item is a treasureClass
		String drop = tcList.get(index).getRandomItem();
		
		for (int x = 0; x < tcList.size(); x++) {
			if (drop.compareTo(tcList.get(x).treasureClass) == 0) {
				//item = new random item from lower treasure class
				drop = tcList.get(x).getRandomItem();
				x = 0;
			}
		}
		return drop; 
	}
	
}//end of treasureClasses


