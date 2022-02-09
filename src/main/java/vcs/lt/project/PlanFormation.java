package vcs.lt.project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlanFormation {


    List<Product> productList = new ArrayList<>();
    List<Macros> macrosList = new ArrayList<>();
    Plan plan = new Plan();

    JSONArray arrayPlan = new JSONArray();

    Database database = new HibernateDatabaseImpl();


    public List<Product> getProductList() {

        return database.getProducts();
    }

    public List<Product> addProductToMeal(String ingredient, int portionSize) {

        Product productTemp = database.getProductByName(ingredient);

        double calories = productTemp.getCalories() * portionSize;
        double carbs = productTemp.getCarbs() * portionSize;
        double protein = productTemp.getProtein() * portionSize;
        double fats = productTemp.getFats() * portionSize;
        double fiber = productTemp.getFiber() * portionSize;

        Product productNew = new Product(productTemp.getType(), productTemp.getMeal(), calories, carbs, protein, fats, fiber);

        productList.add(productNew);

        return productList;
    }

    public void addMealToPlan() {
        Meal meal = new Meal();
        meal.setProducts(productList);

        Macros macros;

        double caloriesTotal = productList.stream().mapToDouble(Product::getCalories).sum();
        double carbsTotal = productList.stream().mapToDouble(Product::getCarbs).sum();
        double proteinTotal = productList.stream().mapToDouble(Product::getProtein).sum();
        double fatTotal = productList.stream().mapToDouble(Product::getFats).sum();
        double fiberTotal = productList.stream().mapToDouble(Product::getFiber).sum();

        macros = new Macros(caloriesTotal, carbsTotal, proteinTotal, fatTotal, fiberTotal);

        macrosList.add(macros);
        System.out.println(macrosList);

        JSONArray arrayMeal = new JSONArray();

        for (Product product : productList) {
            JSONObject objProduct = new JSONObject();
            objProduct.put("type", product.getType());
            objProduct.put("meal", product.getMeal());
            objProduct.put("calories", product.getCalories());
            objProduct.put("carbs", product.getCarbs());
            objProduct.put("protein", product.getProtein());
            objProduct.put("fats", product.getFats());
            objProduct.put("fiber", product.getFiber());
            arrayMeal.put(objProduct);

        }
//        planJson.put("Meal", arrayMeal);
        arrayPlan.put(arrayMeal);

        plan.addMeal(meal);
        System.out.println("This is plan: " + plan);
        database.updatePlan(arrayPlan);

        productList.clear();

    }


    public JSONArray getPlan() {

        return arrayPlan;
    }
}
