package com.example.taixesf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHieuSuatController implements Initializable {
    @FXML
    LineChart<String,Number> linechart;
    @FXML
    ComboBox<String> cb;

    private ObservableList<String> tuan= FXCollections.observableArrayList("Tuần 1","Tuần 2","Tuần 3","Tuần 4","Tuần 5");



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String,Number> series=new XYChart.Series<>();
        XYChart.Data<String,Number> t2=new XYChart.Data<>("MONDAY",300);
        XYChart.Data<String,Number> t3=new XYChart.Data<>("TUESDAY",400);
        XYChart.Data<String,Number> t4=new XYChart.Data<>("WEDNESDAY",500);
        XYChart.Data<String,Number> t5=new XYChart.Data<>("THURDAY",400);
        XYChart.Data<String,Number> t6=new XYChart.Data<>("FRIDAY",500);
        XYChart.Data<String,Number> t7=new XYChart.Data<>("SATURDAY",600);
        XYChart.Data<String,Number> cn=new XYChart.Data<>("SUNDAY",700);
        series.getData().addAll(t2,t3,t4,t5,t6,t7,cn);
        series.setName("Khu vực Nam Từ Liêm");

        XYChart.Series<String,Number> series1=new XYChart.Series<>();
        XYChart.Data<String,Number> t21=new XYChart.Data<>("MONDAY",400);
        XYChart.Data<String,Number> t31=new XYChart.Data<>("TUESDAY",300);
        XYChart.Data<String,Number> t41=new XYChart.Data<>("WEDNESDAY",400);
        XYChart.Data<String,Number> t51=new XYChart.Data<>("THURDAY",500);
        XYChart.Data<String,Number> t61=new XYChart.Data<>("FRIDAY",600);
        XYChart.Data<String,Number> t71=new XYChart.Data<>("SATURDAY",500);
        XYChart.Data<String,Number> cn1=new XYChart.Data<>("SUNDAY",600);
        series1.getData().addAll(t21,t31,t41,t51,t61,t71,cn1);
        series1.setName("Khu vực Bắc Từ Liêm");

        XYChart.Series<String,Number> series2=new XYChart.Series<>();
        XYChart.Data<String,Number> t22=new XYChart.Data<>("MONDAY",200);
        XYChart.Data<String,Number> t32=new XYChart.Data<>("TUESDAY",300);
        XYChart.Data<String,Number> t42=new XYChart.Data<>("WEDNESDAY",500);
        XYChart.Data<String,Number> t52=new XYChart.Data<>("THURDAY",500);
        XYChart.Data<String,Number> t62=new XYChart.Data<>("FRIDAY",600);
        XYChart.Data<String,Number> t72=new XYChart.Data<>("SATURDAY",700);
        XYChart.Data<String,Number> cn2=new XYChart.Data<>("SUNDAY",700);
        series2.getData().addAll(t22,t32,t42,t52,t62,t72,cn2);
        series2.setName("Khu vực Cầu Giấy");

        XYChart.Series<String,Number> series3=new XYChart.Series<>();
        XYChart.Data<String,Number> t23=new XYChart.Data<>("MONDAY",500);
        XYChart.Data<String,Number> t33=new XYChart.Data<>("TUESDAY",400);
        XYChart.Data<String,Number> t43=new XYChart.Data<>("WEDNESDAY",300);
        XYChart.Data<String,Number> t53=new XYChart.Data<>("THURDAY",400);
        XYChart.Data<String,Number> t63=new XYChart.Data<>("FRIDAY",500);
        XYChart.Data<String,Number> t73=new XYChart.Data<>("SATURDAY",400);
        XYChart.Data<String,Number> cn3=new XYChart.Data<>("SUNDAY",400);
        series3.getData().addAll(t23,t33,t43,t53,t63,t73,cn3);
        series3.setName("Khu vực Hồ Tây");

        XYChart.Series<String,Number> series4=new XYChart.Series<>();
        XYChart.Data<String,Number> t24=new XYChart.Data<>("MONDAY",200);
        XYChart.Data<String,Number> t34=new XYChart.Data<>("TUESDAY",300);
        XYChart.Data<String,Number> t44=new XYChart.Data<>("WEDNESDAY",200);
        XYChart.Data<String,Number> t54=new XYChart.Data<>("THURDAY",300);
        XYChart.Data<String,Number> t64=new XYChart.Data<>("FRIDAY",400);
        XYChart.Data<String,Number> t74=new XYChart.Data<>("SATURDAY",400);
        XYChart.Data<String,Number> cn4=new XYChart.Data<>("SUNDAY",500);
        series4.getData().addAll(t24,t34,t44,t54,t64,t74,cn4);
        series4.setName("Khu vực Hoài Đức");
        linechart.getData().addAll(series,series1,series2,series3,series4);
        cb.setItems(tuan);
        cb.setValue(tuan.get(3));
    }
}
