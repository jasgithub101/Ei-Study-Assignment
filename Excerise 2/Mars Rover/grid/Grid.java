package grid;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Set;

public class Grid implements GridComponent {
    private static final Logger logger = Logger.getLogger(Grid.class.getName());
    private int width;
    private int height;
    private Set<GridComponent> obstacles;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;   
        this.obstacles = new HashSet<>();
        logger.info("Grid created with dimensions " + width + "x" + height);
    }

    @Override
    public void addObstacle(GridComponent obstacle) {
        obstacles.add(obstacle); 
        logger.info("Obstacle added at (" + obstacle.getX() + ", " + obstacle.getY() + ")");
    }


    @Override
    public boolean isObstacle(int x, int y) {
        return obstacles.contains(new Obstacle(x, y));
    }

    public boolean isWithinBounds(int x, int y) {
        boolean result = x >= 0 && x < width && y >= 0 && y < height;
        if(!result) {
            logger.severe("Coordinates (" + x + ", " + y + ") are out of bounds");
        }
        return result;

    }
    public Set<GridComponent> getObstacles() {
        return obstacles;
    }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Unimplemented method 'getX'");
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Unimplemented method 'getY'");
    }
}
