package hw2;

public class PercolationFactory {
    public Percolation make(int N) {
        return new Percolation(N);
    }
}
