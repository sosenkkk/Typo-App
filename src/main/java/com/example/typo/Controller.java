package com.example.typo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

abstract  class Create_scene{
    public void playGame(ActionEvent ddd) throws IOException {

    }
}
public  class Controller extends Create_scene  implements Initializable  {

    @FXML
    public ImageView logo;
    @FXML
    public Button playButton;
    @FXML
    public Button csvupload;
    public Button csvupload1;

    @FXML
    private Label timeLabel2;

    @FXML
    private Label timeLabel;

    @FXML
    private Text total;
    @FXML
    private Text wpm;
    @FXML
    private Text invalid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDay = formatter.format(date);
        formatter=new SimpleDateFormat("HH:mm:ss");
        String time= formatter.format(date);
        timeLabel.setText("Date-"+strDay);
        timeLabel2.setText("Time-"+time);
        int[] data = FileHandling.sumUpNumbers("src/data");
        total.setText(String.valueOf(data[0]));
        wpm.setText(String.valueOf(Math.round(data[1]*1.0/data[3])));
        invalid.setText(String.valueOf(data[2]));
    }

    public void addusingcsv(){
        String filepath="E:\\College\\Programs\\CSV oops\\usercsv.csv";
        Database.addcsv(filepath);
    }
    public void uptusingcsv(){
        String filepath="E:\\College\\Programs\\CSV oops\\userupcsv.csv";
        Database.updatecsv(filepath);
    }
    @Override
    public void playGame(ActionEvent ddd) throws IOException {
        Login m = new Login();
            m.changescene("game.fxml");
    }


}
