package com.example.taixesf;

import com.example.taixesf.model.UserObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    private UserObject user;

    public UserObject getUser() {
        return user;
    }

    @FXML
    private ImageView avatar;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button btnLich;

    @FXML
    private Label hoten;

    @FXML
    private Label sdt;

    @FXML
    private Button sansang;

    public void xemLich(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/lich.fxml"));
        AnchorPane view= fxmlLoader.load();
        borderpane.setCenter(view);
        XemLichController controller=fxmlLoader.getController();
        controller.setUser(user);
    }
    public void trangThai(ActionEvent e) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("views/trangthai.fxml"));
        borderpane.setCenter(view);
    }
    public void xemthongbao(ActionEvent e) throws IOException {
        AnchorPane view= FXMLLoader.load(getClass().getResource("views/thongbao.fxml"));
        borderpane.setCenter(view);
    }
    public void xemvi(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/xemvi.fxml"));
        AnchorPane view= fxmlLoader.load();
        borderpane.setCenter(view);
        XemViController controller=fxmlLoader.getController();
        controller.setUser(user);
    }
    public void thongTin(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/thongtin.fxml"));
        AnchorPane view= fxmlLoader.load();
        borderpane.setCenter(view);
        XemThongTinUserController controller=fxmlLoader.getController();
        controller.setUser(user);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setUser(UserObject u){
        user=u;
        String imagePath = user.getUser_image();
        Image image = new Image(getClass().getResource("images/" + imagePath).toExternalForm());
        avatar.setImage(image);
        hoten.setText(user.getUser_name());
        sdt.setText(user.getUser_phone());
    }
}
