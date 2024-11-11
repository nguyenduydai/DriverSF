package com.example.taixesf.controller;

import com.example.taixesf.dao.UserFunction;
import com.example.taixesf.dao.implement.UserFunctionImpl;
import com.example.taixesf.model.UserObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DangKyController implements Initializable {
    private UserObject user=new UserObject();
    private UserFunction uf=new UserFunctionImpl();
    @FXML
    private Button btnDangKy ;
    @FXML
    private TextField hoten;

    @FXML
    private TextField diachi;
    @FXML
    private TextField sdt;
    @FXML
    private TextField mk;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private RadioButton nam;
    @FXML
    private RadioButton nu;
    @FXML
    private Label alert;
    @FXML
    private ComboBox<String> khuvuc;

    private ObservableList<String> khuvucs= FXCollections.observableArrayList("Nam Từ Liêm","Bắc Từ Liêm","Cầu Giấy","Hồ Tây");
    @FXML
    private ComboBox<String> role;
    @FXML
    private ObservableList<String> roles= FXCollections.observableArrayList("Tài xế","Nhân viên quản lý ca","Nhân viên quản lý nhân sự","Nhân viên quản lý hành chính","Quản trị viên");
    @FXML
    private ImageView avatar;
    @FXML
    private HBox hbox;
    private String loi;
    public void chonAnh(ActionEvent e){
        Stage stage=(Stage)hbox.getScene().getWindow();
        FileChooser fc=new FileChooser();
        fc.setTitle("Chọn ảnh");
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Image files","*.jpg","*png");
        fc.getExtensionFilters().add(filter);
        File file=fc.showOpenDialog(stage);
        if(file!=null){
            Image image=new Image(file.toURI().toString(),140,85,false,true);
            avatar.setImage(image);
            String[] parts = file.toURI().toString().split("/");
            String fileimage = parts[parts.length - 1];
            user.setUser_image(fileimage);
        }
    }
    public void troLai(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("views/dangnhap.fxml"));
        Parent dangnhap=loader.load();
        Scene scene=new Scene(dangnhap);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    private boolean check(){
        try{
            int tuoi=Integer.parseInt(age.getText());
            if(tuoi<18 || tuoi > 60){
                loi="Tuổi không đủ điều kiện";
                return false;
            }
            if(!email.getText().endsWith("@gmail.com")){
                loi="Email không đúng định dạng";
                return false;
            }

        }catch (Exception ex){
            loi="Lỗi dữ liệu";
            return false;
        }
        return true;
    }
    public void dangKy(ActionEvent e){
        if(sdt.getText().trim().equals("")||hoten.getText().trim().equals("")||mk.getText().trim().equals("")||
                diachi.getText().trim().equals("")||email.getText().trim().equals("")||age.getText().trim().equals(""))
            alert.setText("Hãy nhập đủ các trường dữ liệu");
        else {
            if(check()){
                user.setUser_phone(sdt.getText());
                user.setUser_name(hoten.getText());
                user.setUser_password(mk.getText());
                user.setUser_hometown(diachi.getText());
                user.setUser_email(email.getText());
                user.setUser_age(Integer.parseInt(age.getText()));
                if(nam.isSelected())    user.setUser_sex("Nam");
                else if (nu.isSelected())    user.setUser_sex("Nữ");
                if(khuvuc.getValue()=="Nam Từ Liêm") user.setUser_area_id(1);
                else if(khuvuc.getValue()=="Bắc Từ Liêm") user.setUser_area_id(4);
                else if(khuvuc.getValue()=="Cầu Giấy") user.setUser_area_id(7);
                else if(khuvuc.getValue()=="Hồ Tây") user.setUser_area_id(10);
                if(role.getValue()=="Tài xế") user.setUser_role_id(1);
                else if(role.getValue()=="Nhân viên quản lý ca") user.setUser_role_id(2);
                else if(role.getValue()=="Nhân viên quản lý nhân sự") user.setUser_role_id(3);
                else if(role.getValue()=="Nhân viên quản lý hành chính") user.setUser_role_id(4);
                else if(role.getValue()=="Quản trị viên") user.setUser_role_id(5);
                LocalDate localDate = LocalDate.now();
                Timestamp now = Timestamp.valueOf(localDate.atStartOfDay());
                user.setCreatedDate(now);
                user.setUser_status(2);
                if(uf.addUser(user))
                    alert.setText("Gửi đơn đăng ký thành công");
            }else{
                alert.setText(loi);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nam.isSelected();
        khuvuc.setItems(khuvucs);
        khuvuc.setValue(khuvucs.get(0));
        role.setItems(roles);
        role.setValue(roles.get(0));
    }
}
