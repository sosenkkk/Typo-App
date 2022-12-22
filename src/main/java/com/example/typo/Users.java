package com.example.typo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Users {
    @FXML

    private TextArea ID;
    public javafx.scene.layout.Pane Pane;
    public Text userlist;
    public Button disall;
    public Button backtologin;
    public Button disall1;

    public void displayall() throws SQLException {
        ArrayList<String> arr;
        arr=Database.display();
        for(int i=0; i< arr.size(); i++){
            System.out.println(arr.get(i));
        }
        String s= arr.toString();
        userlist.setText(s);

    }
    public void displaybyid(KeyEvent ke) throws SQLException {
        if(ke.getCode().equals(KeyCode.SPACE)) {
            String s = ID.getText();
            s=s.trim();
            int id= Integer.parseInt(s);
            System.out.println(s);
            String arr;
            arr = Database.displaybyID(id);
            if(arr!=null)
                userlist.setText(arr);
            else
                userlist.setText("Not Found");
        }
    }

    public void login() throws IOException {
        Login m = new Login();
        m.changescene("game.fxml");
    }
}
