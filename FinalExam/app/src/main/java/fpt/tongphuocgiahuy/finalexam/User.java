package fpt.tongphuocgiahuy.finalexam;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String queQuan;
    private String namSinh;

    public User(int id, String name, String queQuan, String namSinh) {
        this.id = id;
        this.name = name;
        this.queQuan = queQuan;
        this.namSinh = namSinh;
    }

    public User(String name, String queQuan, String namSinh) {
        this.name = name;
        this.queQuan = queQuan;
        this.namSinh = namSinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
