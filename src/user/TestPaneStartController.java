package user;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import static user.UserHomeController.nameUser;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class TestPaneStartController implements Initializable {

    @FXML
    private JFXTextArea t1;
    static String course;
    AssistantData db;
    ToggleGroup group;
    @FXML
    private JFXRadioButton rb1;
    @FXML
    private JFXRadioButton rb2;
    @FXML
    private JFXRadioButton rb3;
    @FXML
    private JFXRadioButton rb4;
    Timeline timer;
    Label label[];
    @FXML
    private Label qno;
    @FXML
    private Label time;
    boolean start = true;
    int correct = 0;
    int wrong = 0;
    int skip = 0;
    String correct_option = "0";
    static int h = 0;
    static int m = 0;
    static int s = 0;
    ObservableList list;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label usernameLabel;
    public static String username = "user";
    @FXML
    private JFXButton btn_nxt;
    @FXML
    private VBox qqt;

    JFXButton bt[];
    int q_ok[];

    @FXML
    private JFXButton btn_nxt1;

    // public Vbox b;
    /**
     * Initializes the controller class.
     */
    public String getUsernameLabel() {
        return usernameLabel.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(username);
        setName();

        group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        rb4.setToggleGroup(group);

        try {
            db = AssistantData.getDataBaseHandler();
            list = FXCollections.observableArrayList();
            loadSideQuestion();
            setTime();
            doSomething();
            startTest();
            bt[0].setStyle("-fx-background-color: lightgray; -fx-text-fill:white; -fx-font-size:18px;");

        } catch (Exception ex) {
            Logger.getLogger(TestPaneStartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCourse(String s) {
        this.course = s;
    }

    private void startTest() throws SQLException, IOException {
        next();
    }

    @FXML
    private void saveAndNext(ActionEvent event) throws SQLException, IOException {
        JFXRadioButton correctBtn;
        correctBtn = (JFXRadioButton) group.getSelectedToggle();

        if (start) {
            if (correctBtn == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(" Select one Option First !!!");
                alert.showAndWait();
                return;

            }
            if (correctBtn.getAccessibleText().equals(correct_option)) {
                correct++;

            } else {
                wrong++;
            }
            
            if(skip>0)
                --skip;
            
            

            q_ok[Integer.parseInt(qno.getText()) - 1] = 1;

            buttonColor(bt, qno.getText());
            changeColor(qno.getText());

            next();

        }

        group.selectToggle(null);

    }

    @FXML
    private void skipAndNext(ActionEvent event) throws SQLException, IOException {
        if (start) {
            group.selectToggle(null);
            
            next();
            buttonColor(bt, qno.getText());
           // if(skip>0)
             //   --skip;

        }

    }

    private void next(String num) throws SQLException {
        group.selectToggle(null);

        try {
            String q = "select * from " + course + "_question where q_number = " + (Integer.parseInt(num)) + "";
            ResultSet res = db.getData(q);
            
            System.out.println("Yess");

            if (res != null) {
                if (res.next()) {
                                             
                    qno.setText(String.valueOf(res.getString("q_number")));
                    t1.setText(res.getString("question"));
                    rb1.setText(res.getString("option_1"));
                    rb2.setText(res.getString("option_2"));
                    rb3.setText(res.getString("option_3"));
                    rb4.setText(res.getString("option_4"));
                    correct_option = res.getString("correct_ans");
                    
                     
                    
                   
                }

            }

        } catch (SQLException ex) {
            //  Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void next() throws SQLException, IOException {

        try {
            String qq = "select count(*) from " + course + "_question";
            ResultSet ress = db.getData(qq);
         
            int n = 0;

            if (ress.next()) {
                n = ress.getInt(1);
            }

            if (n > Integer.parseInt(qno.getText())) {
                String q = "select * from " + course + "_question where q_number = " + (Integer.parseInt(qno.getText()) + 1) + "";
                start = true;
                ResultSet res = db.getData(q);

                if (res != null) {
                    if (res.next()) {
                        
                        
                       

                        qno.setText(String.valueOf(res.getString("q_number")));
                        t1.setText(res.getString("question"));
                        rb1.setText(res.getString("option_1"));
                        rb2.setText(res.getString("option_2"));
                        rb3.setText(res.getString("option_3"));
                        rb4.setText(res.getString("option_4"));
                           
                        correct_option = res.getString("correct_ans");
                         
                        
                        
                        
                    }

                }
            } else {
                // Test Finished

               // finishTest();

            }

        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }

    }

    private void doSomething() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            try {
                runTimer();
            } catch (IOException ex) {
                Logger.getLogger(TestPaneStartController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TestPaneStartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }

    private void setResult(int correct, int wrong, int skip) throws SQLException {
        
      
        
        
        String qq =  "select * from totalCourse where courseName = '"+course+"'";
        String eachMarks = null;
        String minMarks = null ;
        
        ResultSet rs = db.getData(qq);
        if(rs!=null)
        {
            if(rs.next())
            {
                eachMarks = rs.getString("questionPerMarks");
             minMarks = rs.getString("minMarks");
                
            }
                
        }
        String status;
        
        int total = Integer.parseInt(eachMarks)*correct;
        
        if(total>= Integer.parseInt(minMarks))
        {
            status = "Pass";
            
        }
        else
            status = "Fail";
        
        
        
        
        String q =  "insert into "+UserHomeController.nameUser+"_userTable values ('"+course+"','"+correct+ "','"+wrong+"','"+skip+"','"+status+"')";

      db.isExeQuery(q);

    }

    private void runTimer() throws IOException, SQLException {
        if (h <= 0 && m <= 0 && s <= 0) {
            timer.stop();
            finishTest();
            start = false;

        }

        if (s == 0 && m > 0) {

            s = 60;
            m = m - 1;

        } else if (m == 0 && h > 0) {
            m = 59;
            s = 60;
            h = h - 1;
        }
        if (s > 0) {
            s = s - 1;
        }

        String ttime = String.valueOf(h + " : " + m + " : " + s);

        time.setText(ttime);
    }

    private void setTime() throws SQLException {
        String query = "select duration from totalCourse where coursename ='" + course + "'";
        ResultSet rs = db.getData(query);

        if (rs != null) {
            if (rs.next()) {
                String duration = rs.getString("duration");
                String arr[] = duration.split(":");
                h = Integer.parseInt(arr[0]);
                m = Integer.parseInt(arr[1]);
                s = 0;

            }
        }

    }

    private void loadSideQuestion() throws SQLException, IOException {

        String q = "select count(*) from " + course + "_question";
        ResultSet rs = db.getData(q);
        int qRange = 0;

        if (rs != null) {
            if (rs.next()) {
                qRange = Integer.parseInt(rs.getString(1));

            }
        }

        bt = new JFXButton[qRange];
        skip = qRange;
        q_ok = new int[qRange];

        for (int i = 0; i < qRange; i++) {

            bt[i] = new JFXButton();
            bt[i].setText(String.valueOf(i + 1));
            bt[i].setPrefSize(123, 45);
            bt[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ev) {

                    try {
                        chk(ev);
                    } catch (SQLException ex) {
                        Logger.getLogger(TestPaneStartController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });

            bt[i].setStyle("-fx-background-color: #34495E; -fx-text-fill:white; -fx-font-size:18px;");

            qqt.getChildren().add(bt[i]);

            // list.add(new TestPaneStartController.QNo(String.valueOf(i)));
        }

    }

    private void finishTest() throws IOException, SQLException {
        h = 0;
        m = 0;
        s = 0;
        timer.stop();
        start = false;
        
        setResult(correct,wrong,skip);

        BoxBlur blur = new BoxBlur(8, 8, 8);

        rootPane.setEffect(blur);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Test is Finished !!! See your result go to result Section!!!");
        alert.showAndWait();

        UserHomeController.nameUser = username;

        rootPane.setEffect(null);
        // ResultPaneController.course = course;

        loadWindow("UserHome.fxml", "ExamAssistant");

        btn_nxt.getScene().getWindow().hide();

        System.out.println("Finished");

    }

    private void setName() {
        usernameLabel.setText(nameUser);

    }

    private void loadWindow(String str, String title) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(str));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();

    }

    private void chk(ActionEvent ev) throws SQLException {

        JFXButton b = new JFXButton();
        b = (JFXButton) ev.getSource();

        // bt[Integer.parseInt(b.getText())-1].setStyle("-fx-background-color: lightgray; -fx-text-fill:green; -fx-font-size:18px;");
        System.out.println(q_ok[0]);

      

        if(q_ok[Integer.parseInt(b.getText())-1]==0){
              buttonColor(bt, b.getText());
        next(b.getText());
        }

    }

    private void changeColor(String text) {

        bt[Integer.parseInt(text) - 1].setStyle("-fx-background-color: green; -fx-text-fill:white; -fx-font-size:18px;");
        bt[Integer.parseInt(text)].setStyle("-fx-background-color: lightgray; -fx-text-fill:white; -fx-font-size:18px;");

    }

    @FXML
    private void finalSubmit(ActionEvent event) throws IOException, SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Are you soure want to  submit exam !!!");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            finishTest();

        } else {
            return;
        }

    }

    private void buttonColor(JFXButton btn[], String num) {
        for (int i = 0; i < btn.length; i++) {
            if (q_ok[i] == 1) {
                bt[i].setStyle("-fx-background-color: green; -fx-text-fill:white; -fx-font-size:18px;");

            } else {
                bt[i].setStyle("-fx-background-color: #34495E; -fx-text-fill:white; -fx-font-size:18px;");

            }
        }

        bt[Integer.parseInt(num) - 1].setStyle("-fx-background-color: lightgray; -fx-text-fill:white; -fx-font-size:18px;");

    }

}
