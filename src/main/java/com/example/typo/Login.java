package com.example.typo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Login extends Application implements Initializable  {
    private static Stage stg;
    public javafx.scene.layout.Pane Pane;
    public Label displayUsername;
    public ImageView logo;
    public Button login;
    public TextField username;
    public TextField password;
    public Button signup;
    public Button delete;
    public Button forget_pass;

    @FXML
    private Text notf;
    @FXML
    private Text notm;

    @Override
    public void start(Stage pstage) throws IOException {
        stg= pstage;
        pstage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        pstage.setTitle("Typo");
        pstage.setMaxHeight(720.0);
        pstage.setMaxWidth(950.0);
        pstage.setMinHeight(720.0);
        pstage.setMinWidth(950.0);
        pstage.setScene(scene);
        pstage.getIcons().add(new Image(Login.class.getResourceAsStream("/com/example/typo/Images/key.png")));
        pstage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notf.setVisible(false);
        notm.setVisible(false);
    }
    public void changescene(String fxml) throws IOException{
        Parent pane= FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public void sign_in_page() throws IOException {
        Login m = new Login();
        m.changescene("signup.fxml");
    }
    public void delete_page() throws IOException {
        Login m = new Login();
        m.changescene("deleteacc.fxml");
    }
    public void updatepass() throws IOException {
        Login m = new Login();
        m.changescene("Settings.fxml");
    }
    public void login(ActionEvent ae) throws SQLException, IOException {
        String u= username.getText();
        String pass =password.getText();
        Login m = new Login();
        notf.setVisible(false);
        notm.setVisible(false);
        boolean found = Database.searchuser(u, pass);
        if(found==true){
            boolean match = Database.matchpass(u, pass);
            if(match==true){

                m.changescene("hello-view.fxml");
            }else{
                notm.setVisible(true);
            }
        }else{
            notf.setVisible(true);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}