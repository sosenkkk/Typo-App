package com.example.typo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    public javafx.scene.layout.Pane Pane;
    public TextField username;
    public ImageView logo;
    public TextField password;
    public Button signup;
    public Button backtologin;
    public Text notf;
    public Text notm;
    public TextField passwordnew;
    public Text notm1;
    public Text notm11;
    public Text notm12;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notf.setVisible(false);
        notm.setVisible(false);
    }
    public void update_user(ActionEvent ae) throws SQLException, IOException {
        String u= username.getText();
        String pass =password.getText();
        String newpass= passwordnew.getText();
        Login m = new Login();
        notf.setVisible(false);
        notm.setVisible(false);
        boolean found = Database.searchuser(u, pass);
        if(found==true){
            boolean match = Database.matchpass(u, pass);
            if(match==true){

                Database.updateuser(u,pass,newpass);
                notm.setText("Password Changed.");
                signup.setVisible(false);
                signup.setDisable(true);
                notm.setVisible(true);
                backtologin.setDisable(false);
                backtologin.setVisible(true);
            }else{
                notm.setVisible(true);
            }
        }else{
            notf.setVisible(true);
        }
    }
    public void login_page(ActionEvent ae) throws IOException {
        Login m = new Login();
        m.changescene("login.fxml");
    }

}
