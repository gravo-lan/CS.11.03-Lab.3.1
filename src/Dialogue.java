import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.*;
import java.util.Random;

public class Dialogue {
    public static ArrayList<String> dialogue = new ArrayList<>();
    public static String flow = "0000100001";
    public static void introduction() throws Exception {
        Scanner input = new Scanner(System.in);
        JSONParser parser = new JSONParser();
        Main.loadData((JSONArray) parser.parse(new FileReader("./src/data.json")));
        load();
        runD();
        System.out.println("[1] Examine the drawer\n[2] Grab something just in case\n[3] Never-mind, I'll by a new phone...");
        int option = input.nextInt();
        if (option==1) text("You grab onto a nearby stick as you feel yourself sucked into the abyss...");
        else if (option==2) text("You grab a nearby stick on your desk, before you suddenly get sucked into the abyss...");
        else System.exit(0);
        Main.progress++;
        Main.saveGame();
        floor1();
    }

    public static void floor1() throws Exception {
        Scanner input = new Scanner(System.in);
        JSONParser parser = new JSONParser();
        Main.loadData((JSONArray) parser.parse(new FileReader("./src/data.json")));
        load();
        runD();
        if (Main.progress==9) {
            System.out.println("Follow the guiding light?\n[1] Yes, I'd rather be able to see! (Exploration Tutorial)\n[2] No, maybe the laughter is coming from someone who could help me! (Combat Tutorial)\n(Don't worry, you can always do one or the other later!)");
            int option = input.nextInt();
            if (option==1) {
                ;
            }
            else if (option==2) {
                text("A shadowy figure appears...");
                text("[COMBAT TUTORIAL]\nCombat is a turn based system. Every turn you can either defend, attack or attempt an escape. Defending will block the incoming enemies' attack, allowing you to recuperate and heal (Critical hits will disable your defenses). Attacking will damage the enemy and escape chances scale with your remaining health.");
                Combat tutorial = new Combat(Main.hp,"Howling Bat");
                tutorial.startCombat();
            }
        }
    }
    private static void text(String str) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if (Main.hp<=0) {
            System.out.println("Oh no! You have died!");
        }
        System.out.print(str);
        Scanner stall = new Scanner(System.in);
        stall.nextLine();
    }

    private static void load() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader("./src/dialogue.json"));
        JSONArray arr = (JSONArray) obj.get("Dialogue");
        for (Object o : arr) {
            dialogue.add((String) o);
        }
    }

    private static void runD() {
        while (Integer.parseInt(String.valueOf(flow.charAt((int) Main.progress)))==0) {
            text(dialogue.get((int) Main.progress));
            Main.progress++;
        }
        try {
            Main.saveGame();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void explore() {
        String currentRoom = "Entrance";
        boolean exit = false;
        while (!exit) {
            System.out.println("Current Room: " + currentRoom);
            text("The guiding light splits into four, before casting itself into the four doors surrounding you...");
            System.out.println("EXPLORATION\n[1] North Wing\n[2] East Wing\n[3] South Wing\n[4] West Wing");
        }
    }
}
