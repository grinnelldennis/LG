import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Monsters {

	private class Monster {
		public String name;
		private String treasureClass;
		
		//Create each monster by parsing an entire line of monster stat
		//@param monsterstat,	entire line of monster stat
		public Monster(String monster, String a, String b, String tc) {
			name = monster;
			treasureClass= tc;
		}
		public String getTC() { return treasureClass; }
	}
	
	private ArrayList<Monster> monsters;
	private int monsterIndex;
	
	public Monsters(String path) throws FileNotFoundException {
		File file = new File (path);
		Scanner scan = new Scanner(file).useDelimiter("\\t");
		monsters = new ArrayList<>();
		
		while (scan.hasNext()) {
			String line = scan.nextLine();
			Scanner lineScan = new Scanner(line);
			Monster mo = new Monster(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next()); 
			monsters.add(mo);
		}
	}
	
	public String getTreasureClass(){ return monsters.get(monsterIndex).getTC(); }
	
	public String getMonster(){	return monsters.get(monsterIndex).name; }
	
	public void pickNewMonster() {
		Random rand = new Random();
		monsterIndex = rand.nextInt(monsters.size());
	}
}
