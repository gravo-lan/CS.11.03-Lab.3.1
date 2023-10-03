import java.util.Random;
import java.util.*;

public class Combat {
    private long playerHP, enemyHP;
    private final String enemy;
    private boolean isBattleOver;
    private boolean blocked;

    public Combat(long startingHP, String enemyType) {
        playerHP = startingHP;
        if (!enemyType.equals("random")) enemy = enemyType;
        else {
            Random rand = new Random();
            String[] enemyList = new String[]{
                    "Echo Bat", //Double attack incoming and outgoing, same as howling bat
                    "Stone Guardian", //50% dmg absorption rate, High HP
                    "Undead Warrior", //High HP, Low ATK
                    "Dark Hound", //High CC, High CD, Low ATK
                    "Howling Bat", //Low HP, Low ATK, High CC, High CD
                    "Lost Traveller", //Low HP, High ATK
                    "Wandering Soul" //Low HP, High ATK
            };
            enemy = enemyList[rand.nextInt(6)];
            enemyHP = (int) (10 + 0.5*((int) Main.level * (int) Main.level));
            switch (enemy) {
                case "Wandering Soul", "Lost Traveller" -> enemyHP /= 3;
                case "Echo Bat", "Howling Bat" -> enemyHP /= 2;
                case "Stone Guardian", "Undead Warrior" -> enemyHP *= 2;
            }
        }
        isBattleOver = false;
    }

    public void startCombat() throws Exception {
        System.out.println("A hostile " + enemy + " appears!");

        // Combat loop
        while (playerHP > 0 && !isBattleOver) {
            System.out.println(Main.name + " HP: " + playerHP);
            System.out.println("Enemy HP: " + enemyHP);
            System.out.println("Choose an action:\n[1] Attack\n[2] Defend\n[3] Escape");

            int choice = getPlayerChoice();

            switch (choice) {
                case 1 -> attackEnemy();
                case 2 -> defend();
                case 3 -> {
                    if (tryToEscape()) {
                        System.out.println("You successfully escaped from the battle!");
                        return;
                    } else {
                        System.out.println("Escape attempt failed. The battle continues.");
                    }
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            // Simulate enemy's turn (You'll need to implement this separately)
            enemyTurn();
            blocked = false;
        }

        // Player has lost the battle
        if (!isBattleOver) System.out.println("You have been defeated in battle!");
    }

    private int getPlayerChoice() {
        // Get user input for their choice (1, 2, or 3) and return it
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void attackEnemy() throws Exception {
        long dmg = 1;
        if (Main.weapon.equals("Stick")) dmg++;
        System.out.println(Main.name + " attacks the " + enemy + "!");
        Random crit = new Random();
        if (crit.nextInt(99)>89) {
            dmg*=2;
            System.out.println("Critical hit!");
        }
        enemyHP-=dmg;
        if (enemy.equals("Echo Bat")) {
            System.out.println("Echo Bat amplifies your attack...");
            enemyHP-=dmg;
        }
        if (enemy.equals("Stone Guardian") && crit.nextInt(2)==0) {
            System.out.println("Stone Guardian absorbs the damage...");
            enemyHP+=dmg;
        }
        if (enemyHP<=0) {
            System.out.println("You have defeated the " + enemy + "!");
            Main.hp = playerHP;
            Main.xp+=5*(int) (100 + 0.5*((int) Main.level * (int) Main.level));
            Main.levelUp();
            isBattleOver = true;
        }
    }

    private void defend() {
        blocked=true;
        playerHP++;
        System.out.println(Main.name + " defends against the enemy's attack!");
    }

    private boolean tryToEscape() {
        Random rand = new Random();
        double hpChance = (double) playerHP/100;
        double chance = (double) rand.nextInt(1,1001) * hpChance;
        return chance < 200;
    }

    private void enemyTurn() {
        if (!isBattleOver){
            Random enemyA = new Random();
            int chance = enemyA.nextInt(100);
            if (chance == 99) {
                System.out.println("The " + enemy + " wanders away...");
                isBattleOver = true;
            } else if (chance > 89) {
                System.out.println("The " + enemy + " lets out a haunting wail, but otherwise does nothing...");
            } else {
                long dmg = 3;
                if (enemy.equals("Lost Traveller") || enemy.equals("Wandering Soul")) dmg *= 1.5;
                else if (!enemy.equals("Stone Guardian")) dmg /= 1.5;
                System.out.println(enemy + " attacks!");
                Random crit = new Random();
                boolean ihatecodingcombat = (enemy.equals("Echo Bat") || enemy.equals("Howling Bat")) || enemy.equals("Dark Hound");
                if (crit.nextInt(99) > 89 || (ihatecodingcombat) && crit.nextInt(99) > 79) {
                    dmg *= 2;
                    if (ihatecodingcombat) {
                        dmg *= 3.0 / 2;
                    }
                    System.out.println("Critical hit!");
                    if (blocked) {
                        System.out.println("Critical hit pierces your defences!");
                        blocked = false;
                    }
                }
                if (!blocked) playerHP -= dmg;
                if (enemy.equals("Echo Bat")) {
                    System.out.println("Echo Bat amplifies its attack...");
                    playerHP -= dmg;
                }
            }
        }
    }
}
