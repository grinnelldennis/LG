import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Armors {
	Random rand = new Random();
	private ArrayList<Armor> armorList;
	
	private class Armor {
		public String armor;
		private int minac;
		private int maxac;
		
		public Armor(String armor, int min, int max){
			this.armor = armor;
			this.minac = min;
			this.maxac = max;
		}
		public int getRandomBaseStat() {
			return rand.nextInt(maxac - minac) + minac;
		}
	}
	
	public Armors(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = new Scanner(file);
		armorList = new ArrayList<>();
		
		while (scan.hasNext()) {
			String line = scan.nextLine();
			Scanner lineScan = new Scanner(line).useDelimiter("\\t");
			Armor ar = new Armor(lineScan.next(), lineScan.nextInt(), lineScan.nextInt());
			armorList.add(ar);
		}
	}
	
	public String getBaseStat(String drop) {
		for (int x = 0; x < armorList.size(); x++) {
			if (drop.compareTo(armorList.get(x).armor) == 0) {
				return "Defense: " + armorList.get(x).getRandomBaseStat();
			}
		}
		return null;
	}	
}
