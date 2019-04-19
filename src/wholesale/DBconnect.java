
package wholesale;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DBconnect {
   Connection DBconnects() throws Exception{
        String url = "jdbc:mysql://localhost:3306/WholeSale";
        String username = "root";  //getUser();
        String password ="";//getPass();
        Connection connection = DriverManager.getConnection(url, username,"");
        return (connection);
   }
     String getPass(){
       JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "The title",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0) 
        {
            char[] password = pass.getPassword();
            return (new String(password));
        }
        return "";
   }

        String getUser() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter UserName:");
        JTextField user = new JTextField(10);
        panel.add(label);
        panel.add(user);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "The title",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0) // pressing OK button
        {
            String UserName = user.getText();
            return (new String(UserName));
        }
        return "";
    }

}
