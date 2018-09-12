/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class QuestionFXMLController implements Initializable {

    @FXML
    private GridPane qlist;
    @FXML
    private JFXTextArea t1;
    @FXML
    private JFXTextField t3;
    @FXML
    private JFXTextField t2;
    @FXML
    private JFXTextField t4;
    @FXML
    private JFXTextField t5;
    ObservableList<String> list;
    @FXML
    private JFXComboBox<String> t6;
    @FXML
    private Label l1;
    AssistantData d;
    static String course;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        list.addAll("1", "2", "3", "4");
        t6.setItems(list);

        try {
            d = AssistantData.getDataBaseHandler();

        } catch (Exception ex) {
            Logger.getLogger(QuestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setQNo();

    }

    public void setCourse(String course) {
        this.course = course;

    }

    private void setQNo() {

        try {
          
            String q = "select max(q_number) from " + course + "_question";

            ResultSet res = d.getData(q);

            if (res != null) {
                if (res.next()) {
                    l1.setText(String.valueOf(res.getInt(1) + 1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addQuestion(ActionEvent event) {

        int q_number = Integer.parseInt(l1.getText());
        String question = t1.getText();
        String option1 = t2.getText();
        String option2 = t3.getText();
        String option3 = t4.getText();
        String option4 = t5.getText();
        String correctAns = t6.getValue();

        String q = "insert into " + course + "_question values(" + q_number + ","
                + "'" + question + "',"
                + "'" + option1 + "',"
                + "'" + option2 + "',"
                + "'" + option3 + "',"
                + "'" + option4 + "',"
                + "'" + correctAns + "'"
                + ")";

        if (d.isExeQuery(q)) {          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Question is Added!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setValue(null);
                setQNo();

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Question is Not Added!!!");
                alert.setTitle("Message");
                alert.showAndWait();
            }

        

    }

}
