package grid;

public interface GridComponent {
    boolean isObstacle(int x, int y);
    void addObstacle(GridComponent obstacle);
    int getX();
    int getY();

    
}
