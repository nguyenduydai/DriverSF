
package com.example.taixesf.controller;

import com.example.taixesf.dao.AreaFunction;
import com.example.taixesf.dao.implement.AreaFunctionImpl;
import com.example.taixesf.model.AreaObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminAreaController implements Initializable {

    @FXML
    private TextField kv;

    @FXML
    private TextField kvcon;

    @FXML
    private ListView<String> listkv;

    @FXML
    private ListView<String> listkvcon;
    private ObservableList<String> listarea;
    private ObservableList<String> listareacon;
    private String selectedkv;
    private String selectedkvcon;
    private AreaFunction ar=new AreaFunctionImpl();

    @FXML
    void sua(ActionEvent event) {
         AreaObject ao=ar.getAreaByCode(selectedkvcon);
         ao.setArea_code(kvcon.getText());
         ao.setArea_name(kv.getText());
         ar.editArea(ao);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Thông báo");
         alert.setHeaderText("Đây là một thông báo");
         alert.setContentText("Sửa thành công");
         alert.showAndWait();
    }

    @FXML
    void them(ActionEvent event) {
        AreaObject ao=new AreaObject();
        ao.setArea_code(kvcon.getText());
        ao.setArea_name(kv.getText());
        ar.addArea(ao);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Đây là một thông báo");
        alert.setContentText("Thêm mới thành công");
        alert.showAndWait();
    }

    @FXML
    void thoat(ActionEvent event) {

    }

    @FXML
    void xoa(ActionEvent event) {
        AreaObject ao=ar.getAreaByCode(selectedkvcon);
        if(ar.delArea(ao)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Đây là một thông báo");
            alert.setContentText("Xóa thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Đây là một thông báo");
            alert.setContentText("Xóa không thành công");
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AreaFunction ar=new AreaFunctionImpl();
        ArrayList<AreaObject> la=ar.getAreas(null,0,(byte)100);
        ArrayList<String> lareas=new ArrayList<>();
        ArrayList<String> lareacon=new ArrayList<>();
        for (AreaObject a: la) {
            if(lareas.indexOf(a.getArea_name())==-1) lareas.add(a.getArea_name());
            lareacon.add(a.getArea_code());
        }
        listarea= FXCollections.observableArrayList(lareas);
        listkv.setItems(listarea);

        ArrayList<String> lareaconss=new ArrayList<>();
        listkv.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selectedkv = listkv.getSelectionModel().getSelectedItem();
                kv.setText(selectedkv);
                lareaconss.clear();
                for (String s:lareacon) {
                    if(s.contains(selectedkv)) lareaconss.add(s);
                }
                listareacon= FXCollections.observableArrayList(lareaconss);
                listkvcon.setItems(listareacon);
            }
        });
        listkvcon.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selectedkvcon = listkvcon.getSelectionModel().getSelectedItem();
                kvcon.setText(selectedkvcon);
            }
        });

    }
}

