package com.example.typo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Signup  implements Initializable {
    public javafx.scene.layout.Pane Pane;
    public Label displayUsername;
    public ImageView logo;
    public Button backtologin;
    public Label regis;
    @FXML
    private TextField newuser;
    @FXML
    private TextField newpass;
    @FXML
    private Button signup2;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        regis.setVisible(false);
        regis.setDisable(true);
    }
    public void signin_user(ActionEvent ae) throws SQLException {
        String u= newuser.getText();
        String pass =newpass.getText();
        try {
                if(!u.equals("")&&!pass.equals("")) {
                    Database.adduser(u, pass);
                    Database.addusertorec(u,pass);
                    regis.setVisible(true);
                    regis.setDisable(false);

                    signup2.setVisible(false);
                    signup2.setDisable(true);
                }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void login_page(ActionEvent ae) throws IOException {
        Login m = new Login();
        m.changescene("login.fxml");
    }
}