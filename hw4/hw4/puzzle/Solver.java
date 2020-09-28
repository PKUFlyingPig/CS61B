package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode pre;
        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            moves = m;
            pre = p;;
        }

        public WorldState getState() {
            return state;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPre() {
            return pre;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.moves + this.state.estimatedDistanceToGoal() - o.moves - o.state.estimatedDistanceToGoal();
        }
    }

    private MinPQ<SearchNode> openNodes = new MinPQ<>();
    private List<WorldState> bestSolution;
    private int totMoves;

    /** After solving the puzzle, cache the answer for later use. */
    private void getAnswer(SearchNode goal) {
        totMoves = goal.moves;
        bestSolution = new ArrayList<>();
        SearchNode p = goal;
        while (p != null) {
            bestSolution.add(p.state);
            p = p.pre;
        }
    }

    /** Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        openNodes.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode node = openNodes.delMin();
            if (node.state.isGoal()) {
                getAnswer(node);
                return;
            } else {
                for (WorldState neighbour : node.state.neighbors()) {
                    if (node.pre == null || !neighbour.equals(node.pre.state)) {
                        openNodes.insert(new SearchNode(neighbour, node.moves + 1, node));
                    }
                }
            }
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves() {
        return totMoves;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        List<WorldState> ret = new ArrayList<>();
        for (int i = totMoves; i >= 0; i--) {
            ret.add(bestSolution.get(i));
        }
        return ret;
    }
}
