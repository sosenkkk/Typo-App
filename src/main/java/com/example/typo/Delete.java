package com.example.typo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Delete  implements Initializable {
    public Text deletem;
    @FXML
    private javafx.scene.layout.Pane Pane;
    @FXML
    private Label displayUsername;
    @FXML
    private ImageView logo;
    @FXML
    private TextField newuser;
    @FXML
    private Button delete;
    @FXML
    private TextField newpass;
    @FXML
    private Button backtologin;
    @FXML
    private Text notm;
    @FXML
    private Text notf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notf.setVisible(false);
        notm.setVisible(false);
        deletem.setVisible(false);

    }
    public void delete_user(ActionEvent ae) throws SQLException, IOException {
        String u= newuser.getText();
        String pass =newpass.getText();
        Login m = new Login();
        notf.setVisible(false);
        notm.setVisible(false);
        deletem.setVisible(false);
        boolean found = Database.searchuser(u, pass);
        if(found==true){
            boolean match = Database.matchpass(u, pass);
            if(match==true){
                Database.deleteuser(u, pass);
                deletem.setVisible(true);

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
