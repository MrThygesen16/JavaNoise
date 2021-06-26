package src;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


class Main extends JFrame {
    

    class App extends JPanel implements KeyListener {
        
        Grid grid;
        

        // here we set size of the window
        // and create a new grid object
        public App() {
            // attach key listener to App object
            addKeyListener(this); // for resetting grid
            setPreferredSize(new Dimension(500, 500));
            grid = new Grid();
            
        }

        @Override
        public void paint(Graphics g) {
            // paint grid object we created
            grid.paint(g, getMousePosition());
        }

        // used for detecting when a key is pressed
        @Override
        public void keyPressed(KeyEvent e) {
            
            int key = e.getKeyCode();

            // R key restarts the app
            if (key == KeyEvent.VK_R){
                grid.resetGrid();
            }

            // space fills the grid
            if (key == KeyEvent.VK_SPACE){
                grid.fillGrid();
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }

        @Override
        public void keyTyped(KeyEvent e) {

            
        }


    }

    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();

    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        // attach keylistener to canvas (which is an App object)
        this.addKeyListener(canvas); // for ressting grid
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
        this.setTitle("Java Noise Project"); // set title of app
    }

    public void run() {
        while (true) {
            this.repaint();
        }
    }


}