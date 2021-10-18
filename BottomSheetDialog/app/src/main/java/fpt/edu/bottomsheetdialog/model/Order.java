package fpt.edu.bottomsheetdialog.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private String price;
    private List<Product> listProducts;
    private String address;

    public Order(String price, List<Product> listProducts, String address) {
        this.price = price;
        this.listProducts = listProducts;
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getListProductName(){
        if (listProducts == null || listProducts.isEmpty()){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < listProducts.size(); i++) {
            Product product = listProducts.get(i);

            if (stringBuilder.length() > 0){
                stringBuilder.append("\n");
            }
            stringBuilder.append(product.getName());
        }
        return stringBuilder.toString();
    }
}
