package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class TrivialMazeExplorer extends MazeExplorer {
    public TrivialMazeExplorer(Maze maze) {
        super(maze);
    }

    @Override
    /* Walks the entire maze, ignoring walls. */
    public void solve() {
        for (int i = 0; i < maze.V(); i += 1) {
            distTo[i] = i;
            marked[i] = true;
            announce();
        }
    }
}

