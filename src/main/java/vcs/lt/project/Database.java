package vcs.lt.project;

import org.json.JSONArray;

import java.util.List;

public interface Database {

    void insertProduct(String type, String meal, double calories, double carbs, double protein, double fats, double fiber);

    List<Product> getProducts();

    List<Product> getProductsByType(String type);

    Product getProductByName(String meal);

    User getUser(String username, String password);

    List<User> getUsers();

    String updatePlan(JSONArray array);

    String getUserPlan();

    void createUser(String username, String password);

}
