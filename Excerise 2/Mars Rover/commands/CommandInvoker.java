package commands;

import java.util.ArrayList;
import java.util.List;
import rover.Rover;
import utils.*;
public class CommandInvoker {
    static Logger logger = Logger.getInstance();
    private static List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        logger.info("Adding command to the list.");
        commands.add(command);
    }

    public static void executeCommands(Rover rover) {
        logger.info("Executing commands.");
        for (Command command : commands) {
            command.execute(rover);
        }
    }
}
