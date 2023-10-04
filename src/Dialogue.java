import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.*;
import java.util.Random;

public class Dialogue {
    public static ArrayList<String> dialogue = new ArrayList<>();
    public static String flow = "000010000100001";
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
            System.out.println("Follow the guiding light?\n[1] Yes, I'd rather be able to see! (Exploration Tutorial)\n[2] No, maybe the laughter is coming from someone who could help me! (Combat Tutorial)");
            int option = input.nextInt();
            if (option==1) {
                text("[EXPLORATION TUTORIAL]\nOn each floor of the catacombs there are four wings. In each wing there will be a puzzle to solve, and you will receive a code for the next wing.\nBut beware, enemies lurk around every corner of the catacombs...");
                explore();
                text("A shadowy figure appears...");
                text("[COMBAT TUTORIAL]\nCombat is a turn based system. Every turn you can either defend, attack or attempt an escape. Defending will block the incoming enemies' attack, allowing you to recuperate and heal (Critical hits will disable your defenses). Attacking will damage the enemy and escape chances scale with your remaining health.");
                Combat tutorial = new Combat(Main.hp,"Howling Bat");
                tutorial.startCombat();
                Main.progress++;
            }
            else if (option==2) {
                text("A shadowy figure appears...");
                text("[COMBAT TUTORIAL]\nCombat is a turn based system. Every turn you can either defend, attack or attempt an escape. Defending will block the incoming enemies' attack, allowing you to recuperate and heal (Critical hits will disable your defenses). Attacking will damage the enemy and escape chances scale with your remaining health.");
                Combat tutorial = new Combat(Main.hp,"Howling Bat");
                tutorial.startCombat();
                text("[EXPLORATION TUTORIAL]\nOn each floor of the catacombs there are four wings. In each wing there will be a puzzle to solve, and you will receive a code for the next wing.\nBut beware, enemies lurk around every corner of the catacombs...");
                explore();
                Main.progress++;
            }
        }
        Main.saveGame();
        floor2();
    }

    public static void floor2() throws Exception {
        JSONParser parser = new JSONParser();
        Main.loadData((JSONArray) parser.parse(new FileReader("./src/data.json")));
        load();
        runD();
        if (Main.progress==14) explore();
        System.out.println("MORE FLOORS COMING SOON! PLAY AGAIN... TRY TO FIND ALL THE LORE DROPS AND RIDDLES");
    }

    public static void text(String str) {
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

    private static void explore() throws Exception {
        Random key = new Random();
        int firstKey = key.nextInt(1000,10000);
        int secondKey = key.nextInt(1000,10000);
        int thirdKey = key.nextInt(1000,10000);
        int fourthKey = key.nextInt(1000,10000);
        Random theo = new Random();
        String[] rooms = new String[]{"North", "East","South","West"};
        String firstRoom = rooms[theo.nextInt(4)];
        String secondRoom = rooms[theo.nextInt(4)];
        while (secondRoom.equals(firstRoom)) secondRoom = rooms[theo.nextInt(3)];
        String thirdRoom = rooms[theo.nextInt(4)];
        while (thirdRoom.equals(firstRoom) || (thirdRoom.equals(secondRoom))) thirdRoom = rooms[theo.nextInt(3)];
        String fourthRoom = rooms[theo.nextInt(4)];
        while (fourthRoom.equals(firstRoom) || (fourthRoom.equals(secondRoom) || fourthRoom.equals(thirdRoom))) fourthRoom = rooms[theo.nextInt(4)];
        Puzzle thomasWu = new Puzzle(0,firstKey,"random");
        Puzzle nateZhu = new Puzzle(firstKey,secondKey,"random");
        Puzzle nickShi = new Puzzle(secondKey,thirdKey, "random");
        Puzzle theoZhu = new Puzzle(thirdKey,fourthKey,"random");
        boolean exit = false;
        while (!exit) {
            text("The guiding light splits into four, before casting itself into the four doors surrounding you...");
            System.out.println("EXPLORATION\n[1] North Wing\n[2] East Wing\n[3] South Wing\n[4] West Wing\n[5] Speak the code");
            Scanner input = new Scanner(System.in);
            String room = "final";
            switch (input.nextInt()) {
                case 1 -> room = firstRoom;
                case 2 -> room = secondRoom;
                case 3 -> room = thirdRoom;
                case 4 -> room = fourthRoom;
                default -> room = "final";
            }
            if (room.equals(firstRoom)) thomasWu.startPuzzle();
            else if (room.equals(secondRoom)) nateZhu.startPuzzle();
            else if (room.equals(thirdRoom)) nickShi.startPuzzle();
            else if (room.equals(fourthRoom)) theoZhu.startPuzzle();
            else {
                System.out.println("You return to the centre of this level, and recite the passcode for the next level:");
                Scanner code = new Scanner(System.in);
                if (code.nextInt()==fourthKey) {
                    System.out.println("The walls begin to crumble around you, and the floor collapses...");
                    exit = true;
                }
                else System.out.println("Nothing happens...");
            }
        }
    }
}
