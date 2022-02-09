package vcs.lt.project;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private List<Product> products = new ArrayList<>();

//    public Meal(List<Product> products) {
//        this.products = products;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
