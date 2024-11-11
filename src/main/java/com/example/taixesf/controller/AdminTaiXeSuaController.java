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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminTaiXeSuaController implements Initializable {
    private UserObject user=new UserObject();
    private UserFunction uf=new UserFunctionImpl();

    @FXML
    private TextField hoten;

    @FXML
    private TextField diachi;
    @FXML
    private TextField sdt;
    @FXML
    private TextField mk;

    @FXML
    private TextField age;
    @FXML
    private TextField email;

    @FXML
    private RadioButton nam;
    @FXML
    private RadioButton nu;
    @FXML
    private ComboBox<String> khuvuc;
    private ObservableList<String> khuvucs= FXCollections.observableArrayList("Nam Từ Liêm","Bắc Từ Liêm","Cầu Giấy","Hồ Tây","Hoài Đức");

    @FXML
    private TextField role;
    @FXML
    private ImageView avatar;
    @FXML
    private VBox hbox;
    @FXML
    private BorderPane borderPane;

    @FXML
    public  void setBorderPane(BorderPane p){
        borderPane=p;
    }

    public void chonAnh(ActionEvent e){
        Stage stage=(Stage)hbox.getScene().getWindow();
        FileChooser fc=new FileChooser();
        fc.setTitle("Chọn ảnh");
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Image files","*.jpg","*png");
        fc.getExtensionFilters().add(filter);
        File file=fc.showOpenDialog(stage);
        if(file!=null){
            Image image=new Image(file.toURI().toString(),140,85,false,true);
            System.out.println(file.toURI().toString());
            avatar.setImage(image);
            String[] parts = file.toURI().toString().split("/");
            String fileimage = parts[parts.length - 1];
            user.setUser_image(fileimage);
        }
    }

    public void luu(ActionEvent e){
        if(check()){
            user.setUser_phone(sdt.getText());
            user.setUser_name(hoten.getText());
            user.setUser_password(mk.getText());
            user.setUser_hometown(diachi.getText());
            user.setUser_email(email.getText());
            user.setUser_age(Integer.parseInt(age.getText()));
            user.setUser_status(Integer.parseInt(tt.getText()));
            if(nam.isSelected())    user.setUser_sex("Nam");
            else if (nu.isSelected())    user.setUser_sex("Nữ");
            if(khuvuc.getValue()=="Nam Từ Liêm") user.setUser_area_id(1);
            else if(khuvuc.getValue()=="Bắc Từ Liêm") user.setUser_area_id(4);
            else if(khuvuc.getValue()=="Cầu Giấy") user.setUser_area_id(7);
            else if(khuvuc.getValue()=="Hồ Tây") user.setUser_area_id(10);
            else if(khuvuc.getValue()=="Hoài Đức") user.setUser_area_id(13);
            LocalDate localDate = LocalDate.now();
            Timestamp now = Timestamp.valueOf(localDate.atStartOfDay());
            user.setModifiedDate(now);
            uf.editUser(user);
            System.out.println(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Đây là một thông báo");
            alert.setContentText("Lưu người dùng thành công");
            alert.showAndWait();
        }

    }
    @FXML
    private TextField txtid;
    @FXML
    private TextField tt;

    public void nhap(ActionEvent e){
        user=uf.getUser(Integer.parseInt(txtid.getText()));
        setUser(user);
    }
    private boolean check(){
        try{
            int tuoi=Integer.parseInt(age.getText());
            if(tuoi<18 || tuoi > 60){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Đây là một thông báo");
                alert.setContentText("Tuổi không đủ điều kiện");
                alert.showAndWait();
                return false;
            }
            if(!email.getText().endsWith("@gmail.com")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Đây là một thông báo");
                alert.setContentText("Email không đúng định dạng");
                alert.showAndWait();
                return false;
            }

        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Đây là một thông báo");
            alert.setContentText(ex.toString());
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public void setUser(UserObject u){
        hoten.setText(u.getUser_name());
        sdt.setText(u.getUser_phone());
        mk.setText(u.getUser_password());
        diachi.setText(u.getUser_hometown());
        email.setText(u.getUser_email());
        if(u.getUser_sex()=="Nam") nam.isSelected();
        else nu.isSelected();
        mk.setText(u.getUser_password());
        age.setText(String.valueOf(u.getUser_age()));
        tt.setText(String.valueOf(u.getUser_status()));
        if(u.getUser_area_id()<=3)  khuvuc.setValue("Nam Từ Liêm");
        else if(u.getUser_area_id()<=6)  khuvuc.setValue("Bắc Từ Liêm");
        else if(u.getUser_area_id()<=9)  khuvuc.setValue("Cầu Giấy");
        else if(u.getUser_area_id()<=12)  khuvuc.setValue("Hồ Tây");
        else if(u.getUser_area_id()<=15)  khuvuc.setValue("Hoài Đức");
        String imagePath = u.getUser_image();
        Image image = new Image(getClass().getResource("images/" + imagePath).toExternalForm());
        avatar.setImage(image);
    }

    @FXML
    void thoat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/qltaixe.fxml"));
        AnchorPane view = loader.load();
        AdminTaiXeController controller = loader.getController();
        controller.setBorderPane(borderPane);
        borderPane.setCenter(view);
    }

    @FXML
    void xoa(ActionEvent event) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Xác nhận xóa");
        alert2.setHeaderText("Bạn có chắc muốn xóa " + user.getUser_name() + " không?");
        alert2.setContentText("Hành động này không thể hoàn tác.");
        ButtonType buttonTypeYes = new ButtonType("Có");
        ButtonType buttonTypeNo = new ButtonType("Không");
        alert2.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        if (alert2.showAndWait().get() == buttonTypeYes) {
            UserFunction uf=new UserFunctionImpl();
            uf.delUser(user);
        } else {
            System.out.println("Không xóa " + user.getUser_name());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khuvuc.setItems(khuvucs);
    }
}
