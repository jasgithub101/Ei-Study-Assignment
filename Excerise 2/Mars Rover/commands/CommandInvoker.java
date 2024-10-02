package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import rover.Rover;

public class CommandInvoker {
    private static final Logger logger = Logger.getLogger(CommandInvoker.class.getName());
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
