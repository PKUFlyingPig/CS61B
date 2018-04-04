package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class AStarDemo {
    /* Runs an A* from (1, 1) to (N, N) on the graph in the config file. */

    public static void main(String[] args) {
        Maze maze = new Maze("lab11/graphs/maze.config");

        int startX = 1;
        int startY = 1;
        int targetX = maze.N();
        int targetY = maze.N();

        MazeExplorer asp = new MazeAStarPath(maze, startX, startY, targetX, targetY);
        asp.solve();
    }

}
