import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.*;
import java.io.*;
import java.nio.file.*;
public class Main {

    public static String name, weapon;
    public static long hp, xp, progress,level;
    public static void main(String[] args) throws Exception {
        JSONParser parser=new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader("./src/data.json"));
        loadData(data);
        System.out.printf("Welcome! \n[1] Continue as %s \n[2] Start a New Game\n",name);
        Scanner input = new Scanner(System.in);
        if (input.nextInt()==2) newGame();
        play();
    }

    private static void play() throws Exception {
        if (progress<5) Dialogue.introduction();
        else if (progress>5) Dialogue.floor1();
    }

    public static void loadData(JSONArray data) {
        for (Object o : data) {
            JSONObject obj = (JSONObject) o;
            name = (String) obj.get("Name");
            hp = (long) obj.get("HP");
            weapon = (String) obj.get("Weapon");
            xp = (long) obj.get("XP");
            progress = (long) obj.get("Progress");
            level = (long) obj.get("Level");
        }
    }
    
    public static void saveGame() throws Exception {
        JSONParser parser=new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader("./src/data.json"));
        for (Object o: data) {
            JSONObject obj = (JSONObject) o;
            obj.put("Name", name);
            obj.put("HP", hp);
            obj.put("Weapon",weapon);
            obj.put("XP", xp);
            obj.put("Progress",progress);
            obj.put("Level", level);
        }
        write(data);
    }

    private static void newGame() throws Exception {
        Files.copy(Paths.get("./src/defaultData.json"), Paths.get("./src/data.json"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Enter your name: ");
        Scanner name = new Scanner(System.in);
        JSONParser parser=new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader("./src/data.json"));
        for (Object o: data) {
            JSONObject obj = (JSONObject) o;
            obj.put("Name",name.next());
        }
        write(data);
    }
    
    private static void write(JSONArray data) throws IOException {
        FileWriter file = new FileWriter("./src/data.json");
        file.write(data.toJSONString());
        file.flush();
    }
}
