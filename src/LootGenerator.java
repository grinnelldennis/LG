import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//initiate all files
		String path = "C:\\computer_science\\workspace\\Homework 7- Loot Generator\\dat\\";
		Monsters monsters = new Monsters(path + "monstats.txt");
		TreasureClasses tc = new TreasureClasses(path + "TreasureClassEx.txt");
		Armors armors = new Armors(path + "armor.txt");
		Affixes pfix = new Affixes(path + "MagicPrefix.txt");
		Affixes sfix = new Affixes(path + "MagicSuffix.txt");
		Scanner scan = new Scanner(System.in);
		
		//body
		String run = "y";
		while (run.compareToIgnoreCase("y") == 0) {
			
			String monster = pickMonster(monsters);
			System.out.println("\n\nFighting " + monster + "...");
			System.out.println("You have slained " + monster + "!");
			System.out.println(monster + " dropped:\n");
			
			System.out.println(generateAffix(monsters, tc, pfix, sfix, armors));
			
			System.out.print ("\nFight again [y/n]? ");
			run = scan.nextLine();
			
			while ( !(run.compareToIgnoreCase("y") == 0 || 
					run.compareToIgnoreCase("n") == 0)) {
				System.out.println("Invalid expression, enter 'y' or 'n'");
				run = scan.next();
			}
		}
		System.out.println("\nUser terminated loot generator.");
	}

    public static String pickMonster(Monsters monsters){
    	monsters.pickNewMonster();
    	return monsters.getMonster();
    }
        
    public static String generateBaseItem(Monsters mo, TreasureClasses classList) {
    	//get treasure class to get item
    	return classList.getLootItem(fetchTreasureClass(mo));
    }

    public static String fetchTreasureClass(Monsters mo) {
    	return mo.getTreasureClass();
    }
    
    public static String generateBaseStats(Armors ar, String baseItem) {
    	return ar.getBaseStat(baseItem);
    }
    
    public static String generateAffix(Monsters monsters, TreasureClasses tc, Affixes pfix, 
    		Affixes sfix, Armors ar){
    	
    	Random rand = new Random();
    	int affOption = rand.nextInt(4);
    	switch (affOption) {
    	case 0:
    		pfix.setRandomAffix();
    		sfix.setRandomAffix();
    		return printAffix(pfix, generateBaseItem(monsters, tc), sfix, ar);
    	case 1:
    		pfix.setRandomAffix();
    		return printAffix(pfix, generateBaseItem(monsters, tc), ar);
    	case 2:
    		sfix.setRandomAffix();
    		return printAffix(generateBaseItem(monsters, tc), sfix, ar);
    	case 3:
    		return printAffix(generateBaseItem(monsters, tc), ar);
    	}
    	return null;
    }
    
    public static String printAffix(Affixes pf, String item, Affixes sf, Armors ar) {
    	return pf.getName() + " " + item + " " + sf.getName() + 
    			"\n" + ar.getBaseStat(item) + 
    			"\n" +	pf.getAffixStat() + "\n" + sf.getAffixStat();
    }
    public static String printAffix(Affixes pf, String item, Armors ar) {
    	return pf.getName() + " " + item +
    			"\n" + ar.getBaseStat(item) + "\n" + pf.getAffixStat();
    }
    public static String printAffix(String item, Affixes sf, Armors ar) {
    	return item + " " + sf.getName() + 
    			"\n" + ar.getBaseStat(item) + 
    			"\n" + sf.getAffixStat();
    }
    
    public static String printAffix(String item, Armors ar) {
    	return item + "\n" + ar.getBaseStat(item);
    }
}
