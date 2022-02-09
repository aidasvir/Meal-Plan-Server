package vcs.lt.project;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    private List<Meal> meals = new ArrayList<>();

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    @Override
    public String toString() {
        return meals.toString();
    }
}
