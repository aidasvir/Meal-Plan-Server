package vcs.lt.project;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;


    // @Field(analyze=Analyze.NO)
    @Column(name = "meal")
    private String meal;

    @Column(name = "calories")
    private double calories;

    @Column(name = "carbs")
    private double carbs;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fats")
    private double fats;

    @Column(name = "fiber")
    private double fiber;

    public Product() {
        this.id = 0;
        this.type = "";
        this.meal = "";
        this.calories = 0;
        this.carbs = 0;
        this.protein = 0;
        this.fats = 0;
        this.fiber = 0;
    }

    public Product(int id, String type, String meal, double calories, double carbs, double protein, double fats, double fiber) {
        this.id = id;
        this.type = type;
        this.meal = meal;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fats = fats;
        this.fiber = fiber;
    }

    public Product(String type, String meal, double calories, double carbs, double protein, double fats, double fiber) {
        this.type = type;
        this.meal = meal;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fats = fats;
        this.fiber = fiber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    @Override
    public String toString() {
        return type + " " + meal + " " + calories + " " + carbs + " " + protein + " " + fats + " " + fiber;
    }
}
