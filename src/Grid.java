package src;

import java.awt.*;

public class Grid {
    

    // specify how many rows and columns
    int rows = 10;
    int columns = 10;

    // create a 2d of cells that is of size rows * columns
    Cell[][] cells = new Cell[rows][columns];

    // default constructor 
    public Grid(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                cells[i][j] = new Cell(i,j);
                
            }
        }

    }

    // painting the grid of squares
    public void paint(Graphics g, Point mousePos){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j].paint(g, mousePos);
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