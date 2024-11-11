package com.example.taixesf.model;

public class HubObject extends AbstractModel{
    private String hub_name;
    private int hub_user_id;
    private int hub_workingshift_id;
    private int hub_ordernumber;

    public String getHub_name() {
        return hub_name;
    }

    @Override
    public String toString() {
        return "HubObject{" +
                "hub_name='" + hub_name + '\'' +
                ", hub_user_id=" + hub_user_id +
                ", hub_workingshift_id=" + hub_workingshift_id +
                ", hub_ordernumber=" + hub_ordernumber +
                '}';
    }

    public void setHub_name(String hub_name) {
        this.hub_name = hub_name;
    }

    public int getHub_user_id() {
        return hub_user_id;
    }

    public void setHub_user_id(int hub_user_id) {
        this.hub_user_id = hub_user_id;
    }

    public int getHub_workingshift_id() {
        return hub_workingshift_id;
    }

    public void setHub_workingshift_id(int hub_workingshift_id) {
        this.hub_workingshift_id = hub_workingshift_id;
    }

    public int getHub_ordernumber() {
        return hub_ordernumber;
    }

    public void setHub_ordernumber(int hub_ordernumber) {
        this.hub_ordernumber = hub_ordernumber;
    }
}
