package main.java.com.java.main;

import javax.swing.*;
import java.awt.*;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Grid {

    public Grid() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Room");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 800);
        }

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            int size = Math.min(getWidth() - 4, getHeight() - 4) / 10;
            Scanner sc= new Scanner(System.in);
            System.out.print("Please enter the length of the room ");
            int length= sc.nextInt();

            int y = (getHeight() - (size * 10)) / 2;

            for (int horz = 0; horz < length; horz++) {
                int x = (getWidth() - (size * 10)) / 2;
                for (int vert = 0; vert < length; vert++) {
                    g.drawRect(x, y, size, size);
                    x += size;
                }
                y += size;
            }
            g2d.dispose();
        }

    }
}