package com.example.taixesf.controller;

import com.example.taixesf.dao.UserFunction;
import com.example.taixesf.dao.implement.UserFunctionImpl;
import com.example.taixesf.model.UserObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DangNhapController {
    @FXML
    private Button  btnDN;

    @FXML
    private Button btnDK ;

    @FXML
    private TextField sdt;

    @FXML
    private Label error;


    @FXML
    private PasswordField password;


    public void dangky(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/dangky.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Đăng ký");
        stage.setScene(scene);
        stage.show();
    }
    public void dangnhap(ActionEvent e) throws IOException {
        UserFunction uf=new UserFunctionImpl();
        UserObject user=new UserObject();
        String phone=sdt.getText();
        String pass=password.getText();
        if(phone.trim().equals("")||pass.trim().equals(""))
            error.setText("Hãy nhập đủ các trường !");
        else{
            user= uf.getUser(phone,pass);
            if(user==null){
                error.setText("Người dùng này không tồn tại !");
            }else{
                if(user.getUser_status()==2)
                    error.setText("Người dùng đang được xem xét đơn đăng ký !");
                else if (user.getUser_status()==1) {
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader=null;
                    Scene scene = null;
                    if(user.getUser_role_id()==1){
                        fxmlLoader = new FXMLLoader(getClass().getResource("views/user.fxml"));
                        scene = new Scene(fxmlLoader.load());
                        UserController controller=fxmlLoader.getController();
                        controller.setUser(user);
                    }else if(user.getUser_role_id()>=2){
                        fxmlLoader = new FXMLLoader(getClass().getResource("views/admin.fxml"));
                        scene = new Scene(fxmlLoader.load());
                        AdminController controller=fxmlLoader.getController();
                        controller.setUser(user);
                    }
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    stage.setTitle("Màn hình người dùng");
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                }
            }
        }
    }
}
