package grid;


public class Obstacle implements GridComponent {
    private int x;
    private int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Obstacle)) return false;
        Obstacle obstacle = (Obstacle) obj;
        return x == obstacle.x && y == obstacle.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y; // Simple hash code based on coordinates
    }

    @Override
    public boolean isObstacle(int x, int y) {
        return this.x == x && this.y == y; // Check if the given coordinates match this obstacle's coordinates
    }

    @Override
    public void addObstacle(GridComponent obstacle) {
        throw new UnsupportedOperationException("Cannot add an obstacle to a single obstacle.");
    }



}
