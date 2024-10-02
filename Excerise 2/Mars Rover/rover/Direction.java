package rover;

public enum Direction {
    N("North"), E("East"), S("South"), W("West");

    private final String fullName;

    Direction(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }
}
