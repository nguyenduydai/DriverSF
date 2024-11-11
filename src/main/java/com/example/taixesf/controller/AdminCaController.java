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
import javafx.beans.property.SimpleIntegerProperty;
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



public class AdminCaController implements Initializable {
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
    private BorderPane borderPane;

    @FXML
    public  void setBorderPane(BorderPane p){
        borderPane=p;
    }


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
    private TableColumn<WorkingshiftObject, String> sl;

    @FXML
    private DatePicker date;


    @FXML
    private TableView<WorkingshiftObject> table;
    private String dayOfWeek;
    private Timestamp ngaytao = Timestamp.valueOf("2024-06-17 00:00:00");

    @FXML
    private ComboBox<String> cbhub3;

    @FXML
    private ComboBox<String> cbhub5;
    private UserObject user;
    private int area_user;
    private ArrayList<WorkingshiftObject> list;
    private WorkingshiftFunction wf=new WorkingshiftFunctionImpl();
    private AreaFunction af=new AreaFunctionImpl();
    private ObservableList<String> listarea;
    private ObservableList<String> listhub5;
    private ObservableList<String> listhub3;
    private HubFunction hf=new HubFunctionImpl();

    private ObservableList<WorkingshiftObject> listwork;

    @FXML
    public UserObject getUser() {
        return user;
    }
    @FXML
    public void setUser(UserObject u) {

    }
    @FXML
    void chonkv(ActionEvent event) {
        int index=listarea.indexOf(cbkv.getValue());
        index=index*3+1;
        ArrayList<WorkingshiftObject> listkv=wf.getWorkingshifts(ngaytao,"workingshift_area_id >= "+index+" AND workingshift_area_id < "+(index+3));
        ArrayList<WorkingshiftObject> commonElements = new ArrayList<>(listn);
        commonElements.retainAll(listkv);
        this.listwork= FXCollections.observableArrayList(commonElements);
        table.setItems(listwork);
    }

