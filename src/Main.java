package src;

import javax.swing.*;
import java.awt.*;

class Main extends JFrame {
    

    class App extends JPanel {
        
        Grid grid;

        public App() {
            setPreferredSize(new Dimension(950, 720));
            grid = new Grid();
        }

        @Override
        public void paint(Graphics g) {
            
            grid.paint(g, getMousePosition());
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
        this.setTitle("Test App");
    }

    public void run() {
        while (true) {
            this.repaint();
        }
    }

}