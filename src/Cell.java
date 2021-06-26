package src;

import java.awt.*;

public class Cell extends Rectangle {
    // fields
    static int size = 47;
    Color colour;

    boolean visited;

    int xPos;
    int yPos;


    //constructors
    public Cell(int x, int y){
        super(10+x*size, 10+y*size, size, size );
        colour = Color.WHITE;
        xPos = (x);
        yPos = (y);

        visited = false;
    }


    //methods
    void paint(Graphics g, Point mousePos){
        
        // boolean used as a flag
        // we want to draw some text over the square
        // but we have to call the text afterwards
        // since newer paints are placed on top of older ones
        boolean mouseOver = false;

        
        if(contains(mousePos)){
            
            visited = true;

            mouseOver = true;
            // square becomes grey
            g.setColor(Color.GRAY);

        } else {
            // we set squar to default colour (white)

            if (visited){
                g.setColor(Color.darkGray);
            } else{
                g.setColor(colour);
            }

            
        }

        // draw recttangle 
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);

        // this is where we write the coordinates of the square if the most is in it
        if (mouseOver){
            g.setColor(Color.GREEN);
            g.drawString("[" + xPos + "," + yPos + "]", 18+size*xPos, 38+size*yPos);
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