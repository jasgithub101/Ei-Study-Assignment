package grid;
import java.util.HashSet;
import java.util.Set;
import utils.*;

public class Grid implements GridComponent {
    Logger logger = Logger.getInstance();    
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
            logger.warn("Coordinates (" + x + ", " + y + ") are out of bounds");
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
