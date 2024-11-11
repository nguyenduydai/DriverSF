package com.example.taixesf.controller;

import com.example.taixesf.dao.HubFunction;
import com.example.taixesf.dao.UserFunction;
import com.example.taixesf.dao.WorkingshiftFunction;
import com.example.taixesf.dao.implement.HubFunctionImpl;
import com.example.taixesf.dao.implement.UserFunctionImpl;
import com.example.taixesf.dao.implement.WorkingshiftFunctionImpl;
import com.example.taixesf.model.HubObject;
import com.example.taixesf.model.UserObject;
import com.example.taixesf.model.WorkingshiftObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminThongKeController implements Initializable {
    @FXML
    private PieChart pie;
    @FXML
    private PieChart pie1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HubFunction hf=new HubFunctionImpl();
        ArrayList<HubObject> listh=hf.getHubs(null,0,(byte)100);
        WorkingshiftFunction wf=new WorkingshiftFunctionImpl();
        ArrayList<WorkingshiftObject> listw=wf.getWorkingshifts();
        PieChart.Data dadk=new PieChart.Data("Đã đăng ký",listh.size());
        PieChart.Data chuadk=new PieChart.Data("Chưa đăng ký",listw.size()*5-listh.size());
        pie.getData().addAll(dadk,chuadk);
        for (PieChart.Data d:pie.getData()) {
            d.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText(d.getName() +" : " + d.getPieValue());
                alert.showAndWait();
            });
        }

        UserFunction uf=new UserFunctionImpl();
        ArrayList<UserObject> listu=uf.getUsers(null,0,(byte)100);
        int ntl=0,btl=0,cg=0,ht=0,hd=0;
        for (UserObject u:listu) {
            System.out.println(u);
            if(u.getUser_area_id()==1) ntl++;
            if(u.getUser_area_id()==4) btl++;
            if(u.getUser_area_id()==7) cg++;
            if(u.getUser_area_id()==10) ht++;
            if(u.getUser_area_id()==13) hd++;
        }
        PieChart.Data kntl=new PieChart.Data("Nam Từ liêm",ntl);
        PieChart.Data kbtl=new PieChart.Data("Bắc Từ liêm",btl);
        PieChart.Data kcg=new PieChart.Data("Cầu Giấy",cg);
        PieChart.Data kht=new PieChart.Data("Hồ Tây",ht);
        PieChart.Data khd=new PieChart.Data("Hoài Đức",ht);
        pie1.getData().addAll(kntl,kbtl,kcg,kht,khd);
        for (PieChart.Data d:pie1.getData()) {
            d.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText(d.getName() +" : " + d.getPieValue());
                alert.showAndWait();
            });
        }
    }
}