    private ArrayList<WorkingshiftObject> listn = wf.getWorkingshifts();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listwork =  FXCollections.observableArrayList(listn);
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
            ArrayList<HubObject> h=hf.getHubByWorkingshift(w.getId());
            if(h.size()>0) {
                status="Đã được đăng ký";
                if(w.getWorkingshift_drivernumber()==0) status="Đã đủ số lượng";
            }
            else status="Chưa được đăng ký";
            return new SimpleStringProperty(status);
        });
        sl.setCellValueFactory(cellData -> {
            WorkingshiftObject w = cellData.getValue();
            return new SimpleStringProperty(Integer.toString(w.getWorkingshift_drivernumber()));
        });
        table.setItems(listwork);

        AreaFunction ar=new AreaFunctionImpl();
        ArrayList<AreaObject> la=ar.getAreas(null,0,(byte)100);
        ArrayList<String> lareas=new ArrayList<>();
        for (AreaObject a: la) {
            if(lareas.indexOf(a.getArea_name())==-1) lareas.add(a.getArea_name());
        }
        listarea= FXCollections.observableArrayList(lareas);
        cbkv.setItems(listarea);

        ArrayList<String> listh5=new ArrayList<>();
        ArrayList<String> listh3=new ArrayList<>();
        for (WorkingshiftObject w: listn) {
            if(w.getWorkingshift_name().contains("Hub 5") && listh5.indexOf(w.getWorkingshift_name())==-1 )
                listh5.add(w.getWorkingshift_name());
            if(w.getWorkingshift_name().contains("Hub 3") && listh3.indexOf(w.getWorkingshift_name())==-1 )
                listh3.add(w.getWorkingshift_name());
        }
        listhub5= FXCollections.observableArrayList(listh5);
        cbhub5.setItems(listhub5);
        listhub3= FXCollections.observableArrayList(listh3);
        cbhub3.setItems(listhub3);
    }
    private ArrayList<WorkingshiftObject> listall=wf.getWorkingshifts();
    @FXML
    public void cn(ActionEvent event) {
        listn.clear();
        dayOfWeek="SUNDAY";
        for (WorkingshiftObject w: listall) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listn.add(w);
        }
        listwork = FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }



    @FXML
    public void thu2(ActionEvent event) {
        listn.clear();
        dayOfWeek="MONDAY";
        for (WorkingshiftObject w: listall) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listn.add(w);
        }
        listwork = FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }

    @FXML
    public void thu3(ActionEvent event) {
        listn.clear();
        dayOfWeek="TUESDAY";
        for (WorkingshiftObject w: listall) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listn.add(w);
        }
        listwork = FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }

    @FXML
    public void thu4(ActionEvent event) {
        listn.clear();
        dayOfWeek="WEDNESDAY";
        for (WorkingshiftObject w: listall) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listn.add(w);
        }
        listwork = FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }

    @FXML
    public void thu5(ActionEvent event) {
        listn.clear();
        dayOfWeek="THURSDAY";
        for (WorkingshiftObject w: listall) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listn.add(w);
        }
        listwork = FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }

    @FXML
    public void thu6(ActionEvent event) {
        listn.clear();
        dayOfWeek="FRIDAY";
        for (WorkingshiftObject w: listall) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if(localtime.getDayOfWeek().toString()==dayOfWeek)   listn.add(w);
        }
        listwork = FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }

    @FXML
    public void thu7(ActionEvent event) {
        listn.clear();
        dayOfWeek="SATURDAY";
        for (WorkingshiftObject w : listall) {
            Timestamp time = w.getWorkingshift_date();
            LocalDate localtime = time.toLocalDateTime().toLocalDate();
            if (localtime.getDayOfWeek().toString() == dayOfWeek) listn.add(w);
        }
        listwork = FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }
    @FXML
    void chonCa(MouseEvent event) {
      /*  WorkingshiftObject w=table.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận đăng ký");
            alert.setHeaderText("Bạn có chắc muốn đăng ký ca này không?");
            alert.setContentText("Hành động này không thể hoàn tác.");
            ButtonType buttonTypeYes = new ButtonType("Có");
            ButtonType buttonTypeNo = new ButtonType("Không");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            if (alert.showAndWait().get() == buttonTypeYes) {
                HubObject ho=new HubObject();
                ho.setHub_user_id(user.getId());
                ho.setHub_workingshift_id(w.getId());
                ho.setHub_name("dang ky");
                System.out.println(ho);
                hf.addHub(ho);
                w.setWorkingshift_drivernumber(w.getWorkingshift_drivernumber()-1);
                wf.editWorkingshift(w);
                setUser(user);
            } else
                System.out.println("Không đăng ký ");
*/

    }
    @FXML
    void timhientai(ActionEvent event) {
        LocalDate localDate=LocalDate.now();
        Timestamp timestamp= Timestamp.valueOf(localDate.atStartOfDay());
        this.listn=wf.getWorkingshiftsByDate(timestamp);
        this.listwork= FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }

    @FXML
    void timngay(ActionEvent event) {
        LocalDate localDate=date.getValue();
        Timestamp timestamp= Timestamp.valueOf(localDate.atStartOfDay());
        this.listn=wf.getWorkingshiftsByDate(timestamp);
        this.listwork= FXCollections.observableArrayList(listn);
        table.setItems(listwork);
    }

    @FXML
    void xem(ActionEvent event) throws IOException {
        WorkingshiftObject w=table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/chitietLich.fxml"));
        AnchorPane view = loader.load();
        AdminCaXemController controller = loader.getController();
        controller.setBorderPane(borderPane);
        controller.setW(w);
        borderPane.setCenter(view);
    }
    @FXML
    public void hub333(ActionEvent event) {
        ArrayList<WorkingshiftObject> listh3=wf.getWorkingshifts(ngaytao,"workingshift_name = '"+cbhub3.getValue()+"'");
        ArrayList<WorkingshiftObject> commonElements = new ArrayList<>(this.listn);
        commonElements.retainAll(listh3);
        this.listwork= FXCollections.observableArrayList(commonElements);
        table.setItems(listwork);
    }

    @FXML
    public void hub5(ActionEvent event) {
        ArrayList<WorkingshiftObject> listh5=wf.getWorkingshifts(ngaytao,"workingshift_name = '"+cbhub5.getValue()+"'");
        ArrayList<WorkingshiftObject> commonElements = new ArrayList<>(listn);
        commonElements.retainAll(listh5);
        this.listwork= FXCollections.observableArrayList(commonElements);
        table.setItems(listwork);
    }

    @FXML
    void them(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/themLich.fxml"));
        AnchorPane view = loader.load();
        AdminCaAddController controller = loader.getController();
        controller.setBorderPane(borderPane);
        borderPane.setCenter(view);
    }

}
