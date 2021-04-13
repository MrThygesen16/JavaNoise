package src;

import java.awt.*;

public class Cell extends Rectangle {
    // fields
    static int size = 35;
    Color colour;

    int xPos;
    int yPos;


    //constructors
    public Cell(int x, int y){
        super(x,y,size,size);
        colour = Color.WHITE;
        xPos = (x/35);
        yPos = (y/35);
    }


    //methods
    void paint(Graphics g, Point mousePos){
        if(contains(mousePos)){
            g.setColor(Color.BLACK);
            g.drawString("Cell [" + xPos + "," + yPos + "]", 725, 75);
            
            g.setColor(Color.GRAY);

        } else {
            g.setColor(colour);
        }
        
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
        
    }

    public boolean contains(Point p){
        if (p != null){
            return super.contains(p);
        } else {
            return false;
        }
    }
   

}