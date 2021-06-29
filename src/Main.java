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


    class App extends JPanel implements MouseListener {
        

        // here we set size of the window
        // and create a new grid object
        public App() {
            
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
            setPreferredSize(new Dimension(725, 725));
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
            
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // we can leave blank as we do not need to use this method
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // we can leave blank as we do not need to use this method
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // we can leave blank as we do not need to use this method
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // we can leave blank as we do not need to use this method            
        }

      
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
            this.repaint();
        }
    }

    // handles when 'r' is pressed
    public class ActionR extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
           
            grid.resetGrid();
        
        }
        
    }

    // handles when "SPACE" is pressed
    public class ActionSpace extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
           
            grid.fillGrid();
        
        }
        

    }



}