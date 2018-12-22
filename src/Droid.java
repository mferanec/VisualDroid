import java.util.Scanner;

public class Droid {

    static int battery;
    static int commandNo;
    int positionX = 0;
    int positionY = 0;
    static Boolean winCondition = false;
    static Boolean hitMine = false;

    public Droid(int batteryLevel) {
        battery = batteryLevel;
    }

    public void startup() {
        System.out.println("Hi and welcome to Droid! You are in control of a small robot which can hover and move.");
        System.out.println("Its battery life is limited and you'll need to recharge them. You cannot recharge while the robot is hovering.");
        System.out.println("The goal is to move into the opposite corner of a 10 by 10 gameboard. Be careful though, there might be mines on the way!");
        System.out.println("Droid starting up...");
        battery = battery - 5;
    }

    public void awaitingCommand() {
        System.out.println("Battery level is " + battery);
        System.out.println("Please enter command(hover, move, land, recharge)");
    }

    public void command(String commandString) {
        commandString = commandString.toLowerCase();
        if (commandString.equals("hover")) {
            commandNo = 1;
        } else if (commandString.equals("move")){
            commandNo = 2;
        } else if (commandString.equals("land")){
            commandNo = 3;
        } else if (commandString.equals("recharge")){
            commandNo = 4;
        }
    }

    public void hover(int hoverHeight) {
        if (hoverHeight > 3) {
            System.out.println("Cannot hover that high!");
        } else if (hoverHeight <= 0) {
            System.out.println("Invalid hover height!");
        } else {
            System.out.println("Hovering...");
            battery = battery - (hoverHeight * 10);
        }
    }

    public void location() {
        System.out.println("Your current location is: " + positionX + " " + positionY);
    }

    public void move(int moveX, int moveY) {
        if (moveX > 2 || moveX < -2 || moveY > 2 || moveY < -2) {
            System.out.println("You can only move maximum 2 by 2 spaces at a time.");
        } else {
            if (positionX + moveX < 0 || positionY + moveY < 0) {
                System.out.println("Negative axis is out of bounds.");
            } else if (positionX + moveX > 10 || positionY + moveY > 10) {
                System.out.println("Those coordinates are out of bounds.");
            } else {
                positionX = positionX + moveX;
                positionY = positionY + moveY;
                if (moveX == 0) { moveX = 1; }
                if (moveY == 0) { moveY = 1; }
                battery = battery - (moveX * moveY * 2);
                if (positionX == 10 && positionY == 10) { winCondition = true; }
            }
        }
    }

    public void recharge() {
        System.out.println("Droid recharging...");
        battery = 100;
    }

    public static void main(String[] args) {

        Boolean hoverBoolean = false;
        Droid advancedDroid = new Droid(100);
        Scanner user_input = new Scanner(System.in);

        advancedDroid.startup();

        while (battery > 0 && winCondition == false && hitMine == false) {
            advancedDroid.awaitingCommand();
            advancedDroid.command(user_input.next());

            switch (commandNo) {
                case 1:
                    System.out.println("Please select hover height:");
                    advancedDroid.hover(user_input.nextInt());
                    hoverBoolean = true;
                    break;
                case 2:
                    if (hoverBoolean == true) {
                        advancedDroid.location();
                        System.out.println("Select X + Y coordinates separated by space:");
                        advancedDroid.move(user_input.nextInt(), user_input.nextInt());
                    } else {
                        System.out.println("You need to hover to move!");
                    }
                    break;
                case 3:
                    System.out.println("Landing...");
                    hoverBoolean = false;
                    break;
                case 4:
                    if (hoverBoolean == false) {
                        advancedDroid.recharge();
                    } else {
                        System.out.println("You need to land to recharge.");
                    }
            }
        }
        if (battery <= 0) {
            System.out.println("You ran out of battery!");
            System.out.println("GAME OVER");
        } else if (hitMine == true) {
            System.out.println("You hit a mine!");
            System.out.println("GAME OVER");
        } else {
            advancedDroid.location();
            System.out.println("Congratulations! You won!");
        }
    }
}


