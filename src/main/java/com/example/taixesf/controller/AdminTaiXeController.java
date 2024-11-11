package com.example.taixesf.controller;

import com.example.taixesf.dao.AreaFunction;
import com.example.taixesf.dao.UserFunction;
import com.example.taixesf.dao.implement.AreaFunctionImpl;
import com.example.taixesf.dao.implement.UserFunctionImpl;
import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.UserObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminTaiXeController implements Initializable {
    @FXML
    private HBox hbox;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ComboBox<String> cbkv;
    private ObservableList<String> listarea;

    private UserFunction uf=new UserFunctionImpl();
    private ArrayList<UserObject> lu;
    @FXML
    void dsdk(ActionEvent event) {
        this.lu=uf.getUsers("user_status=2");
        hienthi(lu);
    }

    @FXML
    void dsql(ActionEvent event) {
        this.lu=uf.getUsers("user_role_id>1");
        hienthi(lu);
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    public  void setBorderPane(BorderPane p){
        borderPane=p;
    }

    @FXML
    void sua(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/suataixe.fxml"));
        AnchorPane view = loader.load();
        AdminTaiXeSuaController controller = loader.getController();
        controller.setBorderPane(borderPane);
        borderPane.setCenter(view);
    }

    @FXML
    void chonkv(ActionEvent event) {
        int index=listarea.indexOf(cbkv.getValue());
        index=index*3+1;
        this.lu=uf.getUsers("user_area_id="+index);
        hienthi(lu);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AreaFunction ar=new AreaFunctionImpl();
        ArrayList<AreaObject> la=ar.getAreas(null,0,(byte)100);
        ArrayList<String> lareas=new ArrayList<>();
        for (AreaObject a: la) {
            if(lareas.indexOf(a.getArea_name())==-1) lareas.add(a.getArea_name());
        }
        listarea= FXCollections.observableArrayList(lareas);
        cbkv.setItems(listarea);
        scroll.setContent(hbox);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        lu=uf.getUsers("user_role_id=1");
        hienthi(lu);


    }
    public void hienthi(ArrayList<UserObject> l){
        hbox.getChildren().clear();
        for (UserObject u:lu) {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("views/taixeitem.fxml"));
            VBox item= null;
            try {
                item = fxmlLoader.load();
                TaiXeItemController controller=fxmlLoader.getController();
                controller.setData(u);
                hbox.getChildren().add(item);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public ArrayList<UserObject> getUserss()
    {
        UserFunction uf=new UserFunctionImpl();
        return uf.getUsers(null,0,(byte)100);
    }
}
