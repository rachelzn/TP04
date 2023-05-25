package TP04;
import javax.swing.*;

/**
 * @author Rachel Heningtyas Zanetta Erari
 * NPM: 2206081944
 * Tugas Pemrograman 4: Infix to Postfix Expression
 * https://github.com/rachelzn/TP04.git
 */

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame with title "Infix -> Postfix Evaluator"
        JFrame frame = new JFrame("Infix -> Postfix Evaluator");

        // Create a PanelEvaluator instance
        ThePanel ThePanel = new ThePanel();

        // Set the default close operation for the frame to exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panelEvaluator to the frame
        frame.add(ThePanel);

        // Pack the frame to fit the preferred size of its components
        frame.pack();

        // Set the location of the frame on the screen
        frame.setLocation(500, 350);

        // Make the frame visible
        frame.setVisible(true);
    }
}

