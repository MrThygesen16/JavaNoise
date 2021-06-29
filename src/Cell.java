package src;

import java.awt.*;

public class Cell extends Rectangle {
    
    // how large each square cell will be in terms of length * width
    static int size = 35;
    Color colour;

    // has the mouse hovered over or, is it active: (1), or inactive (0)
    boolean visited;

    // rowPos = x coord
    // colPos = y coord
    int rowPos;
    int colPos;


    //constructors
    public Cell(int r, int c){
        // off set the x and y coords by 10 so it looks better when drawn
        super(10+r*size, 10+c*size, size, size);
        colour = Color.WHITE;
        rowPos = r;
        colPos = c;

        // all cells are inactive by default
        visited = false;
    }


    //methods
    void paint(Graphics g, Point mousePos, int s){
        
        // boolean used as a flag
        // we want to draw some text over the square
        // but we have to call the text afterwards
        // since newer paints are placed on top of older ones
        boolean mouseOver = false;

        
        if(contains(mousePos)){
            
            mouseOver = true;
            // cell currently being hovered over by the mouse becomes grey
            g.setColor(Color.GRAY);

        } else {

            // if the cell has been clicked: if clicked it is visited (1), if not it is not visited (0)
            if (visited){
                g.setColor(Color.darkGray);
            } else{ // if the mouse has not hovered over a cell, it remains the default colour (white)
                g.setColor(colour);
            }

            
        }

        // draw cell using above colours 
        g.fillRect(x,y,size,size);

        // we pass through the state of the grid into the cell object
        // if we are in init state we show the grid to assist with drawing
        // otherwise, we show no grid
        if (s == 0){
            g.setColor(Color.BLACK);
            g.drawRect(x,y,size,size);
        }
        // this creates an outline of the cells
      

        // this is where we write the coordinates of the square if the mouse is in it
        if (mouseOver){
            g.setColor(Color.GREEN);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
            g.drawString("[" + rowPos + "," + colPos + "]", 13+size*rowPos, 38+size*colPos);
        }

    }

    // check if mouse is in at a point
    public boolean contains(Point p){
        if (p != null){
            return super.contains(p);
        } else {
            return false;
        }
    }


}