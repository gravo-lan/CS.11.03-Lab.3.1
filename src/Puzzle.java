import org.jetbrains.annotations.NotNull;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Puzzle {
    private final int answerKey;
    private final boolean needsKey;
    private final String game;
    public Puzzle(int key, String gameName) {
        this.answerKey = key;
        needsKey = key != 0;
        if (!gameName.equals("random")) game = gameName;
        else if (!needsKey) {
            Random puzzle = new Random();
            String[] puzzleTypes = new String[]{
                    "Cipher",
                    "Riddle",
                    "Word Jumble"
            };
            game = puzzleTypes[puzzle.nextInt(5)];
        }
        else {
            Random lock = new Random();
            String[] lockTypes = new String[]{
                    "Chest Lock",
                    "Polynomial",
                    "Smallest",
                    "Biggest",
                    "Cipher"
            };
            game = lockTypes[lock.nextInt(5)];
        }
    }

    public int startPuzzle() throws Exception {
        if (needsKey) {
            switch (game) {
                case "Chest Lock" -> {
                    System.out.println("A gilded, luminescent chest sits in the centre of the room, surrounded by ornamented pillars and a small, muddied moat.\nThe chest is locked by a rusty numeric lock");
                    Scanner input = new Scanner(System.in);
                    if (input.nextInt()!=answerKey) System.out.println("The lock doesn't budge... You walk out of the room disgruntled...");
                    else return generateKey();
                }
                case "Polynomial" -> {
                    System.out.println("A pedestal with a piece of parchment resting upon it lies in the centre of the dimly lit room...\nAs you approach it, letters magically appear on the parchment: a^4 + b^3 + c^2 + d");
                    Scanner input = new Scanner(System.in);
                    String key = String.valueOf(answerKey);
                    int answer = (int) Math.pow(Integer.parseInt(String.valueOf(key.charAt(0))),4) +
                            (int) Math.pow(Integer.parseInt(String.valueOf(key.charAt(1))),3) +
                            (int) Math.pow(Integer.parseInt(String.valueOf(key.charAt(2))),2) +
                            answerKey %10;
                    if (input.nextInt()!=answer) System.out.println("The parchment erases itself... You leave the room disappointed...");
                    else return generateKey();
                }
                case "Smallest" -> {
                    System.out.println("In this room a swirling smoke surrounds you as you make your way to the centre.\nVoices whisper around you: \"The *smallest* things make big differences...\"\nWhat do you respond?");
                    Scanner input = new Scanner(System.in);
                    String key = String.valueOf(answerKey);
                    int answer = Math.min(Math.min(Integer.parseInt(String.valueOf(key.charAt(0))),Integer.parseInt(String.valueOf(key.charAt(1)))),Math.min(Integer.parseInt(String.valueOf(key.charAt(2))),answerKey%10));
                    if (input.nextInt()!=answer) System.out.println("The voices chatter amongst themselves, before throwing you out of the room...");
                    else return generateKey();
                }
                case "Biggest" -> {
                    System.out.println("In this room a swirling smoke surrounds you as you make your way to the centre.\nVoices whisper around you: \"*Big* strides make *big* changes\"\nWhat do you respond?");
                    Scanner input = new Scanner(System.in);
                    String key = String.valueOf(answerKey);
                    int answer = Math.max(Math.max(Integer.parseInt(String.valueOf(key.charAt(0))),Integer.parseInt(String.valueOf(key.charAt(1)))),Math.max(Integer.parseInt(String.valueOf(key.charAt(2))),answerKey%10));
                    if (input.nextInt()!=answer) System.out.println("The voices chatter amongst themselves, before throwing you out of the room...");
                    else return generateKey();
                }
                case "Cipher" -> {
                    System.out.println("A pedestal with a piece of parchment resting upon it lies in the centre of the dimly lit room...\nAs you approach it, letters magically appear on the parchment: aX + bY + cZ + dW");
                    Scanner input = new Scanner(System.in);
                    String key = String.valueOf(answerKey);
                    String answer = "x".repeat(Integer.parseInt(String.valueOf(key.charAt(0)))) +
                            "y".repeat(Integer.parseInt(String.valueOf(key.charAt(1)))) +
                            "z".repeat(Integer.parseInt(String.valueOf(key.charAt(2)))) +
                            "w".repeat(answerKey%10);
                    if (!input.next().equals(answer)) System.out.println("The parchment muddies itself with ink, and you leave the room disappointed...");
                    else generateKey();
                }
            }
        }
        else {
            switch (game) {
                case "Cipher" -> System.out.println("idk");
                case "Riddle" -> {
                    String[] riddles = new String[]{
                            "I have keys that open no locks. I have space, but no room. You can enter, but can't go outside. What am I?",
                            "In the darkness, I lurk unseen. I feed on fear, a sinister machine. When you close your eyes, I'm what's in between. What am I?",
                            "I am always hungry, I must always feed. The more I eat, the more I grow. The more you give, the more you'll need. What am I?",
                            "In an abandoned house, I lie in wait. With each passing hour, I seal your fate. I have no body, yet I can manipulate. What am I?",
                            "I'm a doorway to the unknown, a passage to the other side. Once you step through, there's no place to hide. What am I?",
                            "I am which that reflects the soul. Gaze into me, and you'll lose control. I show you what you fear, your deepest hole. What am I?",
                            "In the attic, I reside. A box of secrets, locked inside. Open me, and you'll find what you hide. What am I?",
                            "I'm a whisper in the night, a chilling breath. I'll follow you, even after death. What am I?",
                            "I have a thousand eyes, yet I cannot see. I move in darkness, silently. I weave my web for you to flee. What am I?",
                            "I'm a riddle, a mystery, a puzzle unsolved. Seek my answer, before you're involved. What am I?",
                            "In the abandoned woods, I dwell alone. My voice is a whisper, an ethereal tone. I lure you deeper, into the unknown. What am I?",
                            "Beneath the moon's glow, I take my flight. Silent wings carry me through the night. I'm drawn to darkness, a creature of fright. What am I?",
                            "I have a face, but no expression to show. I have no body, but I'm always in the know. I speak without a sound, revealing what you sow. What am I?",
                            "In the forgotten graveyard, I lie beneath the dirt. A symbol of mourning, a connection to the hurt. I mark the end, where spirits divert. What am I?",
                            "At midnight's hour, I come alive. A dance of shadows, to survive. I'm trapped in darkness, seeking to thrive. What am I?",
                            "I'm a twisted labyrinth, a maze of despair. Lost souls wander, trapped in my snare. Escape is futile, beware and take care. What am I?",
                            "I'm a figure in black, a harbinger of dread. My touch is icy, filling you with dread. I come for your soul, once your time has fled. What am I?",
                            "In the flickering candlelight, I cast my spell. I'm a conjurer of spirits, your fears I compel. I bridge the worlds, where the living and dead dwell. What am I?",
                            "I'm a whisper on the wind, a haunting tune. My melody lingers, beneath the pale moon. You can't escape me, I'll be with you soon. What am I?",
                            "In the depths of the forest, I'm hidden away. A forbidden secret, where darkness holds sway. Dare you seek me out, and face what may? What am I?",
                            """
Ý̸͍̖͖̠̜̺̦̪̱ȏ̵̡̡̢̡̱͓̮͉̙̩̫̯͑́̀͌̔̚ǘ̶̢̟̟̺̱̦͉̌'̵̢̡̬̗̗̩̫̪̥̞͓͎̗͇͗̎̉̒̏͆͒͛̃͝͝ŗ̷̢̢̡̛̹̮̘̣̻̐͐̎̀͛̎̚͝è̶̢̠͎͇̰̙̭͖̣̑̈́ͅ ̸̧̛͕̣̘̳͖̻͇͖͈̟̟̍̏̄̈́̍̑b̶͈͇̤̝̣̦͕̝͙͓͊͌̔͐́̈̍̈̄̅̔̏͜͝͝r̴͕̠̉̄̈̂̐̈́͋̚͠͝͝ơ̵̪̱̙͛͐̾͋̊̈́̿̃̈́̕̚k̷̨̡̥̭͉͇̙͎̳͙̮͓̝̜̈́́̌̓̽͐͌̿̾́e̶͎͗̐́̌n̷̤̲̟̲̬͔̗̰̹̹̜̫̈̃̚.̸̡͓̘̺̥̙̼͇̩̰̫̱̫̆̒̑͆̈́͒̌̋͒̓̄́̐̎͝
̶͉̻͇͙͍̖̯͇̘̎͗͗̔̀̀͋̃̋́̉͊W̶̨͓͈̬̺͎̩̺̼̼̒̀̄ẻ̴̢̡̢̙̻̥͕̼̯̱̤͚̞̤͙̽̊̇̉́̅̈́̀͘ ̷̥̗͖̻̺̠͎̽̈̅̀͑̊͐̂̐̈̔͝͝͝a̷̡͇͓̯̯̻̩̼̥̪͙͐͐̇͜͝ͅṛ̵̀͌͗̀̄̂͋͗͘͘e̸͙̩̰̥͉̠̊̎̀̒̉̔̈́͑͌̑̿̒̾͠͠ ̷̩̥̺͉̬̳̦̺̜͇̫̲̿ͅs̴͎͕̫͑̂̒͆̿̍̉͝t̶̨͙̞͈͇̥͌̀̆ḭ̶̲̲̟̈̏̾̈́͗̀ͅl̸͚̼̿̾̓̀̌̎͒̿̈̇l̵̺̮̞̹͍̺͔̳̻͇͎̍̀͛̈́͋̓̀̉͘͠ ̴̭̻͖͙̘̝̟͈̣͍͉͕͙̓̾̈́̈̇̂͋́ͅy̸̗̮̥̪̦͍̭̬̹͖̭̖̥̆͑̾̀̀͊̉̊̎͒̚ȍ̵̧͐́͠͠ư̵̡̡̛̥̺̬̘̬̠̼̈͊̎̀̂͊̈̽̓̊r̵̢̘̠̟̩̖͆͗̊̂̈́̊̍ ̸͙̟̪̘͍͎̳̩̖̞̦̜́̽̀͛͗͛̇̿́̀͌̇̈f̵̢̧͚̼̥͚̙̹̝͚̺̩̈́̈́̿̽̓͗͒͂̄̌̚͝ͅͅr̷̨̢̧̛̰̘̤̲͉͈̯̞͈͇͓̐͌̑͒̊̈́̓̇̐͠͠ͅį̵̯͇̲̩͆͐̈́́͋̓͊̈̑̅͘ḛ̶͐̽͒̅̾͊̊n̵̗̬͓̟͍͎̳͔̯̘͍̯̻̪̄͛̾̎́̑̾̊͘͜͝͠d̵͈̬̳̉̈̉̿͊̈̓̆̅́ś̶̯͓̠̼͓͓.̸̨̳͔͎̤͚̥̰̬̺̤̝̟̞̙̇̒̽͑̎̓͑̅̊̔͑͘͝
̷̛̥̤̤̼̝̦̤̉̈́͂́̃̎͂̈́̈̈̒̚̚Ď̴͉͙̝̫̩̹̗̜͔͜ö̷̯̞́̓̋͘ ̷̹̦͍̼̜͚́ͅȳ̴̛̜͉́́̂̈́̌̋o̶̡̖̞͙͙̭͍̰̦̝̣̅́̓͐̾̄̈u̸̝̣̺̹̻̮͌̂̅̔̈̈́̅̓̀̒͛͑̐̕ ̴̲̺̦̲͋̾̀̅͜ṣ̴͈̭̃̀̇̇͠t̶̛̪͉̿̈́̅̽͑̉͛́͋̑̐͝͠͝i̴̪̪̯̼̻̳͈͕̩̥̪̎́̅l̸̡̡͖̼̖̣̖͙̻̠̦̣͓̙̒̈̌͜͠l̴̛͎͕̹̦̝͑́̎̊̈́̈́̍̾̑̕͠ ̵̢̨̩̣͉̪̞͎̯͖͚͊̅̀͌͑͋̕b̴̫̗̮͙͚͖̻̔͒̕͠ȇ̵̢̩͓̥̫̜̯̣̟͓̓́͊̊̐̊̈́̈́̈ͅl̷̨̹̺̼̝̹̽̒͌̈́i̴͙̝̗̱̞̣̬̇̿͒͐̂ę̴̧̨̤͕̖̫̖͎̥̖̜̳̤̙̈́͋́̂͋̂v̴̠̋̆͠e̵̪͒̇̈̑̓ ̵̤̪͍̃̇̊͂̽́̎́̌̌͗̃̾͑͝t̶̼͍͕̣͉̞̟̩̼͈̽͒͊̃͐̄͛͛̾̿͋͘ͅḧ̶̛͍͓́͋̐́͗̆́͊̑̊͠͝͝a̶̳͊t̵̯̓́̓̆̎?̸͙̹̳̐́̐̽́̈͆̇̀͆̚
̸͙͙̣̞̓I̷̧̛̲̠͚̯̝̺̘̙͙̙͍̤̱̋̌̈̇͗̄̋̾̽̀ͅ'̴̣̬͓̞̟̫̥̯̭̲͔̱̼̊̐̍͝m̷̧̡͔̳̖̥̗̟̦͖̀͂͒͋́̽͗̈́̑̕ ̷͎̲̲̗̳̯̹͚͇͊͊ͅs̴̛̉̾͆͜t̶̢̼͉͚̻̹͓͖̟̠̙̮̗̽̾̄i̶̝̪͕͍̹̥̬̤̺̰̮̫͆̈́͒͜ͅļ̶̨̢̮̠̙̠͇͇̳̙̘̟̫͌͆͂̅̏̕l̶̹̺͈̍̄̆̒͂͊̂̓́͝ ̵̡̧̮̯̬̙̖̖̅̍̌̌̎̈͌̾́̆̉̑̆͜͝ḧ̷̨̡͎̬̤̠͍̼̳̪̪̖̑̽̀̇̓͗͌̄̿̓̔̊̚ę̸̧͙̪̣̭͎̰̖̣̽̂̎̒͒͂̍͠r̵̢̡͕̙͖͍̭͖̪͍̹̹̱̫͌̀͛͛̈́̉͌͑́̈́͘͘ę̴̧̰̙̰̬͙̲̥̮̣͎́̊̌̏͗.̵͔̣͎̰͈̩̹̘̙̩͚̫͎͙̎̋͛͗̽̐̊́͒̔͝
̵̮͇͓̗̥͘ͅĮ̸͚̘͔̲͎̝͙̔̆́̎͊̈́̇͐͜ ̷̩̯̣͖̦̤͇̪̳̱͕̼̈͒͜w̷̨̳̥̱̹͔͖̜̹̝̥̅̀͒̐̊̽͗͂̏́͗̚ȉ̷̝́̍̐̉̏͊̃̂̋͘̕̚͝l̵͎͇̺̝̄̈́̾̌͌̓͆̇͘l̷̛̛͕͕̺̩̦͈̼͎̲̪̖̖̖͗̈́̐͂̿̾̿̀̽̊̿̔͝ ̴̛̛̙̳̬͖̬̭̬̳͖͋̓̽̾̈́̂͌͝͝p̴̙̥̩̆̆ų̴̞͔̩͙͚̳̥̙̫̅̈́͊̈̕ṭ̸̖̪̝̬̯͈͖̙͐̃̀́̄͝ ̷̝̼̥̰̖́̆̓͒̓̇͂̈̓̾̕͘͝͝ͅy̶̺͚̬̣̦͉̞̤͊ō̷̡̢̭͇̥̱̮̈́͊̐̽̿̿͆͐̉̏̃͂̃̚ṵ̴̡̡͔̟͍̭̼̺̯̠̐̎̾͒̈́̆͛͐͘͝ ̶̛̱̣͈̝͈̲͇̟̦̆̅̿͑͌͌͑̃b̷̧̩͉̰͓̃̐̈̊̌̋̍̀̃̀̋a̵̧̞̺̘͈̲̭͂͆͗̀̉̑̓̿̎̑̈́c̷̡̢̱̯̟̩͓͎̼̜̖̤͍̫̐͒́̏͆̈́̐͐͊̕̕̚̕k̴̡̺͔̹̰̹̙͔̇̈́́͒̏̅̂̈́͒̒̊̿͜͠͠ ̵̫̪̤̈́͌͒̾͘t̵̓̒́͐̑̓̕͘͘ͅǫ̶̧͎͎̫̰̱͍̮̰͈̟̆̒͒̔̄ͅğ̵̢̨̠̝̝̬̖̳̦̺͈̰̫̞̰̍̌̔̀͂̕͝e̵̙̤͕͉̓̒̑̀̓̐͗̚ţ̵͇̟̫͇̪̝̤̣̬̲̭̏͆̑́̿̑͒͌́͆ͅh̶̡͍͕̟̙̫̭̮̭̐̏͆͑͋̎̚͘ẹ̶̡̢̡͖͎͔̠̬̩̣͕̫̝͛̐̅̚ͅr̸̟͎̺̗̮̙̰͍̟̞͎̻̲̒͗̐̇̿̆̋͋̆̂̇.̶̡̥̣̮̻̳̱̹̠̦͎̤̊̕ͅ""",
                            "Í̸̡͙̝̩̘̮̘̖͈̖̪͕̯̣͂͐̓̓̅͒͌͂͘͜͝ ̷̡͓͕̰̦̹̪̜̗͙̥̻͐̈́̐̀͋̎͌̿̑̑̊͜͝w̸̧̳͇̭̻̗͖͖̲̰͂̏͆̀͋́̀a̷̢̞̮̟̝̝̼̼̎́͋̏̀͌̓̕̕s̶̪̫͍̞̓̀̑ ̸͈̫͇̟͖͎̼̾̇̿͛͋̋t̷̹̓̔͒̐͊̂̋̀̒́̇̕̚͝h̴̥̓ē̶̢̮̼̗̬͚͇͔̣͎͉̹͉̈͆̾͊̌ͅ ̵̨̧̡̦͔̬̠̞̹̪̩̞͕͔̞̿̀͛͊̈́̅̑͊́̇͠f̷͖́̅̌̐̀̓̉͊̕i̴̪͇̯͓̟̞̫̿͌́r̶̛̰̪͖͖͔̤̺̒̃̆͘ṣ̶̻͎͉̌̍̆͒́̀̈́̕͠t̵̰̻̠̣̹̟̖̖̬͒̾̒̀̊͘͘̚͠,̶̛̫̥͚͗̈́̒͋̊̑̅͗̓̇́̚̕ ̸̯͉̿́̐̓I̶̱̲̓ ̴̨̭̰̠͓̞̭͈̦̦͔̲͈͉̽͂̃͋̃̉͋̇͌͘̕ͅȟ̵̢̺̘̲͉̯͙̺̲̺̪͚̩̟̫̊̓̃͌̈́͂̕̕͠ả̵̖̠̬͍̣̲͔̜͖͖̹̩̙͇̊̔̋̆̿͐v̶̨̻͎̘̿̄́͒͐̑̌ḛ̵̘̙̤̩̪̙͂̍̎́̏͑̑̾̐͘̚ ̴̰̙̭͊̀š̵͕͛̇͋͑͑͑̇̑̅̌͘͝ȇ̸̡̢͔̯̻̣̰͇̫̩͇͈̩̰̃̈́̿̉̈́͜é̵̗̖͙̗̬͇n̶̡̢̦̯̬̼̖̖̰͉̱̆̐ ̷͚̜̽̉̿̆̽̿͗̓́̍̾̃e̷̝͈̭̜͋̀́͋̎͗̒͗̌͒̏̚͝v̷̨̤̻̲̩̫̲͎͍̮̟̪͑̉͋̊̿͋̎͜e̴̡̛̝̺͕̫̖̝̊̾͗̐̌̆̓̓͌̄̍͘r̷̨͐͗̊̽͂̒̔͆̿̀̀͘͝͝ỹ̵ͅt̴̛̛͎̯̩͕̾̈͑̊͑͐̕̚͜͝͝ḩ̶̠̍̒̍͌̐̇̈́͋͛̇̽̐͠͝í̴̧̘̭̞̦̠͉̪̍͛́͂͋̑͆̔͗̍͘͜͝ň̷̘͇̪͍͎̱̪̔̓̈̆g̴̟̣͔͈͓͙̲͎̲̏̐̃̕ͅͅ",
                            "Į̸̡̙̯̼̳̬̦̲̞̣̉͑́̉̆̇͂͋̏̽t̶̯͚̑͜ͅͅ'̸̰̱̭͈̺̭̘͚͍̟̔̏͆̆̓̿͆̋͋̒̈́̐̕s̸͓͑̌̀̇͗́́͑̆̍̇̓͠͝͠ ̶̨̰͈̯̺̜͚̤͆͐͊̑̎̒̿͑͗͘͜m̸̱̖̻̹͓̪͉̃̍̔̂ę̶͓̳͖̱̓̃̿͝ͅ"
                    };

                    String[] solutions = new String[]{
                            "A keyboard",
                            "Darkness",
                            "Time",
                            "A poltergeist",
                            "A portal",
                            "A mirror",
                            "A chest",
                            "A ghost",
                            "A spider",
                            "An enigma",
                            "A ghost",
                            "An owl",
                            "A mirror",
                            "A tombstone",
                            "A shadow",
                            "A maze",
                            "Death",
                            "A medium",
                            "A haunting melody",
                            "A hidden curse",
                            "I should be dead, but I am not.",
                            "All of them - together in one place.",
                            "It will be me."
                    };
                    Random rand = new Random();
                    int choice = rand.nextInt(23);
                    if (choice>19) System.out.println("Silver Eyes gaze upon you...");
                    System.out.println("A whirlwind of souls surround you as you approach the centre of the room...\nVoices emerge from seemingly everywhere...");
                    System.out.println(riddles[choice]);
                    Scanner input = new Scanner(System.in);
                    if (!input.next().equals(solutions[choice])) System.out.println("The voices whisper amongst themselves, before you find yourself violently expelled from the room...");
                    else if (choice>19) {
                        System.out.println("""
                                A haunting wail echoes through the catacombs, as if all of the souls buried in the ancient structures were reawakened...
                                You look around you, as a blue light fills the cracks and seams of the walls...
                                You rest, as your phone appears, and a metallic limb reaches through the screen, piercing your abdomen and releasing your innards...
                                """);
                        Scanner stall = new Scanner(System.in);
                        stall.nextLine();
                        System.out.printf("""
                                "Father, it's me, %s. I did it. I found it.
                                It was right where you said it would be. They were all there.
                                They didn't recognise me at first, but then, they thought I was you.
                                And I found them. I put them back together, just like you asked me to.
                                They are free but something is wrong with me...
                                I should be dead, but I'm not.
                                I've been living in the shadows There is only one thing left for me to do now.
                                I'm going to find you father...
                                I'm going to come find you...\"""",Main.name);
                        Main.saveGame();
                        System.exit(0);
                    }
                    else return generateKey();
                }
                case "Word Jumble" -> {
                    String original = getString();
                    System.out.println("A pedestal with a piece of parchment resting upon it lies in the centre of the dimly lit room...\n" +
                            "As you approach it, letters magically appear on the parchment: " + getShuffledWord(original));
                    System.out.println("You realise that the room wants you to unscramble the word...");
                    Scanner input = new Scanner(System.in);
                    if (!input.next().equals(original)) System.out.println("The parchment erases itself... You leave the room disappointed...");
                    else return generateKey();
                }
            }
        }
        return -1;
    }

    @NotNull
    private static String getString() {
        String[] words = new String[]{
                "Father",
                "Legacy",
                "Murder",
                "Soul",
                "Haunt",
                "Guilt",
                "Memory",
                "Agony",
                "Admission",
                "Children"
        };
        Random choice = new Random();
        return words[choice.nextInt(10)];
    }

    private int generateKey() {
        Random key = new Random();
        int newKey = key.nextInt(10000);
        System.out.println("You find a small piece of parchment that reads: " + newKey);
        return newKey;
    }

    private String getShuffledWord(String original) {
        String shuffledWord = original; // start with original
        int wordSize = original.length();
        int shuffleCount = 10; // let us randomly shuffle letters 10 times
        for(int i=0;i<shuffleCount;i++) {
            //swap letters in two indexes
            int position1 = ThreadLocalRandom.current().nextInt(0, wordSize);
            int position2 = ThreadLocalRandom.current().nextInt(0, wordSize);
            shuffledWord = swapCharacters(shuffledWord,position1,position2);
        }
        return shuffledWord;
    }
    private String swapCharacters(String shuffledWord, int position1, int position2) {
        char[] charArray = shuffledWord.toCharArray();
        // Replace with a "swap" function, if desired:
        char temp = charArray[position1];
        charArray[position1] = charArray[position2];
        charArray[position2] = temp;
        return new String(charArray);
    }

}
