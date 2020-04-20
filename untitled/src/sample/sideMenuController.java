package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class sideMenuController implements Initializable{
    @FXML
    public GridPane secondGrid;
    @FXML
    public GridPane mainGrid;
    @FXML
    public JFXButton capuchinoPaneButton;
    @FXML
    public JFXButton homeCappucinoButton;
    @FXML
    public JFXButton capuchinoButton;
    @FXML
    public AnchorPane splashScreen;
    @FXML
    public AnchorPane bluePaneClients;
    @FXML
    public AnchorPane clientTableMain;
    @FXML
    public AnchorPane redPaneReturns;
    @FXML
    public AnchorPane greenPaneDelivery;
    @FXML
    public AnchorPane brownPaneProducts;
    @FXML
    public AnchorPane chapuchinoPaneInvoice ,tableDeliveryPane,tableProductsPane, tableInvoicePane, tableRurnsPane;
    @FXML
    public AnchorPane invoiceTable;


    @FXML
    private JFXButton  exitButton, showButton, brownButton, greenButton, redButton, blueButton,
            homeBlueButton,  homeGreenButton, homeRedButton, homeBrownButton, bluePanelButton,
            redPanelButton, greenPanelButton, brownPanelButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exitButton.setGraphic(new ImageView(new Image("sample/ic_clear_white_24.png")));
        blueButton.setVisible(false);
        bluePaneClients.setDisable(true);
        redPaneReturns.setDisable(true);
        greenPaneDelivery.setDisable(true);
        brownPaneProducts.setDisable(true);
        chapuchinoPaneInvoice.setDisable(true);
//        redButton.setVisible(false);
//        greenButton.setVisible(false);
//        brownButton.setVisible(false);
//        capuchinoButton.setVisible(false);
        fadeAnimation(1, bluePaneClients, 1, 0);
        fadeAnimation(1, redPaneReturns, 1, 0);
        fadeAnimation(1, greenPaneDelivery, 1, 0);
        fadeAnimation(1, brownPaneProducts, 1, 0);
        fadeAnimation(1, chapuchinoPaneInvoice, 1, 0);

        TranslateTransition translateTransition = translateAnimation(.1, exitButton, -300, 0);
        translateTransition.setOnFinished(event -> {
            TranslateTransition translateTransition2 = translateAnimation(.1, capuchinoPaneButton, -300, 0);
            translateTransition2.setOnFinished(event2 -> {
                TranslateTransition translateTransition3 = translateAnimation(.1, brownPanelButton, -300, 0);
                translateTransition3.setOnFinished(event3 -> {
                    TranslateTransition translateTransition4 = translateAnimation(.1, greenPanelButton, -300, 0);
                    translateTransition4.setOnFinished(event4 -> {
                        TranslateTransition translateTransition5 = translateAnimation(.1, redPanelButton, -300, 0);
                        translateTransition5.setOnFinished(event5 -> translateAnimation(.1, bluePanelButton, -300, 0));
                    });
                });
            });
        });


        showButton.setOnAction(event -> {
            showSideMenu();
            translateAnimation(.1, showButton, -300, 0);
            secondGrid.setVisible(false);
            mainGrid.setVisible(true);
        });

        exitButton.setOnAction(event -> {
            HideSideMenu();
            translateAnimation(.1, showButton, 0, 0);

//            mainGrid.setVisible(false);
            secondGrid.setVisible(true);
        });

        bluePanelButton.setOnAction(event->{
            blueButton.setVisible(true);
         FadeTransition fadeTransition = fadeAnimation(.01,blueButton,0,1);
            fadeTransition.setOnFinished(event2 -> {
                ScaleTransition scaleTransition=scaleTransition(.1,blueButton,90,90);
                blueButton.setDisable(true);
                scaleTransition.setOnFinished(event3 -> {
                    FadeTransition fadeTransition1 = fadeAnimation(.3,homeBlueButton,0,1);
                    fadeTransition1.setOnFinished(event4 -> {
                        translateAnimation(.3,homeBlueButton,0,-50);
                        bluePaneClients.setDisable(false);
                        bluePaneClients.setVisible(true);
                        fadeAnimation(1, bluePaneClients, 0, 1);
                        HideSideMenu();
                    });
                });
            });
        });

        homeBlueButton.setOnAction(event->{
            TranslateTransition blueButtonTransition = translateAnimation(.2,homeBlueButton,0,50);
            blueButtonTransition.setOnFinished(event1 -> {
                FadeTransition fadeTransition=fadeAnimation(.2,homeBlueButton,1,0);
                fadeTransition.setOnFinished(event2 -> {
                    FadeTransition fadeTransition1 =  fadeAnimation(1, bluePaneClients, 1, 0);
                    fadeTransition1.setOnFinished(event3 -> {
                        fadeAnimation(1, bluePaneClients, 1, 0);
                        scaleTransition(.3, blueButton, -90, -90);
                        fadeAnimation(.3, blueButton, 1, 0);

                        bluePaneClients.setVisible(false);
                        blueButton.setDisable(true);
                       //                    fadeAnimation(0.3,transparentAnchor, 1,0);
                        showSideMenu();
                    });
                });
            });
        });
        fadeAnimation(.2, homeBlueButton, 1, 0);

        redPanelButton.setOnAction(event->{
            redButton.setVisible(true);
            FadeTransition fadeTransition = fadeAnimation(.01,redButton,0,1);
            fadeTransition.setOnFinished(event2 -> {
                ScaleTransition scaleTransition=scaleTransition(.1,redButton,90,90);
                redButton.setDisable(true);
                scaleTransition.setOnFinished(event3 -> {
                    FadeTransition fadeTransition1 = fadeAnimation(.3,homeRedButton,0,1);
                    fadeTransition1.setOnFinished(event4 -> {
                        translateAnimation(.3,homeRedButton,0,-50);
                        redPaneReturns.setDisable(false);
                        redPaneReturns.setVisible(true);
                        fadeAnimation(1, redPaneReturns, 0, 1);
                        HideSideMenu();
                    });
                });
            });
        });

        homeRedButton.setOnAction(event->{
            TranslateTransition blueButtonTransition = translateAnimation(.2,homeRedButton,0,50);
            blueButtonTransition.setOnFinished(event1 -> {
                FadeTransition fadeTransition=fadeAnimation(.2,homeRedButton,1,0);
                fadeTransition.setOnFinished(event2 -> {
                   FadeTransition fadeTransition1 =  fadeAnimation(1, redPaneReturns, 1, 0);
                    fadeTransition1.setOnFinished(event3 -> {
                        fadeAnimation(1, redPaneReturns, 1, 0);
                        scaleTransition(.3, redButton, -90, -90);
                        fadeAnimation(.3, redButton, 1, 0);

                        redPaneReturns.setVisible(false);
                        redButton.setDisable(true);
                        showSideMenu();
                    });
                });
            });
        });
        fadeAnimation(.2, homeRedButton, 1, 0);

        greenPanelButton.setOnAction(event->{
            greenButton.setVisible(true);
            FadeTransition fadeTransition = fadeAnimation(.01,greenButton,0,1);
            fadeTransition.setOnFinished(event2 -> {
                ScaleTransition scaleTransition=scaleTransition(.1,greenButton,90,90);
                greenButton.setDisable(true);
                scaleTransition.setOnFinished(event3 -> {
                    FadeTransition fadeTransition1 = fadeAnimation(.3,homeGreenButton,0,1);
                    fadeTransition1.setOnFinished(event4 -> {
                        translateAnimation(.3,homeGreenButton,0,-50);
                        greenPaneDelivery.setDisable(false);
                        greenPaneDelivery.setVisible(true);

                        fadeAnimation(1, greenPaneDelivery, 0, 1);
                        HideSideMenu();
                    });
                });
            });
        });

        homeGreenButton.setOnAction(event->{
            TranslateTransition blueButtonTransition = translateAnimation(.2,homeGreenButton,0,50);
            blueButtonTransition.setOnFinished(event1 -> {
                FadeTransition fadeTransition=fadeAnimation(.2,homeGreenButton,1,0);
                fadeTransition.setOnFinished(event2 -> {
                    FadeTransition fadeTransition1 =  fadeAnimation(1, greenPaneDelivery, 1, 0);
                    fadeTransition1.setOnFinished(event3 -> {
                        fadeAnimation(1, greenPaneDelivery, 1, 0);
                        scaleTransition(.3, greenButton, -90, -90);
                        fadeAnimation(.3, greenButton, 1, 0);

                        greenPaneDelivery.setVisible(false);
                        greenButton.setDisable(true);
                                          showSideMenu();
                    });
                });
            });
        });
        fadeAnimation(.2, homeGreenButton, 1, 0);

        brownPanelButton.setOnAction(event->{
            brownButton.setVisible(true);
            FadeTransition fadeTransition = fadeAnimation(.01,brownButton,0,1);
            fadeTransition.setOnFinished(event2 -> {
                ScaleTransition scaleTransition=scaleTransition(.1,brownButton,90,90);
                brownButton.setDisable(true);
                scaleTransition.setOnFinished(event3 -> {
                    FadeTransition fadeTransition1 = fadeAnimation(.3,homeBrownButton,0,1);
                    fadeTransition1.setOnFinished(event4 -> {
                        translateAnimation(.3,homeBrownButton,0,-50);
                        brownPaneProducts.setDisable(false);
                        brownPaneProducts.setVisible(true);
                        fadeAnimation(1, brownPaneProducts, 0, 1);
                        HideSideMenu();
                    });
                });
            });
        });

        homeBrownButton.setOnAction(event->{
            TranslateTransition blueButtonTransition = translateAnimation(.2,homeBrownButton,0,50);
            blueButtonTransition.setOnFinished(event1 -> {
                FadeTransition fadeTransition=fadeAnimation(.2,homeBrownButton,1,0);
                fadeTransition.setOnFinished(event2 -> {
                    FadeTransition fadeTransition1 =  fadeAnimation(1, tableProductsPane, 1, 0);
                    fadeTransition1.setOnFinished(event3 -> {
                        fadeAnimation(1, tableProductsPane, 1, 0);
                        scaleTransition(.3, brownButton, -90, -90);
                        fadeAnimation(.3, brownButton, 1, 0);

                        tableProductsPane.setVisible(false);
                        blueButton.setDisable(true);
                                       showSideMenu();
                    });
                });
            });
        });
        fadeAnimation(.2, homeBrownButton, 1, 0);

        capuchinoPaneButton.setOnAction(event->{
            capuchinoButton.setVisible(true);
            FadeTransition fadeTransition = fadeAnimation(.01,capuchinoButton,0,1);
            fadeTransition.setOnFinished(event2 -> {
                ScaleTransition scaleTransition=scaleTransition(.1,capuchinoButton,90,90);
                capuchinoButton.setDisable(true);
                scaleTransition.setOnFinished(event3 -> {
                    FadeTransition fadeTransition1 = fadeAnimation(.3,homeCappucinoButton,0,1);
                    fadeTransition1.setOnFinished(event4 -> {
                        translateAnimation(.3,homeCappucinoButton,0,-50);
                        chapuchinoPaneInvoice.setDisable(false);
                        chapuchinoPaneInvoice.setVisible(true);
                        fadeAnimation(1, chapuchinoPaneInvoice, 0, 1);
                        HideSideMenu();
                    });
                });
            });
        });

        homeCappucinoButton.setOnAction(event->{

            TranslateTransition blueButtonTransition = translateAnimation(.2,homeCappucinoButton,0,50);
            blueButtonTransition.setOnFinished(event1 -> {
                FadeTransition fadeTransition=fadeAnimation(.2,homeCappucinoButton,1,0);
                fadeTransition.setOnFinished(event2 -> {
                    FadeTransition fadeTransition1 =  fadeAnimation(1, chapuchinoPaneInvoice, 1, 0);
                    fadeTransition1.setOnFinished(event3 -> {
                        fadeAnimation(1, chapuchinoPaneInvoice, 1, 0);
                        scaleTransition(.3, capuchinoButton, -90, -90);
                        fadeAnimation(.3, capuchinoButton, 1, 0);

                        chapuchinoPaneInvoice.setVisible(false);
                        capuchinoButton.setDisable(true);
                                         showSideMenu();
                    });
                });
            });
        });
        fadeAnimation(.2, homeCappucinoButton, 1, 0);

    }

    public void showSideMenu() {

        TranslateTransition translateTransition = translateAnimation(.1, exitButton, 0, 0);
        translateTransition.setOnFinished(event -> {
            TranslateTransition translateTransition2 = translateAnimation(.1, capuchinoPaneButton, 0, 0);
            translateTransition2.setOnFinished(event2 -> {
                TranslateTransition translateTransition3 = translateAnimation(.1, brownPanelButton, 0, 0);
                translateTransition3.setOnFinished(event3 -> {
                    TranslateTransition translateTransition4 = translateAnimation(.1, greenPanelButton, 0, 0);
                    translateTransition4.setOnFinished(event4 -> {
                        TranslateTransition translateTransition5 = translateAnimation(.1, redPanelButton, 0, 0);
                        translateTransition5.setOnFinished(event5 -> translateAnimation(.1, bluePanelButton, 0, 0));
                    });
                });
            });
        });
    }

    public void HideSideMenu() {

        TranslateTransition translateTransition = translateAnimation(.1, exitButton, -300, 0);
        translateTransition.setOnFinished(event -> {
            TranslateTransition translateTransition2 = translateAnimation(.1, capuchinoPaneButton, -300, 0);
            translateTransition2.setOnFinished(event2 -> {
                TranslateTransition translateTransition3 = translateAnimation(.1, brownPanelButton, -300, 0);
                translateTransition3.setOnFinished(event3 -> {
                    TranslateTransition translateTransition4 = translateAnimation(.1, greenPanelButton, -300, 0);
                    translateTransition4.setOnFinished(event4 -> {
                        TranslateTransition translateTransition5 = translateAnimation(.1, redPanelButton, -300, 0);
                        translateTransition5.setOnFinished(event5 -> translateAnimation(.1, bluePanelButton, -300, 0));
                    });
                });
            });
        });
    }

    public FadeTransition fadeAnimation(double duration, Node node, double from , double to){
        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(duration),node);
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

    public ScaleTransition scaleTransition(double duration, Node node, double byX , double byY){
        ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(duration),node);
        scaleTransition.setByX(byX);
        scaleTransition.setByY(byY);
        scaleTransition.play();
        return scaleTransition;
    }



}
