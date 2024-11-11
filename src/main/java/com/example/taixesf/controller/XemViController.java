package com.example.taixesf.controller;

import com.example.taixesf.model.UserObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class XemViController {

    @FXML
    private Label tien;
    private UserObject user;

    public UserObject getUser() {
        return user;
    }

    public void setUser(UserObject user) {
        this.user = user;
        tien.setText(String.valueOf(user.getUser_salary()));
    }

}
