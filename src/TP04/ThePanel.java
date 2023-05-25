package TP04;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Rachel Heningtyas Zanetta Erari
 * NPM: 2206081944
 * Tugas Pemrograman 4: Infix to Postfix Expression
 * https://github.com/rachelzn/TP04.git
 */

public class ThePanel extends JPanel {
    // Declare variables
    private JLabel infixLabel, postfixLabel, resultLabel, resultValue, errorLabel, errorMessage, postfixValue;
    private JTextField enterInput;

    // Constructor for PanelEvaluator
    public ThePanel() {

        setBackground(Color.LIGHT_GRAY); // Set the background color of the panel
        setPreferredSize(new Dimension(500, 120)); // Set the preferred size of the panel
        setFocusable(true); // Set the panel to be focusable

        // Set layout as a 4x2 grid
        setLayout(new GridLayout(4, 2));

        // Create and initialize labels and text fields
        infixLabel = new JLabel("Enter infix expression: ");
        enterInput = new JTextField(20);
        enterInput.addActionListener(new InfixListener()); // Add an action listener for infix input
        enterInput.setBackground(Color.YELLOW); // Set the background color of the input field
        postfixLabel = new JLabel("Postfix expression: ");
        postfixValue = new JLabel("");
        resultLabel = new JLabel("Result: ");
        resultValue = new JLabel("");
        errorLabel = new JLabel("Error Message: ");
        errorMessage = new JLabel("");

        // Add labels and text fields to the panel
        add(infixLabel);
        add(enterInput);
        add(postfixLabel);
        add(postfixValue);
        add(resultLabel);
        add(resultValue);
        add(errorLabel);
        add(errorMessage);
    }

    // ActionListener for infix input
    private class InfixListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Reset error handling
            TheAlgorithm.resetError();

            // Set the postfix expression value based on the infix input
            postfixValue.setText(TheAlgorithm.infixToPostfix(enterInput.getText()));

            // Calculate the result based on the postfix expression
            resultValue.setText(String.valueOf(TheAlgorithm.calculation(postfixValue.getText())));

            // Set the error message value
            errorMessage.setText(TheAlgorithm.getErrorHandling());
        }
    }
}

