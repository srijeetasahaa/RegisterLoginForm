package gui;

import javax.swing.JFrame;

import constant.commonUse;

public class form extends JFrame{
    public form (String title) {
        super (title);
        // set the title
        setSize(550, 700);

        // to close the JFrame properly
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //to disable the default management so that components can be placed wherever needed
        setLayout(null);

        // to make the JFrame open in the middle of the screen
        setLocationRelativeTo(null);

        //to set the background colour of JFrame
        getContentPane().setBackground(commonUse.primary);
    }
}

