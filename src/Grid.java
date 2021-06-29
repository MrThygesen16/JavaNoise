package src;

import java.awt.*;

public class Grid {
    

    // specify how many rows and columns
    // since we always want a square grid, we set columns = rows
    int rows = 20;
    int columns = rows;

    // create a 2d of cells that is of size rows * columns
    Cell[][] cells = new Cell[rows][columns];

    // default constructor 
    public Grid(){
        // 'r' denotes rows, and 'c' denotes columns
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                cells[r][c] = new Cell(r,c);
            }
        }

    }

    // painting the grid of squares
    public void paint(Graphics g, Point mousePos){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                cells[r][c].paint(g, mousePos);
            }
        }

    }

    // all cells have their visited boolean set to false
    public void resetGrid(){
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                cells[r][c].visited = false;
            }
        }


        
    }

    // does the inverse of reset
    public void fillGrid(){
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                cells[r][c].visited = true;
            }
        }
    }

   
}