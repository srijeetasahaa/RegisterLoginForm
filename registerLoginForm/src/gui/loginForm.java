package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.myJDBC;
import constant.commonUse;

public class loginForm extends form {
    public loginForm () {
        super ("Login");
        addComponent ();
    }

    public void addComponent () {

        //create a login label
        JLabel loginLabel=new JLabel("Login");
        loginLabel.setBounds(0, 25, 530,150);// sets the text in JFrame
        loginLabel.setForeground(commonUse.text);// changes the colour of font
        loginLabel.setFont(new Font ("Dialog",Font.BOLD,40));// sets the text style and size
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);// centers the text

        // create a username label
        JLabel userName=new JLabel ("Username:");
        userName.setBounds(20,50,400,225);
        userName.setForeground(commonUse.text);
        userName.setFont (new Font ("Dialog",Font.PLAIN,20));

        // create a username text field
        JTextField user=new JTextField();
        user.setBounds(20, 185, 500, 50);
        user.setBackground(commonUse.secondary);
        user.setFont(new Font("Dialog",Font.PLAIN,20));
        user.setForeground(commonUse.text);

        // create a password label
        JLabel password=new JLabel("Password:");
        password.setBounds(20, 240, 600, 60);
        password.setForeground(commonUse.text);
        password.setFont(new Font("Dialog",Font.PLAIN,20));

        //create a password text field
        JPasswordField pw=new JPasswordField();
        pw.setBounds(20,295,500,50);
        pw.setBackground(commonUse.secondary);
        pw.setFont(new Font("Dialog",Font.PLAIN,20));
        pw.setForeground(commonUse.text);

        //create a login button
        JButton login=new JButton("Login");
        login.setBounds(120, 420, 300, 60);
        login.setForeground(commonUse.secondary);
        login.setFont(new Font("Dialog",Font.BOLD,20));
        login.setBackground(commonUse.text);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// to change cursor style in login button


        // add login credential validation
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = user.getText();

                // get password
                String password=new String (pw.getPassword());

                // check validation through another method
                if (!myJDBC.validateLogin(username, password)) {
                    // login succesful
                    JOptionPane.showMessageDialog(loginForm.this, "Login successful!");

                    // open a new page
                    newPage np=new newPage();
                    np.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginForm.this, "Login failed!");

                    // go back to register page
                    registerForm rf=new registerForm();
                    rf.setVisible(true);
                }
            }
            
        });


        // create a register up label
        JLabel register=new JLabel("Not a user? Register here!");
        register.setBounds(190, 470, 400, 60);
        register.setForeground(commonUse.text);
        register.setFont(new Font("Dialog",Font.PLAIN,13));

        // add connection between login and register page
        register.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // to close login page
                loginForm.this.dispose();
                new registerForm().setVisible(true);
            }
            
        });



        // add the components
        add (loginLabel);
        add (userName);
        add (user);
        add (password);
        add (pw);
        add (login);
        add (register);
    }

    
}
