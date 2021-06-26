package src;

import java.awt.*;


public class Grid {
    
    int rows = 10;
    int columns = 10;

    Cell[][] cells = new Cell[rows][columns];


    public Grid(){

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                cells[i][j] = new Cell(i,j);
            }
        }

    }

    public void paint(Graphics g, Point mousePos){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j].paint(g, mousePos);
            }
        }

    }


}

