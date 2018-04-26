package huglife;

public class Position {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Position that = (Position) other;
        return this.x == that.x && this.y == that.y;
    }

    public String toString() {
        return String.format("x: %d, y: %d", x, y);
    }
}