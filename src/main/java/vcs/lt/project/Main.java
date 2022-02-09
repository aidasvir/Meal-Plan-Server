package vcs.lt.project;

import org.json.JSONArray;
import org.json.JSONObject;

import static spark.Spark.get;
import static spark.Spark.post;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlanFormation planFormation = new PlanFormation();


        get("/", (request, response) -> getTextFromFile("login.html"));

        post("/login", (request, response) -> {

            String username = request.queryParams("username");
            String password = request.queryParams("password");
            System.out.println(username + " " + password);

            User user = planFormation.database.getUser(
                    username,
                    password
            );

            if (user == null) {
                response.type("application/json");
                JSONObject error = new JSONObject();
                error.put("error", "404 error");
                return error.toString();
            }

            return getTextFromFile("index.html");
        });

        post("/products", (request, response) -> {
            response.type("application/json");
            List<Product> products = planFormation.database.getProducts();

            JSONArray array = new JSONArray();

            for (Product product : products) {
                JSONObject obj = new JSONObject();
                obj.put("type", product.getType());
                obj.put("meal", product.getMeal());
                obj.put("calories", product.getCalories());
                obj.put("carbs", product.getCarbs());
                obj.put("protein", product.getProtein());
                obj.put("fats", product.getFats());
                obj.put("fiber", product.getFiber());
                array.put(obj);
            }




            return array;
        });

        post("/product", (request, response) -> {
            response.type("application/json");
            String product = request.queryParams("product");
            Product productFull = planFormation.database.getProductByName(product);

                JSONObject obj1 = new JSONObject();
                obj1.put("meal", productFull.getMeal());
                obj1.put("calories", productFull.getCalories());
                obj1.put("carbs", productFull.getCarbs());
                obj1.put("protein", productFull.getProtein());
                obj1.put("fats", productFull.getFats());
                obj1.put("fiber", productFull.getFiber());

             return obj1;

        });

        post("/add", (request, response) -> {
            response.type("application/json");

            String ingredient = request.queryParams("ingredient");
            int portionSize = Integer.valueOf(request.queryParams("size"));

            List<Product> productList = planFormation.addProductToMeal(ingredient, portionSize);

            JSONArray array3 = new JSONArray();

            for (Product product : productList) {
                JSONObject obj = new JSONObject();
                obj.put("type", product.getType());
                obj.put("meal", product.getMeal());
                obj.put("calories", product.getCalories());
                obj.put("carbs", product.getCarbs());
                obj.put("protein", product.getProtein());
                obj.put("fats", product.getFats());
                obj.put("fiber", product.getFiber());
                array3.put(obj);
            }

            return array3;
        });

        post("/addToPlan", (request, response) -> {
            planFormation.addMealToPlan();
            return getTextFromFile("index.html");
        });

        post("/plan", (request, response) -> {
            response.type("application/json");
            return planFormation.database.getUserPlan(); //planFormation.arrayPlan;
        });

        get("/insertProduct", (request, response) -> getTextFromFile("insertProduct.html"));

        post("/insertNewProduct", (request, response) -> {
            String type = request.queryParams("type");
            String meal = request.queryParams("meal");

            double calories = Double.parseDouble(request.queryParams("calories"));
            double carbs = Double.parseDouble(request.queryParams("carbs"));
            double protein = Double.parseDouble(request.queryParams("protein"));
            double fats = Double.parseDouble(request.queryParams("fats"));
            double fiber = Double.parseDouble(request.queryParams("fiber"));


            planFormation.database.insertProduct(type, meal, calories, carbs, protein, fats, fiber);

            return getTextFromFile("insertProduct.html");
        });

        get("/register", (request, response) -> getTextFromFile("register.html"));

        post("/registration", (request, response) ->{
            String username = request.queryParams("username");
            String password = request.queryParams("password");

           planFormation.database.createUser(username,password);

            User user = planFormation.database.getUser(
                    username,
                    password
            );

            if (user == null) {
                JSONObject error = new JSONObject();
                error.put("error", "404 error");
                return error.toString();
            }

            return getTextFromFile("login.html");
        });

        post("/macros", (request, response) -> {
            response.type("application/json");

            List<Macros> macrosList = planFormation.macrosList;


            JSONArray array = new JSONArray();

            for (Macros macros : macrosList) {
                JSONObject obj = new JSONObject();
                obj.put("Calories", macros.getTotalCalories());
                obj.put("Carbs", macros.getTotalCarbs());
                obj.put("Protein", macros.getTotalProtein());
                obj.put("Fats", macros.getTotalFats());
                obj.put("Fiber", macros.getTotalFiber());
                array.put(obj);
            }

            return array;
        });

        post("/productsByType", (request, response) -> {
            response.type("application/json");
            String type = request.queryParams("type");

            List<Product> products = planFormation.database.getProductsByType(type);

            JSONArray array = new JSONArray();

            for (Product product : products) {
                JSONObject obj = new JSONObject();
                obj.put("type", product.getType());
                obj.put("meal", product.getMeal());
                obj.put("calories", product.getCalories());
                obj.put("carbs", product.getCarbs());
                obj.put("protein", product.getProtein());
                obj.put("fats", product.getFats());
                obj.put("fiber", product.getFiber());
                array.put(obj);
            }




            return array;
        });

    }

    //Teksto nuskaitymas iš failo resursų kataloge
    private static String getTextFromFile(String path) {
        try {
            URI fullPath = Main.class.getClassLoader().getResource(path).toURI();
            return new String(Files.readAllBytes(Paths.get(fullPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Loading error";


    }

}
