package fpt.edu.recycleviewmvvmlivedata;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String des;

    public User(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
