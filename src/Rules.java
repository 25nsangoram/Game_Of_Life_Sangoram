public abstract class Rules {//This class is responsible for determining a cell’s state in the next generation (following evolution), which is a function of its current state and the state of its surrounding neighbors.
    public abstract boolean shouldBeBorn(int liveNeighbors);
    public abstract boolean shouldSurvive(int liveNeighbors);

    public CellState applyRules(CellState cellState, int liveNeighbors) {
        if (cellState == CellState.DEAD && shouldBeBorn(liveNeighbors) == true){
            return CellState.WILL_REVIVE;
        } else if (cellState == CellState.ALIVE && shouldSurvive(liveNeighbors) == false){
            return CellState.WILL_DIE;
        } else {
            return cellState;
        }
    }
}

