package rover;
import utils.*;
public class StatusReport {
    Logger logger = Logger.getInstance();    
    private int x;
    private int y;
    private Direction direction;

    public StatusReport(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public String toString() {
        logger.info("Generating status report.");
        return "Rover is at (" + x + ", " + y + ") facing " + direction;
    }
}
