public class Match {
    private BitSequence sequence;
    private Character symbol;
    
    public Match(BitSequence sequence, Character symbol) {
        this.sequence = sequence;
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return this.symbol;
    }

    public BitSequence getSequence() {
        return this.sequence;
    }
}
