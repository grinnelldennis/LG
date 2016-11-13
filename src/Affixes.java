import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Affixes {
	Random rand = new Random(); 
	
	private class Affix {
		private String affix;
		private String skill; 
		private int min;
		private int max;
		
		public Affix(String a, String s, int min, int max) {
			this.affix = a;
			this.skill = s;
			this.min = min;
			this.max = max;
		}
		public int getRandomSkillStat() {
			if (max != min){ return rand.nextInt(max - min) + min; }
			else { return min; }
		}
		public String getAffix() { return affix; }
		public String getSkill() { return skill; }
	}
	
	private ArrayList<Affix> affixList;
	private int index;
	
	public Affixes (String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = new Scanner(file);
		affixList = new ArrayList<>();
		
		while (scan.hasNext()) {
			String line = scan.nextLine();
			Scanner lineScan = new Scanner(line).useDelimiter("\\t");
			Affix af = new Affix(lineScan.next(), lineScan.next(), lineScan.nextInt(), lineScan.nextInt());
			affixList.add(af);
		}
	}
	public void setRandomAffix() { index = rand.nextInt(affixList.size()); }
	
	public String getAffixStat() {
		return "" + affixList.get(index).getRandomSkillStat() + " " + affixList.get(index).getSkill();  	
	}
	
	public String getName() { return affixList.get(index).getAffix(); }
	
}
