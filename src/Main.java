package src;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


class Main extends JFrame {
    // actions
    Action actionR;
    Action actionSpace;

    // grid object
    Grid grid;


    class App extends JPanel implements MouseListener{
        

        // here we set size of the window
        // and create a new grid object
        public App() {
            
            // mouse listener
            this.addMouseListener(this);

            // these are our keybindings
            // aka key-bindings
            actionR = new ActionR();
            actionSpace = new ActionSpace();

            // assign the r character to the Action 'actionR'
            this.getInputMap().put(KeyStroke.getKeyStroke('r'), "actionR");
            this.getActionMap().put("actionR", actionR); 

            // assign "SPACE" to the action 'actionSpace'
            this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "actionSpace");
            this.getActionMap().put("actionSpace", actionSpace); 

            // creates new grid object and sets size of window
            setPreferredSize(new Dimension(800, 725));
            grid = new Grid();
            
        }

        @Override
        public void paint(Graphics g) {
            // paint grid object we created
            grid.paint(g, getMousePosition());
        }


        /* 

            Below is mouseListener methods from the class we inheret
        
        */

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if (e.getButton() == MouseEvent.BUTTON1){ // BUTTON1 = LEFT CLICK
                grid.mouseLeftClick(e.getX(), e.getY());
            }
            
            if (e.getButton() == MouseEvent.BUTTON3){ // BUTTON3 = RIGHT CLICK
                grid.mouseRightClick(e.getX(), e.getY());
            }
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {            
        }

        @Override
        public void mouseExited(MouseEvent e) {            
        }

        @Override
        public void mousePressed(MouseEvent e) {           
        }

        @Override
        public void mouseReleased(MouseEvent e) {            
        }

        /* 
        
            END MOUSE LISTENER METHODS
        
        */

      
    }

    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();

    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
        this.setTitle("Java Noise Project"); // set title of app
    }

    // responsible for causing the canvas to be repainted
    public void run() {
        
        while (true) {

            // if we are in the running state we add a delay to drawing the cells
            if (grid.state == 1){
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                this.repaint();
            } else { 
                // if we are not in running state draw as per normal
                this.repaint();
            }
        
        }
    }

    // handles when 'r' is pressed
    public class ActionR extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (grid.state == 0){
                grid.resetGrid();
            }

            if (grid.state == 2){
                grid.resetGrid();
                grid.state = 0;
            }
            
        
        }
        
    }

    // handles when "SPACE" is pressed
    public class ActionSpace extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (grid.state == 0){
                grid.state = 1;
            } else if (grid.state == 1){
                grid.state = 2;
            } else if (grid.state == 2){
                grid.state = 1;
            }
            
        
        }
        

    }



}