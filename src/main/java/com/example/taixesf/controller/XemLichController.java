package com.example.taixesf.controller;

import com.example.taixesf.dao.AreaFunction;
import com.example.taixesf.dao.HubFunction;
import com.example.taixesf.dao.WorkingshiftFunction;
import com.example.taixesf.dao.implement.AreaFunctionImpl;
import com.example.taixesf.dao.implement.HubFunctionImpl;
import com.example.taixesf.dao.implement.WorkingshiftFunctionImpl;
import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.HubObject;
import com.example.taixesf.model.UserObject;
import com.example.taixesf.model.WorkingshiftObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class XemLichController implements Initializable {
    @FXML
    private Button t2;

    @FXML
    private Button t3;

    @FXML
    private Button t4;

    @FXML
    private Button t5;

    @FXML
    private Button t6;

    @FXML
    private Button t7;

    @FXML
    private Button t8;


    @FXML
    private TableColumn<WorkingshiftObject, String> tien;

    @FXML
    private ComboBox<String> cbkv;

    @FXML
    private TableColumn<WorkingshiftObject, String> kv;

    @FXML
    private TableColumn<WorkingshiftObject, String> ngay;

    @FXML
    private TableColumn<WorkingshiftObject, String> ten;

    @FXML
    private TableColumn<WorkingshiftObject, String> thu;
    @FXML
    private TableColumn<WorkingshiftObject, String> tt;

    @FXML
    private ToggleButton toggle;
    private boolean daDK=false;


    @FXML
    private TableView<WorkingshiftObject> table;
    private String dayOfWeek;
    private Timestamp ngaytao = Timestamp.valueOf("2024-06-27 00:00:00");

    private UserObject user;
    private int area_user;
    private ArrayList<WorkingshiftObject> list;
    private WorkingshiftFunction wf=new WorkingshiftFunctionImpl();
    private AreaFunction af=new AreaFunctionImpl();
    private ObservableList<String> listarea;
    private HubFunction hf=new HubFunctionImpl();

    private ObservableList<WorkingshiftObject> listwork;

    @FXML
    public UserObject getUser() {
        return user;
    }
    @FXML
    public void setUser(UserObject u) {
        this.user=u;
        this.area_user=user.getUser_area_id();
        ArrayList<AreaObject> areas=af.getAreas(null,area_user-1,(byte)3);
        ArrayList<String> lareas=new ArrayList<>();
        lareas.add(af.getArea(area_user).getArea_name());
        for (AreaObject a:areas) {
            lareas.add(a.getArea_code());
        }
        listarea=FXCollections.observableArrayList(lareas);
        cbkv.setItems(listarea);
        cbkv.setValue(listarea.get(0));
        ArrayList<HubObject> listDaDangKy=hf.getHubByUser(user.getId());
        String caDaDangKy="";
        for (HubObject ho:listDaDangKy) {
            caDaDangKy+=ho.getHub_workingshift_id()+", ";
            if(ho==listDaDangKy.get(listDaDangKy.size()-1))
                caDaDangKy=caDaDangKy.substring(0, caDaDangKy.length() - 2);
        }
        if(!daDK){
            if(caDaDangKy!="")
                list=wf.getWorkingshifts(ngaytao,"workingshift_id NOT IN ("+caDaDangKy+") AND workingshift_area_id >= "+area_user+" AND workingshift_area_id < "+(area_user+3)+" ORDER BY workingshift_name ASC");
        }else{
            list=wf.getWorkingshifts(ngaytao,"workingshift_id IN ("+caDaDangKy+") AND workingshift_area_id >= "+area_user+" AND workingshift_area_id < "+(area_user+3)+" ORDER BY workingshift_name ASC");
        }
        ArrayList<WorkingshiftObject> listn = new ArrayList<>();
        if(list!=null)
            for (WorkingshiftObject w : list) {
                Timestamp time = w.getWorkingshift_date();
                LocalDate localtime = time.toLocalDateTime().toLocalDate();
                if (localtime.getDayOfWeek().toString() == dayOfWeek) listn.add(w);
            }
        listwork =  FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }
    @FXML
    void chonkv(ActionEvent event) {
        int a=listarea.indexOf(cbkv.getValue());
        ArrayList<HubObject> listDaDangKy=hf.getHubByUser(user.getId());
        String caDaDangKy="";
        for (HubObject ho:listDaDangKy) {
            caDaDangKy+=ho.getHub_workingshift_id()+", ";
            if(ho==listDaDangKy.get(listDaDangKy.size()-1))
                caDaDangKy=caDaDangKy.substring(0, caDaDangKy.length() - 2);
        }
        if(daDK){
            if(a!=0) list=wf.getWorkingshifts(ngaytao,"workingshift_id IN ("+caDaDangKy+") AND workingshift_area_id = "+ (area_user+a-1));
            else list=wf.getWorkingshifts(ngaytao,"workingshift_id IN ("+caDaDangKy+") AND workingshift_area_id >= "+area_user+" AND workingshift_area_id < "+(area_user+3));
        }else{
            if(a!=0)
                list=wf.getWorkingshifts(ngaytao,"workingshift_area_id = "+(area_user+a-1)+" ORDER BY workingshift_name ASC");
            else list=wf.getWorkingshifts(ngaytao,"workingshift_area_id >= "+area_user+" AND workingshift_area_id < "+(area_user+3)+" ORDER BY workingshift_name ASC");
            if(caDaDangKy!=""){
                if(a!=0) list=wf.getWorkingshifts(ngaytao,"workingshift_id NOT IN ("+caDaDangKy+") AND workingshift_area_id = "+ (area_user+a-1)+" ORDER BY workingshift_name ASC");
                else list=wf.getWorkingshifts(ngaytao,"workingshift_id NOT IN ("+caDaDangKy+") AND workingshift_area_id >= "+area_user+" AND workingshift_area_id < "+(area_user+3)+" ORDER BY workingshift_name ASC");
            }
        }
        ArrayList<WorkingshiftObject> listn = new ArrayList<>();
        for (WorkingshiftObject w : list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if (localtime.getDayOfWeek().toString() == dayOfWeek) listn.add(w);
        }
        listwork =  FXCollections.observableArrayList(listn);
        table.setItems(listwork);;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(table.getItems().size() == 0){
            Label placeholder = new Label("lịch trống");
            placeholder.setStyle("-fx-font-size: 20px;");
            table.setPlaceholder(placeholder);
        }
        ten.setCellValueFactory(cellData -> {
            WorkingshiftObject w = cellData.getValue();
            return new SimpleStringProperty(w.getWorkingshift_name());
        });
        ngay.setCellValueFactory(cellData -> {
            WorkingshiftObject w = cellData.getValue();
            String tach[]=w.getWorkingshift_date().toString().split(" ");
            return new SimpleStringProperty(tach[0]);
        });
        kv.setCellValueFactory(cellData -> {
            WorkingshiftObject w = cellData.getValue();
            int area_id = w.getWorkingshift_area_id();
            AreaObject area= af.getArea(area_id);
            return new SimpleStringProperty(area.getArea_code());
        });
        tien.setCellValueFactory(cellData -> {
            WorkingshiftObject w = cellData.getValue();
            String formattedNumber = String.format("%,d đ", w.getWorkingshift_money());
            return new SimpleStringProperty(formattedNumber);
        });
        thu.setCellValueFactory(cellData -> {
            WorkingshiftObject w = cellData.getValue();
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            DayOfWeek dayOfWeek = localtime.getDayOfWeek();
            return new SimpleStringProperty(dayOfWeek.toString());
        });
        tt.setCellValueFactory(cellData -> {
            String status="";
            WorkingshiftObject w = cellData.getValue();
            if(daDK) status="Đã đăng ký";
            else{
                if(w.getWorkingshift_drivernumber()>0)  status="Có thể đăng ký";
                else status="Đã đủ số lượng";
            }
            return new SimpleStringProperty(status);
        });
        LocalDate today=LocalDate.now();
        dayOfWeek=today.getDayOfWeek().toString();
        table.setItems(listwork);
        toggle.setSelected(false);
    }
    @FXML
    public void xemlich(ActionEvent event) {
        if(toggle.isSelected()){
            daDK=true;
            toggle.setText("Lịch chưa đăng ký");
            setUser(user);
        } else{
            daDK=false;
            toggle.setText("Lịch đã đăng ký");
            setUser(user);
        }
    }
    @FXML
    public void cn(ActionEvent event) {
        dayOfWeek="SUNDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }

    @FXML
    public void thu2(ActionEvent event) {
        dayOfWeek="MONDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }

    @FXML
    public void thu3(ActionEvent event) {
        dayOfWeek="TUESDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }

    @FXML
    public void thu4(ActionEvent event) {
        dayOfWeek="WEDNESDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }

    @FXML
    public void thu5(ActionEvent event) {
        dayOfWeek="THURSDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }

    @FXML
    public void thu6(ActionEvent event) {
        dayOfWeek="FRIDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }

    @FXML
    public void thu7(ActionEvent event) {
        dayOfWeek="SATURDAY";
        ArrayList<WorkingshiftObject> listnew = new ArrayList<>();
        for (WorkingshiftObject w : list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if (localtime.getDayOfWeek().toString() == dayOfWeek) listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }
    @FXML
    void chonCa(MouseEvent event) {
        WorkingshiftObject w=table.getSelectionModel().getSelectedItem();
        if(w.getWorkingshift_drivernumber()==0){
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Thông báo");
            alert2.setContentText("Ca đã đủ số lượng tài xế");
            alert2.showAndWait();
        }
        if(!daDK){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận đăng ký");
            alert.setHeaderText("Bạn có chắc muốn đăng ký ca này không?");
            alert.setContentText("Hành động này không thể hoàn tác.");
            ButtonType buttonTypeYes = new ButtonType("Có");
            ButtonType buttonTypeNo = new ButtonType("Không");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            if (alert.showAndWait().get() == buttonTypeYes) {
                if(checkdangky(w)){
                    HubObject ho=new HubObject();
                    ho.setHub_user_id(user.getId());
                    ho.setHub_workingshift_id(w.getId());
                    ho.setHub_name("dang ky");
                    System.out.println(ho);
                    hf.addHub(ho);
                    w.setWorkingshift_drivernumber(w.getWorkingshift_drivernumber()-1);
                    wf.editWorkingshift(w);
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Thông báo");
                    alert2.setHeaderText("Đăng ký ca thành công");
                    alert2.setContentText("Bạn đã đăng ký ca thành công");
                    alert2.showAndWait();
                    setUser(user);
                }else{
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Thông báo");
                    alert2.setHeaderText("Đăng ký ca thất bại");
                    alert2.setContentText("Ca này bị trùng lịch với ca đã đăng ký");
                    alert2.showAndWait();
                }
            } else
                System.out.println("Không đăng ký ");
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận hủy ca");
            alert.setHeaderText("Bạn có chắc muốn hủy ca này không?");
            alert.setContentText("Hành động này không thể hoàn tác.");
            ButtonType buttonTypeYes = new ButtonType("Có");
            ButtonType buttonTypeNo = new ButtonType("Không");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            if (alert.showAndWait().get() == buttonTypeYes) {
                HubObject ho=hf.getHubByUserAndWork(user.getId(),w.getId());
                hf.delHub(ho);
                w.setWorkingshift_drivernumber(w.getWorkingshift_drivernumber()+1);
                wf.editWorkingshift(w);
                setUser(user);
            } else
                System.out.println("Không đăng ký ");
        }

    }
    public boolean checkdangky(WorkingshiftObject workadd){
        ArrayList<HubObject> listDaDangKy=hf.getHubByUser(user.getId());
        ArrayList<WorkingshiftObject> lw=new ArrayList<>();
        if(listDaDangKy==null)  return true;
        else{
            for (HubObject ho:listDaDangKy)
                lw.add(wf.getWorkingshift(ho.getHub_workingshift_id()));
            int[] gio=new int[24];
            for(WorkingshiftObject w:lw){
                if(workadd.getWorkingshift_date().equals(w.getWorkingshift_date())){
                    String[] parts = w.getWorkingshift_name().split(" : ");
                    String[] times = parts[1].split("-");
                    String[] startTimeParts = times[0].split(":");
                    int startHour = Integer.parseInt(startTimeParts[0]);
                    String[] endTimeParts = times[1].split(":");
                    int endHour = Integer.parseInt(endTimeParts[0]);
                    for(int i=startHour;i<endHour;i++) gio[i]=1;
                }
            }
            String[] parts = workadd.getWorkingshift_name().split(" : ");
            String[] times = parts[1].split("-");
            String[] startTimeParts = times[0].split(":");
            int startHour = Integer.parseInt(startTimeParts[0]);
            String[] endTimeParts = times[1].split(":");
            int endHour = Integer.parseInt(endTimeParts[0]);
            for(int i=startHour;i<endHour;i++)
                if(gio[i]==1)   return false;
        }
        return true;
    }
    @FXML
    public void truoc(ActionEvent event) {
        dayOfWeek="SUNDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }
    @FXML
    public void sau(ActionEvent event) {
        dayOfWeek="SUNDAY";
        ArrayList<WorkingshiftObject> listnew=new ArrayList<>();
        for (WorkingshiftObject w: list) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listnew.add(w);
        }
        ObservableList<WorkingshiftObject> newList = FXCollections.observableArrayList(listnew);
        table.setItems(newList);
    }
}
