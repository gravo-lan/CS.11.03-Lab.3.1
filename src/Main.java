import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        JSONParser parser=new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader("data.json"));
    }
}
