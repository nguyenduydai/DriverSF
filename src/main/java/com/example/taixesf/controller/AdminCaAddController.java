package com.example.taixesf.controller;

import com.example.taixesf.dao.AreaFunction;
import com.example.taixesf.dao.HubFunction;
import com.example.taixesf.dao.WorkingshiftFunction;
import com.example.taixesf.dao.implement.AreaFunctionImpl;
import com.example.taixesf.dao.implement.HubFunctionImpl;
import com.example.taixesf.dao.implement.WorkingshiftFunctionImpl;
import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.HubObject;
import com.example.taixesf.model.WorkingshiftObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminCaAddController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label test;
    @FXML
    public  void setBorderPane(BorderPane p){
        borderPane=p;
    }

    @FXML
    private TextField sltx;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> cbtien;

    @FXML
    private ListView<String> listgio;

    @FXML
    private ListView<String> listkv;

    @FXML
    private TableColumn<WorkingshiftObject, String> ngay;

    @FXML
    private TableColumn<WorkingshiftObject, String> tien;

    @FXML
    private TableColumn<WorkingshiftObject, String> kv;

    @FXML
    private TableColumn<WorkingshiftObject, String> ten;

    @FXML
    private TableColumn<WorkingshiftObject, String> thu;
    @FXML
    private TableColumn<WorkingshiftObject, String> tt;
    @FXML
    private TableColumn<WorkingshiftObject, String> sl;

    private ObservableList<String> listarea;
    private ObservableList<String> listca;
    private ObservableList<String> tiens;
    private ObservableList<WorkingshiftObject> listwork;
    private ArrayList<String> ltien = new ArrayList<>();
    private ArrayList<WorkingshiftObject> listthem = new ArrayList<>();
    private AreaFunction af=new AreaFunctionImpl();
    private WorkingshiftFunction wf=new WorkingshiftFunctionImpl();
    private HubFunction hf=new HubFunctionImpl();

    @FXML
    private TableView<WorkingshiftObject> table;


    @FXML
    void them(ActionEvent event) {
        LocalDate localDate=date.getValue();
        Timestamp timestamp= Timestamp.valueOf(localDate.atStartOfDay());
        int numberdriver= Integer.parseInt(sltx.getText());
        String chontien=cbtien.getValue().substring(0,2)+cbtien.getValue().substring(3,6);
        for (String s:listchongio) {
            for (String st:listchonkv) {
                int area_id = 0;
                if(st.equals("Nam Từ Liêm")) area_id=1;
                if(st.equals("Bắc Từ Liêm")) area_id=4;
                if(st.equals("Cầu Giấy")) area_id=7;
                if(st.equals("Hồ Tây")) area_id=10;
                if(st.equals("Hoài Đức")) area_id=13;
                for(int i=0;i<3;i++){
                    WorkingshiftObject w=new WorkingshiftObject();
                    w.setWorkingshift_date(timestamp);
                    w.setWorkingshift_drivernumber(numberdriver);
                    w.setWorkingshift_money(Integer.parseInt(chontien));
                    w.setWorkingshift_name(s);
                    w.setWorkingshift_area_id(area_id+i);
                    LocalDate date = LocalDate.now();
                    Timestamp createdDate = Timestamp.valueOf(date.atStartOfDay());
                    w.setCreatedDate(createdDate);
                    listthem.add(w);
                    wf.addWorkingshift(w);
                }
            }
        }
        listwork =  FXCollections.observableArrayList(listthem);
        table.setItems(listwork);
    }

    @FXML
    void thoat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/qlLich.fxml"));
        AnchorPane view = loader.load();
        AdminCaController controller = loader.getController();
        controller.setBorderPane(borderPane);
        borderPane.setCenter(view);
    }

    private ArrayList<String> listchonkv=new ArrayList<>();
    private ArrayList<String> listchongio=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AreaFunction ar=new AreaFunctionImpl();
        ArrayList<AreaObject> la=ar.getAreas(null,0,(byte)100);
        ArrayList<String> lareas=new ArrayList<>();
        for (AreaObject a: la) {
            if(lareas.indexOf(a.getArea_name())==-1) lareas.add(a.getArea_name());
        }
        listarea= FXCollections.observableArrayList(lareas);
        listkv.setItems(listarea);
        listkv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        WorkingshiftFunction wf=new WorkingshiftFunctionImpl();
        ArrayList<WorkingshiftObject> lw=wf.getWorkingshifts();
        ArrayList<String> lca=new ArrayList<>();
        for (WorkingshiftObject a: lw) {
            if(lca.indexOf(a.getWorkingshift_name())==-1) lca.add(a.getWorkingshift_name());
        }
        listca= FXCollections.observableArrayList(lca);
        listgio.setItems(listca);
        listgio.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listkv.getSelectionModel().getSelectedItems().addListener((ListChangeListener<String>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    listchonkv.addAll(c.getAddedSubList());
                    System.out.println("Added to ArrayList: " + c.getAddedSubList());
                } else if (c.wasRemoved()) {
                    listchonkv.removeAll(c.getRemoved());

                }
            }
        });
        listgio.getSelectionModel().getSelectedItems().addListener((ListChangeListener<String>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    listchongio.addAll(c.getAddedSubList());
                    System.out.println("Added to ArrayList: " + c.getAddedSubList());
                } else if (c.wasRemoved()) {
                    listchongio.removeAll(c.getRemoved());
                    System.out.println("Removed from ArrayList: " + c.getRemoved());
                }
            }
        });

        ltien.add("13.500 đ");
        ltien.add("21.500 đ");
        tiens= FXCollections.observableArrayList(ltien);
        cbtien.setItems(tiens);
        cbtien.setValue(ltien.get(0));


        listwork =  FXCollections.observableArrayList(listthem);
        if(table.getItems().size() == 0){
            Label placeholder = new Label("chưa có");
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
    }
}
