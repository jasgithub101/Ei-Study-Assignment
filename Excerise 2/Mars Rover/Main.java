import commands.*;
import grid.*;
import rover.*;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input Grid Dimensions        
        int width = getPositiveIntegerInput(scanner, "Enter grid width: ");
        int height = getPositiveIntegerInput(scanner, "Enter grid height: ");
        

        // Initialize Grid
        Grid grid = new Grid(width, height);


        // Input Obstacles
        int numObstacles = getPositiveIntegerInput(scanner, "Enter the number of obstacles: ");
        for (int i = 0; i < numObstacles; i++) {
            int ox = getCoordinateInput(scanner, width, "Enter obstacle " + (i + 1) + " x-coordinate: ");
            int oy = getCoordinateInput(scanner, height, "Enter obstacle " + (i + 1) + " y-coordinate: ");
            grid.addObstacle(new Obstacle(ox, oy));
        }

        // Get starting coordinates with obstacle check
        int[] startingCoordinates = getStartingCoordinates(scanner, width, height, grid);
        int startX = startingCoordinates[0];
        int startY = startingCoordinates[1];
        Direction startDirection = getStartingDirection(scanner);

        // Initialize Rover
        Rover rover = new Rover(startX, startY, startDirection, grid);

        // mode selection
        int mode;
        do {
            System.out.println("Enter 1 for Normal mode or 2 for Interactive mode:");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next(); // Clear invalid input
            }
            mode = scanner.nextInt();
        } while (mode != 1 && mode != 2);

        // Input Commands
        if(mode == 1){
        int numCommands = getNumberOfCommands(scanner);
        getCommands(scanner, numCommands);


        // Execute Commands
        CommandInvoker.executeCommands(rover);

        // Get Status Report
        StatusReport statusReport = rover.getStatusReport();
        System.out.println(statusReport);

        displayEncounteredObstacles(rover);
        }
        else if(mode == 2){
            startInteractiveMode(rover);
        }

        scanner.close();
    }


    private static int getPositiveIntegerInput(Scanner scanner, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Error: Value must be a positive integer. Please try again.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
                value = -1; // Reset value to ensure loop continues
            }
        } while (value < 0);
        return value;
    }
    

    private static int getCoordinateInput(Scanner scanner, int max, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value < 0 || value >= max) {
                    System.out.println("Error: Value must be between 0 and " + (max - 1) + ". Please try again.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
                value = -1; // Ensure loop continues
            }
        } while (value < 0 || value >= max);
        return value;
    }

    private static int[] getStartingCoordinates(Scanner scanner, int width, int height, Grid grid) {
        int startX, startY;

        do {
            startX = getCoordinateInput(scanner, width, "Enter starting x-coordinate: ");
            startY = getCoordinateInput(scanner, height, "Enter starting y-coordinate: ");

            if (grid.isObstacle(startX, startY)) {
                System.out.println("Error: Starting coordinates coincide with an existing obstacle.");
            }
        } while (grid.isObstacle(startX, startY));

        return new int[]{startX, startY};
    }

    
    

    private static Direction getStartingDirection(Scanner scanner) {
        Direction startDirection = null;
        while (startDirection == null) {
            try {
                System.out.print("Enter starting direction (N/E/S/W): ");
                startDirection = Direction.valueOf(scanner.next().toUpperCase()); 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid direction. Please enter N, E, S, or W.");
            }
        }
        return startDirection;
    }

    private static int getNumberOfCommands(Scanner scanner) {
        int numCommands;
        System.out.print("Enter the number of commands: ");
        numCommands = scanner.nextInt();
        
        while (numCommands < 0) {
            System.out.println("Error: Number of commands cannot be negative. Please try again.");
            System.out.print("Enter number of commands: ");
            numCommands = scanner.nextInt();
        }
        
        return numCommands;
    }

     // Function to get the list of commands from the user
     private static void getCommands(Scanner scanner, int numCommands) {
        System.out.println("Enter commands (M for move, L for turn left, R for turn right):");
        CommandInvoker commandInvoker = new CommandInvoker();
        for (int i = 0; i < numCommands; i++) {
            char cmd = scanner.next().toUpperCase().charAt(0);
            switch (cmd) {
                case 'M':
                    commandInvoker.addCommand(new MoveCommand());
                    break;
                case 'L':
                    commandInvoker.addCommand(new TurnLeftCommand());
                    break;
                case 'R':
                    commandInvoker.addCommand(new TurnRightCommand());
                    break;
                default:
                    System.out.println("Invalid command: " + cmd);
                    i--; // Decrement i to repeat the command entry
                    break;
            }
        }
        
    }


    public static void displayEncounteredObstacles(Rover rover) {
        Set<int[]> encounteredObstacles = rover.getEncounteredObstacles();
        if (!encounteredObstacles.isEmpty()) {
            System.out.println("Rover encountered obstacle(s) at:");
            for (int[] obstacle : encounteredObstacles) {
                System.out.printf("(%d, %d)%n", obstacle[0], obstacle[1]);
            }
        } else {
            System.out.println("Rover did not encounter any obstacles.");
        }
    }

    public static void startInteractiveMode(Rover rover) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("Enter a command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): ");
            char cmd = scanner.next().toUpperCase().charAt(0);
    
            switch (cmd) {
                case 'M':
                    new MoveCommand().execute(rover);
                    break;
                case 'L':
                    new TurnLeftCommand().execute(rover);
                    break;
                case 'R':
                    new TurnRightCommand().execute(rover);
                    break;
                case 'Q':
                    running = false;
                    System.out.println("Exiting interactive mode.");
                    continue;
                default:        
                    System.out.println("Invalid command. Please enter M, L, R, or Q.");
                    continue;
            }
    
            System.out.println(rover.getStatusReport());
        }
        scanner.close();
    }
    
    // private static void displayEncounteredObstacles(Set<int[]> encounteredObstacles) {
    //     if (!encounteredObstacles.isEmpty()) {
    //         System.out.println("Rover encountered obstacles at:");
    //         for (int[] obstacle : encounteredObstacles) {
    //             System.out.printf("(%d, %d)%n", obstacle[0], obstacle[1]);
    //         }
    //     } else {
    //         System.out.println("Rover did not encounter any obstacles.");
    //     }
    // }

    
}
