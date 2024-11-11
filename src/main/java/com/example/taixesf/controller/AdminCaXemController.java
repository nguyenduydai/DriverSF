package com.example.taixesf.controller;

import com.example.taixesf.dao.AreaFunction;
import com.example.taixesf.dao.HubFunction;
import com.example.taixesf.dao.UserFunction;
import com.example.taixesf.dao.WorkingshiftFunction;
import com.example.taixesf.dao.implement.AreaFunctionImpl;
import com.example.taixesf.dao.implement.HubFunctionImpl;
import com.example.taixesf.dao.implement.UserFunctionImpl;
import com.example.taixesf.dao.implement.WorkingshiftFunctionImpl;
import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.HubObject;
import com.example.taixesf.model.UserObject;
import com.example.taixesf.model.WorkingshiftObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminCaXemController implements Initializable {

    @FXML
    private TableColumn<UserObject, String> ageCol;

    @FXML
    private DatePicker date;

    @FXML
    private TableColumn<UserObject, String> emailCol;

    @FXML
    private TableColumn<UserObject, String> hometownCol;

    @FXML
    private TableColumn<UserObject, String> idCol;

    @FXML
    private TableColumn<UserObject,ImageView> imageCol;

    @FXML
    private TextField kv;

    @FXML
    private TableColumn<UserObject ,String> monthCol;

    @FXML
    private TableColumn<UserObject, String> nameCol;

    @FXML
    private TableColumn<UserObject, String> phoneCol;

    @FXML
    private TableColumn<UserObject, String> sexCol;

    @FXML
    private TextField sltx;

    @FXML
    private TableView<UserObject> table;

    @FXML
    private TextField ten;

    @FXML
    private TextField tien;

    @FXML
    private TextField tt;


    @FXML
    private BorderPane borderPane;

    @FXML
    public  void setBorderPane(BorderPane p){
        borderPane=p;
    }
    private WorkingshiftObject w;

    public WorkingshiftObject getW() {
        return w;
    }
    private HubFunction hf=new HubFunctionImpl();
    private AreaFunction af=new AreaFunctionImpl();
    private UserFunction uf=new UserFunctionImpl();
    private WorkingshiftFunction wf=new WorkingshiftFunctionImpl();
    private ObservableList<UserObject> listuser;
    private ArrayList<UserObject> listu=new ArrayList<>();


    public void setW(WorkingshiftObject w) {
        this.w = w;
        ten.setText(w.getWorkingshift_name());
        date.setValue(w.getWorkingshift_date().toLocalDateTime().toLocalDate());
        tien.setText(String.valueOf(w.getWorkingshift_money()));

        ArrayList<HubObject> h=hf.getHubByWorkingshift(w.getId());
        String status="";
        if(h.size()>0) {
            status="Đã được đăng ký";
            if(w.getWorkingshift_drivernumber()==0) status="Đã đủ số lượng";
        }
        else status="Chưa được đăng ký";
        tt.setText(status);
        sltx.setText(String.valueOf(w.getWorkingshift_drivernumber()));
        int area_id = w.getWorkingshift_area_id();
        AreaObject area= af.getArea(area_id);
        kv.setText(area.getArea_code());

        for (HubObject ho:h) {
            int user_id=ho.getHub_user_id();
            listu.add(uf.getUser(user_id));
        }
        listuser =  FXCollections.observableArrayList(listu);
        table.setItems(listuser);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listuser =  FXCollections.observableArrayList(listu);
        idCol.setCellValueFactory(cellData -> {UserObject user = cellData.getValue();
            return new SimpleStringProperty(String.valueOf(user.getId()));         });
        ageCol.setCellValueFactory(cellData -> {UserObject user = cellData.getValue();
            return new SimpleStringProperty(String.valueOf(user.getUser_age()));         });
        nameCol.setCellValueFactory(cellData -> {UserObject user = cellData.getValue();
            return new SimpleStringProperty(user.getUser_name());         });
        phoneCol.setCellValueFactory(cellData -> {UserObject user = cellData.getValue();
            return new SimpleStringProperty(user.getUser_phone());         });
        sexCol.setCellValueFactory(cellData -> {UserObject user = cellData.getValue();
            return new SimpleStringProperty(user.getUser_sex());         });
        emailCol.setCellValueFactory(cellData -> {UserObject user = cellData.getValue();
            return new SimpleStringProperty(user.getUser_email());         });
        hometownCol.setCellValueFactory(cellData -> {UserObject user = cellData.getValue();
            return new SimpleStringProperty(user.getUser_hometown());         });
        imageCol.setCellValueFactory(cellData -> {
            UserObject user = cellData.getValue();
            String imagePath = user.getUser_image();
            Image image = new Image(getClass().getResource("images/" + imagePath).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            return new SimpleObjectProperty<>(imageView);
        });
        monthCol.setCellValueFactory(cellData -> {
            UserObject user = cellData.getValue();
            Timestamp createdTimestamp = user.getCreatedDate();
            LocalDate createdDate = createdTimestamp.toLocalDateTime().toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(createdDate, currentDate);
            int months = period.getMonths();
            int years=period.getYears();
            if(years>0) months+=years*12;
            return new SimpleStringProperty(String.valueOf(months)+"tháng");
        });
        table.setItems(listuser);
    }
    @FXML
    void sua(ActionEvent event) {
        try{
            w.setWorkingshift_money(Integer.parseInt(tien.getText()));
            w.setWorkingshift_money(Integer.parseInt(sltx.getText()));
            w.setWorkingshift_date(Timestamp.valueOf(date.getValue().atStartOfDay()));
            wf.editWorkingshift(w);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Lỗi nhập dữ liệu");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }

    }

    @FXML
    void xoa(ActionEvent event) {
        if(wf.delWorkingshift(w)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Xóa thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Không thể xóa ca làm việc này");
            alert.showAndWait();
        }

    }
    @FXML
    void thoat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/qlLich.fxml"));
        AnchorPane view = loader.load();
        AdminCaController controller = loader.getController();
        controller.setBorderPane(borderPane);
        borderPane.setCenter(view);
    }
}
