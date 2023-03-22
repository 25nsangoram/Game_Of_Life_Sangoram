import processing.core.PApplet;
public class Main extends PApplet {
    private static final int CELL_SIZE=20;//will never be anything other than 10 and is type int
    public static Main app;//example: main.app.fill
    private Cell[][]cells;//creating a 2D array

    private boolean doEvolve;
    public static void main(String[] args){
        PApplet.main("Main");
    }
    public Main() {//Main's constructor
        super();
        app = this;
    }
    public void settings(){
        size(600,500);
    }
    public void setup(){

        // create a nested loop to iterate over cells by row and column
        //create an instance of a Cell, and add to the array
        Rules rules = new MooreRules(new int[]{3}, new int[]{2, 3});
        cells=new Cell[height/CELL_SIZE][width/CELL_SIZE];// create a 2D array
        frameRate(10);
        CellState cellStates = CellState.DEAD;
        for(int row=0;row<cells.length; row++){
            for(int col=0;col<cells[0].length;col++){
                double i = Math.random();
                if(row==0||col==0||row==cells.length-1||col==cells[0].length-1) {
                    cellStates = CellState.DEAD;
                }else if(i<0.5){
                    cellStates=CellState.DEAD;
                }else{
                    cellStates=CellState.ALIVE;
                    }
                Cell c = new Cell(col*CELL_SIZE,row*CELL_SIZE,CELL_SIZE,row,col,cellStates,rules);//EXTENSION
                cells[row][col]=c;


            }
        }
        /**

        for(int row=0;row<cells.length; row++){
            for(int col=0;col<cells[0].length;col++){
                Cell c = new Cell(col*CELL_SIZE,row*CELL_SIZE,CELL_SIZE,row,col,CellState.ALIVE,rules);//??
                cells[row][col]=c;

            }
        }
         *
         */

    }
    public void draw(){
        if(doEvolve == true){
            applyRules();
            evolve();
        }
        for(int row=0;row<cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                cells[row][col].display();
            }
        }
    }
    public void mouseClicked(){
        int colX = mouseX/CELL_SIZE;//will return an integer that shows what row and col the mouse clicked in. eg. 15/10 is 1
        int rowY = mouseY/CELL_SIZE;
        cells[rowY][colX].handleClick();

    }
    public void applyRules(){
        for(int row = 1; row < cells.length-1; row++){
            for(int col = 1; col < cells[0].length-1; col++){
                cells[row][col].applyRules(cells);
            }
        }
    }
    public void evolve(){
        for(int row = 1; row < cells.length-1; row++){
            for(int col = 1; col < cells[0].length-1; col++){
                cells[row][col].evolve();
            }
        }
    }
    public void keyPressed() {
        doEvolve = !doEvolve;
    }
}