package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginMainController implements Initializable {
    @FXML
    public AnchorPane pane1,pane4, singupPane, pane2, pane3, loginPane, mainPane, textPane;
    @FXML
    public JFXButton createButton, cancelButton, singupButton;
    @FXML
    public JFXPasswordField singupPassword, singupConfirmPassword;
    @FXML
    public JFXTextField singupName, singupAddress,singupEmail;
    @FXML
    public Label dbstatus;
    @FXML
    public JFXPasswordField password;
    @FXML
    public JFXTextField username;
    @FXML
    public AnchorPane errorMessage;
    @FXML
    public AnchorPane loginMessage;
    public StackPane rootPane;
    @FXML
    LoginModel loginModel = new LoginModel();


    @FXML
    public void hideSingupPane () {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(.5), singupPane);
        translateTransition.setByX(600);
        translateTransition.play();
    }

public void createAction(){
    String sql = "INSERT INTO `login`( `username`, `password`) VALUES (? , ?)";
    try {
        Connection conn = dbConnection.getConnection();
        assert conn != null;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, singupName.getText());
        stmt.setString(2, singupPassword.getText());
        stmt.execute();
        stmt.close();
        conn.close();

    } catch (SQLException e) {
        System.err.println("Got an exception!");
        System.err.println(e.getMessage());
    }
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("Login Added!");
    alert.showAndWait();
}

    @FXML
    public void showSingUpPane () {

        singupPane.setVisible(true);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(.2), singupPane);
        translateTransition.setByX(-600);
        translateTransition.play();
    }
    @FXML
    public void Login ( ActionEvent event ) {
        try {
            if (loginModel.isLogin(username.getText(), password.getText())) {
                //to close login panel after sucessful login
                loginMessage.setVisible(true);
                TranslateTransition loginMessageTransition = translateAnimation(2,loginMessage,0,-150);
                loginMessageTransition.setOnFinished(event1 -> {
                    FadeTransition fadeTransition=fadeAnimation(1,loginMessage,1,0);
                    fadeTransition.setOnFinished(event2 -> {
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                    Stage adminStage = new Stage();
                    Pane root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("SideMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    adminStage.setScene(scene);
                    adminStage.setMaximized(true);//////MAXIMUM SIZE WIDNOW
                    adminStage.show();
                });
                });




            } else {
                dbstatus.setText("Wrong Creditials");
                dbstatus.setTextFill(Color.RED);
                errorMessage.setVisible(true);
                TranslateTransition loginMessageTransition = translateAnimation(1,errorMessage,0,-150);
                loginMessageTransition.setOnFinished(event1 -> fadeAnimation(1,errorMessage,1,0));
                    }

        } catch (Exception ignored) {
        }
    }

    @FXML
    public void initialize ( URL location, ResourceBundle resources ) {
        if (loginModel.isDatabaseConnected()) {
            dbstatus.setText("Connected");
        } else {
            dbstatus.setText("Not Connected");
        }


        singupPane.setVisible(true);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(.2), singupPane);
        translateTransition.setByX(600);
        translateTransition.play();


        FadeTransition fade0 = new FadeTransition(Duration.seconds(3), pane4);
        fade0.setFromValue(1);
        fade0.setToValue(0);
        fade0.play();

        fade0.setOnFinished(event -> {
            FadeTransition fade1 = new FadeTransition(Duration.seconds(3), pane3);
            fade1.setFromValue(1);
            fade1.setToValue(0);
            fade1.play();

            fade1.setOnFinished(event1 -> {
                FadeTransition fade2 = new FadeTransition(Duration.seconds(3), pane2);
                fade2.setFromValue(1);
                fade2.setToValue(0);
                fade2.play();

                fade2.setOnFinished(event3 -> {
                    FadeTransition fade4 = new FadeTransition(Duration.seconds(3), pane2);
                    fade4.setFromValue(0);
                    fade4.setToValue(1);
                    fade4.play();

                    fade4.setOnFinished(event4 -> {
                        FadeTransition fade5 = new FadeTransition(Duration.seconds(3), pane3);
                        fade5.setFromValue(0);
                        fade5.setToValue(1);
                        fade5.play();

                        fade5.setOnFinished(event5 -> {
                            FadeTransition fade6 = new FadeTransition(Duration.seconds(3), pane4);
                            fade6.setFromValue(0);
                            fade6.setToValue(1);
                            fade6.play();

                            fade6.setOnFinished(event6 -> {
                                FadeTransition fade7 = new FadeTransition(Duration.seconds(3), pane1);
                                fade7.setFromValue(0);
                                fade7.setToValue(1);
                                fade7.play();

                                fade7.setOnFinished(event2 -> addAnimation());
                            });

                        });
                    });
                });
            });
        });
    }


    private void addAnimation () {

        FadeTransition fade0 = new FadeTransition(Duration.seconds(3), pane4);
        fade0.setFromValue(1);
        fade0.setToValue(0);
        fade0.play();

        fade0.setOnFinished(event -> {
            FadeTransition fade1 = new FadeTransition(Duration.seconds(3), pane3);
            fade1.setFromValue(1);
            fade1.setToValue(0);
            fade1.play();

            fade1.setOnFinished(event1 -> {
                FadeTransition fade2 = new FadeTransition(Duration.seconds(3), pane2);
                fade2.setFromValue(1);
                fade2.setToValue(0);
                fade2.play();

                fade2.setOnFinished(event2 -> {
                    FadeTransition fade3 = new FadeTransition(Duration.seconds(3), pane1);
                    fade3.setFromValue(1);
                    fade3.setToValue(0);
                    fade3.play();

                    fade3.setOnFinished(event3 -> {
                        FadeTransition fade4 = new FadeTransition(Duration.seconds(3), pane1);
                        fade4.setFromValue(0);
                        fade4.setToValue(1);
                        fade4.play();

                        fade4.setOnFinished(event4 -> {
                            FadeTransition fade5 = new FadeTransition(Duration.seconds(3), pane2);
                            fade5.setFromValue(0);
                            fade5.setToValue(1);
                            fade5.play();

                            fade5.setOnFinished(event5 -> {
                                FadeTransition fade6 = new FadeTransition(Duration.seconds(3), pane3);
                                fade6.setFromValue(0);
                                fade6.setToValue(1);
                                fade6.play();

                                fade6.setOnFinished(event6 -> {
                                    FadeTransition fade7 = new FadeTransition(Duration.seconds(3), pane4);
                                    fade7.setFromValue(0);
                                    fade7.setToValue(1);
                                    fade7.play();

                                    fade7.setOnFinished(event7 -> addAnimation());
                                });

                            });
                        });
                    });
                });
            });

        });
    }

    public FadeTransition fadeAnimation ( double duration, Node node, double from, double to ) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        fadeTransition.play();
        return fadeTransition;
    }

    public TranslateTransition translateAnimation(double duration, Node node, double byX , double byY){
        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(duration),node);
        translateTransition.setToX(byX);
        translateTransition.setToY(byY);
        translateTransition.play();
        return translateTransition;
    }

}


