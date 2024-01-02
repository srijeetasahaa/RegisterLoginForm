package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//import javax.swing.SwingConstants;

import org.w3c.dom.events.MouseEvent;

import backend.myJDBC;
import constant.commonUse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class registerForm extends form {
    public registerForm() {
        super("Register");
        addComponent();
    }

    void addComponent() {

        // create a register label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(180, 25, 600, 150);// sets the text in JFrame
        registerLabel.setForeground(commonUse.text);// changes the colour of font
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));// sets the text style and size
        // registerLabel.setHorizontalAlignment(SwingConstants.CENTER);// centers the
        // text

        // create a username label
        JLabel userName = new JLabel("Username:");
        userName.setBounds(20, 50, 400, 225);
        userName.setForeground(commonUse.text);
        userName.setFont(new Font("Dialog", Font.PLAIN, 20));

        // create a username text field
        JTextField user = new JTextField();
        user.setBounds(20, 185, 500, 50);
        user.setBackground(commonUse.secondary);
        user.setFont(new Font("Dialog", Font.PLAIN, 20));
        user.setForeground(commonUse.text);

        // create a password label
        JLabel password = new JLabel("Password:");
        password.setBounds(20, 240, 600, 60);
        password.setForeground(commonUse.text);
        password.setFont(new Font("Dialog", Font.PLAIN, 20));

        // create a password text field
        JPasswordField pw = new JPasswordField();
        pw.setBounds(20, 295, 500, 50);
        pw.setBackground(commonUse.secondary);
        pw.setFont(new Font("Dialog", Font.PLAIN, 20));
        pw.setForeground(commonUse.text);

        // create a re enterpassword label
        JLabel rePassword = new JLabel("Re-enter password:");
        rePassword.setBounds(20, 345, 600, 60);
        rePassword.setForeground(commonUse.text);
        rePassword.setFont(new Font("Dialog", Font.PLAIN, 20));

        // create a re enter password text field
        JPasswordField rePw = new JPasswordField();
        rePw.setBounds(20, 395, 500, 50);
        rePw.setBackground(commonUse.secondary);
        rePw.setFont(new Font("Dialog", Font.PLAIN, 20));
        rePw.setForeground(commonUse.text);

        // create a register button
        JButton register = new JButton("Register");
        register.setBounds(120, 490, 300, 60);
        register.setForeground(commonUse.secondary);
        register.setFont(new Font("Dialog", Font.BOLD, 20));
        register.setBackground(commonUse.text);
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// to change cursor style in login button

        // backend code goes here
        register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = user.getText();

                // get password
                String password = new String(pw.getPassword());

                // get re entered password
                String rePassword = new String(rePw.getPassword());

                // validate uer input
                if (validateUserInput(username, password, rePassword)) {
                    // register user to db
                    if (myJDBC.register(username, rePassword)) {
                        // dispose of this gui and open login
                        registerForm.this.dispose();
                        
                        // back to login
                        loginForm login=new loginForm();
                        login.setVisible(true);

                        // create a result dialog box
                        JOptionPane.showMessageDialog(login, "Registered account succesfully!");

                    } else {
                        // show register failed due to user already existing in the database
                        JOptionPane.showMessageDialog(registerForm.this, "Error: Username already exists!");
                    }
                } else {
                    JOptionPane.showMessageDialog(registerForm.this, "Username should be atleast 6 characters\ntThe password and re entered password must be same");
                    
                }
            }

        });

        // create a register up label
        JLabel loginAsk = new JLabel("Already a user? Login here!");
        loginAsk.setBounds(197, 540, 400, 60);
        loginAsk.setForeground(commonUse.text);
        loginAsk.setFont(new Font("Dialog", Font.PLAIN, 13));

        // link between login and register page
        loginAsk.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                registerForm.this.dispose();
                new loginForm().setVisible(true);
            }

        });

        // add the components
        add(registerLabel);
        add(userName);
        add(user);
        add(password);
        add(pw);
        add(rePassword);
        add(rePw);
        add(register);
        add(loginAsk);

    }

    // here user input will be validated, making sure user has placed a valid
    // username and password
    private boolean validateUserInput(String username, String password, String rePassword) {
        // all fields must have value
        if (username.length() == 0 || password.length() == 0 || rePassword.length() == 0) {
            return false;
        }

        // username has to be atleast a 6 character long
        if (username.length()<6) {
            return false;
        }

        // password and re entered password must be same
        if (!password.equals(rePassword)) {
            return false;
        }

        return true;
    }
}

