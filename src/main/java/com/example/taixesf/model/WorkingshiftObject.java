package com.example.taixesf.model;

import java.sql.Timestamp;
import java.util.Objects;

public class WorkingshiftObject extends AbstractModel{
    private String workingshift_name;
    private Timestamp workingshift_date;
    private int workingshift_drivernumber;
    private int workingshift_money;
    private int workingshift_area_id;
    private AreaObject area;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingshiftObject that = (WorkingshiftObject) o;
        return workingshift_drivernumber == that.workingshift_drivernumber && workingshift_money == that.workingshift_money && workingshift_area_id == that.workingshift_area_id && Objects.equals(workingshift_name, that.workingshift_name) && Objects.equals(workingshift_date, that.workingshift_date) && Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workingshift_name, workingshift_date, workingshift_drivernumber, workingshift_money, workingshift_area_id, area);
    }

    @Override
    public String toString() {
        return "WorkingshiftObject{" +
                "workingshift_name='" + workingshift_name + '\'' +
                ", workingshift_date=" + workingshift_date +
                ", workingshift_drivernumber=" + workingshift_drivernumber +
                ", workingshift_money=" + workingshift_money +
                ", workingshift_area_id=" + workingshift_area_id +
                ", area=" + area +
                '}';
    }

    public AreaObject getArea() {
        return area;
    }

    public void setArea(AreaObject area) {
        this.area = area;
    }

    public String getWorkingshift_name() {
        return workingshift_name;
    }

    public void setWorkingshift_name(String workingshift_name) {
        this.workingshift_name = workingshift_name;
    }

    public Timestamp getWorkingshift_date() {
        return workingshift_date;
    }

    public void setWorkingshift_date(Timestamp workingshift_date) {
        this.workingshift_date = workingshift_date;
    }
    public int getWorkingshift_drivernumber() {
        return workingshift_drivernumber;
    }

    public void setWorkingshift_drivernumber(int workingshift_drivernumber) {
        this.workingshift_drivernumber = workingshift_drivernumber;
    }

    public int getWorkingshift_money() {
        return workingshift_money;
    }

    public void setWorkingshift_money(int workingshift_money) {
        this.workingshift_money = workingshift_money;
    }

    public int getWorkingshift_area_id() {
        return workingshift_area_id;
    }

    public void setWorkingshift_area_id(int workingshift_area_id) {
        this.workingshift_area_id = workingshift_area_id;
    }
}
