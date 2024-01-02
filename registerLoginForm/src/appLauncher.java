import javax.swing.SwingUtilities;

import backend.myJDBC;
import gui.loginForm;
import gui.newPage;
import gui.registerForm;

public class appLauncher {
    public static void main(String[] args) {
        // invokeLater() is used to make updates to GUI more thread safe and efficient
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new loginForm().setVisible(true);// to make the JFrame visible
                //new registerForm().setVisible(true);

                // check user test
                // System.out.println(myJDBC.checkUserMethod("username"));

                // check register test
                // System.out.println(myJDBC.register("username1", "password1"));

                // check login test
                //System.out.println(myJDBC.validateLogin("username123", "password123"));

                // a new page (that will open on successful login)
                //new newPage ().setVisible(true);
            }

        });
    }
}

