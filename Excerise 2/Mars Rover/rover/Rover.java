package rover;

import grid.Grid;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Set;

public class Rover {
    private static final Logger logger = Logger.getLogger(Grid.class.getName());

    private int x;
    private int y;
    private Direction direction;
    private Grid grid;
    private Set<int[]> encounteredObstacles;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
        this.encounteredObstacles = new HashSet<>();
    }

    public void move() {
        int newX = x;
        int newY = y;
        switch (direction) {
            case N : newY += 1; break;
            case E : newX += 1; break;
            case S : newY -= 1; break;
            case W : newX -= 1; break;
        }
        if (grid.isWithinBounds(newX, newY) && !grid.isObstacle(newX, newY)) {
            x = newX;
            y = newY;
            logger.info("Rover moved forward.");
        } else if (grid.isObstacle(newX, newY)) {
            encounteredObstacles.add(new int[] { newX, newY });
        }
    }

    public void turnLeft() {
        logger.info("Rover turned left.");
        direction = direction.turnLeft();
    }

    public void turnRight() {
        logger.info("Rover turned right.");
        direction = direction.turnRight();
    }

    public StatusReport getStatusReport() {
        return new StatusReport(x, y, direction);
    }

    public Set<int[]> getEncounteredObstacles() {
        return encounteredObstacles;
    }
}
