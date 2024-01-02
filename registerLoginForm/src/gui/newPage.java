package gui;

import java.awt.Font;

import javax.swing.JLabel;

import constant.commonUse;

public class newPage extends form {

    public newPage() {
        super("newPage");
        addComponent ();
    }

    public void addComponent() {
        // create a "logged in"
        JLabel loggedIn=new JLabel("You have successfully logged in!");
        loggedIn.setBounds (45,230,500,100);
        loggedIn.setForeground(commonUse.text);
        loggedIn.setFont(new Font("Dialog", Font.PLAIN, 30));


        // add the components
        add (loggedIn);

    }
    
}

