package com.example.typo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TypoLogic implements Initializable {


    public FlowPane flowbottom;
    public FlowPane flowtop;
    public Text programWord1;
    public Button profile;
    public Button home;
    public Button settings;
    public Button options;

    public Button mainmenu;
    private int wordCounter = 0;
    private int first = 1;

    private File saveData;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


    @FXML
    private TextArea textarea;
    @FXML
    public Text seconds;
    @FXML
    private Text wordsPerMin;
    @FXML
    private Text accuracy;
    @FXML
    private Text programWord;
    @FXML
    private Text secondProgramWord;

    @FXML
    private TextField userWord;

    @FXML
    private ImageView correct;
    @FXML
    private ImageView wrong;

    @FXML
    private Button playAgain;
    @FXML
    private TextArea today;

    ArrayList<String> passage= new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date = new Date();
        DateFormat formatter=  new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        saveData = new File("src/data/"+ formatter.format(date).strip()+".csv");
        String strday= formatter.format(date);

        seconds.setText("60");
        createpassage();
        Collections.shuffle(passage);
        String str="";
        for (int i=0; i<80; i++){
            str = str+" " + passage.get(i);}
        textarea.setText(str);
        programWord.setText(passage.get(wordCounter));
        wordCounter++;



    }
    //Creating passage

    public void createpassage(){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("words"));
            String line = br.readLine();
            while (line != null) {
                passage.add(line);
                line = br.readLine();
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();;
        }
    }
    public void playagain(ActionEvent ae) throws  IOException{
        Login m = new Login();
        m.changescene("hello-view.fxml");

    }

    public void underdev(ActionEvent ae) throws  IOException{
        Login m = new Login();
        m.changescene("under-dev.fxml");
    }
    public void display(ActionEvent ae) throws  IOException{
        Login m = new Login();
        m.changescene("users.fxml");
    }
    public void settings(ActionEvent ae) throws  IOException{
        Login m = new Login();
        m.changescene("Settings.fxml");
    }

    private int timer= 60;
    private int counter=0;
    private int all=0;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if(timer>-1){
                seconds.setText(String.valueOf(timer));
                timer--;
            }
            else{
                if(timer==-1) {
                    userWord.setDisable(true);
                    userWord.setText("Time's Up!");
                    try{
                        FileWriter writer= new FileWriter(saveData);
                        writer.write(all+",");
                        writer.write(counter+",");
                        writer.write(String.valueOf(all-counter));
                        writer.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                timer--;
            }
        }
    };
    private int letters;
    public void startgame(KeyEvent ke) {

        // only gets called once
        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }

        if (ke.getCode().equals(KeyCode.SPACE)) {
            String s = userWord.getText();
            String real = programWord.getText();
            all++;
            double minutes;
            int c;
            if (s.equals(real)) {
                letters= letters+ s.length();
                minutes=(((60-timer) * 1.0)/60);
                c=(int)(((letters*1.0)/5)/minutes);
                counter++;
                wordsPerMin.setText(String.valueOf(c));
            }
            else{
                minutes=(((60-timer) * 1.0)/60);
                c=(int)(((letters*1.0)/5)/minutes);
                wordsPerMin.setText(String.valueOf(c));
            }
            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/all)*100)));
            programWord.setText(" "+passage.get(wordCounter));
            wordCounter++;
        }

    }
}