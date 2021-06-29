package src;

import java.awt.*;

public class Grid {
    

    // specify how many rows and columns
    // since we always want a square grid, we set columns = rows
    int rows = 20;
    int columns = rows;

    // state machine - state changing handled in Main.java
    // 0 = init
    // 1 = running
    // 2 = paused
    int state;

    // create a 2d of cells that is of size rows * columns
    Cell[][] cells = new Cell[rows][columns];

    // default constructor 
    public Grid(){

        state = 0;

        // 'r' denotes rows, and 'c' denotes columns
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                cells[r][c] = new Cell(r,c);
            }
        }

    }

    // painting the grid of squares
    public void paint(Graphics g, Point mousePos){

        Cell[][] newGen = cells;
        
        // tell us what state we are currently in
        g.drawString("State: " + state, 720, 25);
        if (state == 0){
            g.drawString("(Initialise)", 720, 45);
        } else if (state == 1){
            g.drawString("(Running)", 720, 45);
        } else if (state == 2){
            g.drawString("(Paused)", 720, 45);
        }
        

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                cells[r][c].paint(g, mousePos, state);

                if (state == 1){
                    int count = 0;
                    if ((r > 0 && r < rows - 1) && (c > 0 && c < columns - 1)){
                                
                        count = countNeighbours(r, c);

                        if (cells[c][r].visited == false && count == 3) {
                            newGen[r][c].visited = true;
                        } else if(cells[c][r].visited && (count < 2 || count > 3 )){
                            newGen[r][c].visited = false;
                        } else {
                            newGen[r][c].visited = cells[c][r].visited;
                        }
                    }


                }
            }
        }

        cells = newGen;

    }

    public int countNeighbours(int x, int y){
        int count = 0;

        // one left of current square
        if (cells[x-1][y].visited){
            count = count +1;
        }

        // one right of current square
        if (cells[x+1][y].visited){
            count = count + 1;
        }

        // onr below
        if (cells[x][y+1].visited){
            count = count + 1;
        }

        // one above
        if (cells[x][y-1].visited){
            count = count + 1;
        }

        // top left diag
        if (cells[x-1][y-1].visited){
            count = count + 1;
        }

        // bottom right diag
        if (cells[x+1][y+1].visited){
            count = count + 1;
        }

        // top right diag
        if (cells[x-1][y+1].visited){
            count = count + 1;
        }

        // bottom left diag
        if (cells[x+1][y-1].visited){
            count = count + 1;
        }

        return count;
    }

    // all cells have their visited boolean set to false
    public void resetGrid(){

        if (state == 0 || state == 2){
            for (int r = 0; r < rows; r++){
                for (int c = 0; c < columns; c++){
                    cells[r][c].visited = false;
                }
            }
        }

    }

    // does the inverse of reset
    public void fillGrid(){

        if (state == 0){
            for (int r = 0; r < rows; r++){
                for (int c = 0; c < columns; c++){
                    cells[r][c].visited = true;
                }
            }
        }
    }

    // handles left click and adds cells
    public void mouseLeftClick(int x, int y){

        if (state == 0){
            for (int r = 0; r < rows; r++){
                for (int c = 0; c < columns; c++){
                
                    if (cells[r][c].contains(x, y)){
                        cells[r][c].visited = true;
                    }
                }
            }
        }
    }

    // handles right click and deletes cells
    public void mouseRightClick(int x, int y){

        if (state == 0){
            for (int r = 0; r < rows; r++){
                for (int c = 0; c < columns; c++){
                
                    if (cells[r][c].contains(x, y)){
                        cells[r][c].visited = false;
                    }
                }
            }

        }
    }


    
   
}