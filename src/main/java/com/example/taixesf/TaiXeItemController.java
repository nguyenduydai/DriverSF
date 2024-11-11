package com.example.taixesf;

import com.example.taixesf.dao.AreaFunction;
import com.example.taixesf.dao.implement.AreaFunctionImpl;
import com.example.taixesf.model.UserObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TaiXeItemController {
    @FXML
    private ImageView anh;

    @FXML
    private VBox item;

    @FXML
    private Label kv;
    @FXML
    private Label id;

    @FXML
    private Label sdt;

    @FXML
    private Label ten;
     public void setData(UserObject u){
         ten.setText(u.getUser_name());
         sdt.setText(u.getUser_phone());
         id.setText(String.valueOf(u.getId()));
         int area_id=u.getUser_area_id();
         AreaFunction af=new AreaFunctionImpl();
         kv.setText(af.getArea(area_id).getArea_name());
         String imagePath = u.getUser_image();
         Image image = new Image(getClass().getResource("images/" + imagePath).toExternalForm(),120,100,false,true);
         anh.setImage(image);
     }
}
