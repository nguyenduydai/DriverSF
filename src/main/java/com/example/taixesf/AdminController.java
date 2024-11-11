package com.example.taixesf;

import com.example.taixesf.model.UserObject;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private UserObject user;

    @FXML
    private AnchorPane anchor;

    public UserObject getUser() {
        return user;
    }

    public void setUser(UserObject u) {
        this.user = u;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }

    public void setBorderpane(BorderPane borderpane) {
        this.borderpane = borderpane;
    }
    public void hien() throws IOException {


        AnchorPane view= FXMLLoader.load(getClass().getResource("views/suataixe.fxml"));
        borderpane.setCenter(view);
    }


    @FXML
    public BorderPane borderpane;
    @FXML
    private Label menuback;
    @FXML
    private Label menu;
    @FXML
    private AnchorPane slider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setTranslateX(-250);
        menu.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-250);
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(false);
                menuback.setVisible(true);
            });
        });

        menuback.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-250);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(true);
                menuback.setVisible(false);
            });
        });
    }

    public void troLai(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("views/dangnhap.fxml"));
        Parent dangnhap=loader.load();
        Scene scene=new Scene(dangnhap);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void qlLich(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/qlLich.fxml"));
        AnchorPane view = loader.load();
        AdminCaController controller = loader.getController();
        controller.setBorderPane(borderpane);
        borderpane.setCenter(view);
    }
    public void thongke(ActionEvent e) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("views/thongke.fxml"));
        borderpane.setCenter(view);
    }
    public void hieusuat(ActionEvent e) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("views/hieusuat.fxml"));
        borderpane.setCenter(view);
    }
    public void qltaixe(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/qltaixe.fxml"));
        AnchorPane view = loader.load();
        AdminTaiXeController controller = loader.getController();
        controller.setBorderPane(borderpane);
        borderpane.setCenter(view);
    }
    public void qlCa(ActionEvent e) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("views/qlCa.fxml"));
        borderpane.setCenter(view);
    }
    public void qlkv(ActionEvent e) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("views/qlkv.fxml"));
        borderpane.setCenter(view);
    }
    @FXML
    void thongtin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/thonginAdmin.fxml"));
        AnchorPane view = loader.load();
        AdminTTController controller = loader.getController();
        controller.setUser(user);
        borderpane.setCenter(view);
    }
}
