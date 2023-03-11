/**the cell class creates a new cell object with a position in the grid, a cellState, and rules
 *
 */
public class Cell {
    private CellState cellState;
    private int x;
    private int y;
    private int size;
    private int row;
    private int column;

   // private int fill;
    private Rules rules;

    /** this is cell's constructor
     *
     * @param hello
     * @param y
     * @param size
     * @param row
     * @param column
     * @param cellState
     * @param rules
     */
    public Cell(int hello, int y, int size, int row, int column, CellState cellState, Rules rules){//pass it MooreRules
        super();
        this.x=hello;
        this.y=y;
        this.size=size;
        this.row=row;
        this.column=column;
        this.rules=rules;
        this.cellState=cellState;


    }

    /** this void method tests if a cell object's cellState is ALIVE. If the cell is alive, the cell square will be filled with black.
     * If the cell is dead, the cell square will be displayed as white
     *
     */
    public void display() {
        if (cellState == CellState.ALIVE){
            Main.app.fill(100,2,3);
        }else{
            Main.app.fill(255);
        }
            //Main.app.fill(fill);
            Main.app.rect(x,y,size,size);

    }

    /**the handleClick method toggles the cellState of the cell that the mouse has clicked on. If the cell is ALIVE, then the cellState will flip to dead
     * (black to white). If the cell is DEAD, then the cellState will flip to ALIVE (White to black)
     *
     */
    public void handleClick(){
        if(cellState == CellState.ALIVE){
            //fill = 0;
            cellState = CellState.DEAD;
        }
        else{
            //fill = 255;
            cellState = CellState.ALIVE;
        }
    }

    /** the applyRules method takes the 2D array of cells (the grid, essentially) assigns the number of live neighbors
     * around a given cell
     * ASK
     *
     * @param cells
     */
    public void applyRules(Cell[][] cells){
        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
    }

    /**countLiveNeighbors keeps a count of the number of cells that are ALIVE or WILL_DIE which both show up as black squares
     * on the screen. In other words, the method counts all the black squares around a given cell. The if statements start at the
     * upmost leftmost cell and moves down to the lower rightmost cell.
     *
     * @param cells
     * @return
     */
    public int countLiveNeighbors(Cell[][]cells){
        int count = 0;
        if(cells[row-1][column-1].cellState == CellState.ALIVE || cells[row-1][column-1].cellState == CellState.WILL_DIE){
            count++;
        }
        if(cells[row-1][column].cellState == CellState.ALIVE || cells[row-1][column].cellState == CellState.WILL_DIE){
            count++;
        }
        if(cells[row-1][column+1].cellState == CellState.ALIVE || cells[row-1][column+1].cellState == CellState.WILL_DIE){
            count++;
        }
        if(cells[row][column-1].cellState == CellState.ALIVE || cells[row][column-1].cellState == CellState.WILL_DIE){
            count++;
        }
        if(cells[row][column+1].cellState == CellState.ALIVE || cells[row][column+1].cellState == CellState.WILL_DIE){
            count++;
        }
        if(cells[row+1][column-1].cellState == CellState.ALIVE || cells[row+1][column-1].cellState == CellState.WILL_DIE){
            count++;
        }
        if(cells[row+1][column].cellState == CellState.ALIVE || cells[row+1][column].cellState == CellState.WILL_DIE){
            count++;
        }
        if(cells[row+1][column+1].cellState == CellState.ALIVE || cells[row+1][column+1].cellState == CellState.WILL_DIE){
            count++;
        }
        return count;
    }

    /** the evolve method checks if the cellState of a cell is WILL_REVIVE or WILL_DIE and changes WILL_REVIVE to ALIVE
     * and WILL_DIE changes to dead.
     *
     */

    public void evolve(){
        if (cellState == CellState.WILL_REVIVE){
            cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        }
    }


}
