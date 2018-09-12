/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane root1;
    @FXML
    private JFXProgressBar p1;
    @FXML
    private JFXProgressBar p2;
    @FXML
    private JFXProgressBar p3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getStart();
    }

    private void getStart() {

        FadeTransition fd = new FadeTransition(Duration.millis(15000), root1);

        fd.setFromValue(0);
        fd.setToValue(1);

        fd.setCycleCount(1);
        fd.setAutoReverse(true);
        fd.setOnFinished(ex -> {

            try {

                loadWindow("/home/LoginAndSignUp.fxml", "ExamAssistant");
                root1.getScene().getWindow().hide();
            } catch (IOException ex1) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        });

        fd.play();

    }

    private void loadWindow(String str, String title) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(str));

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent, (Color.TRANSPARENT)));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

}
