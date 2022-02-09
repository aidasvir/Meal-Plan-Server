package vcs.lt.project;

public class Macros {
    private double totalCalories;
    private double totalCarbs;
    private double totalProtein;
    private double totalFats;
    private double totalFiber;

    public Macros(double totalCalories, double totalCarbs, double totalProtein, double totalFats, double totalFiber) {
        this.totalCalories = totalCalories;
        this.totalCarbs = totalCarbs;
        this.totalProtein = totalProtein;
        this.totalFats = totalFats;
        this.totalFiber = totalFiber;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalFats() {
        return totalFats;
    }

    public void setTotalFats(double totalFats) {
        this.totalFats = totalFats;
    }

    public double getTotalFiber() {
        return totalFiber;
    }

    public void setTotalFiber(double totalFiber) {
        this.totalFiber = totalFiber;
    }

    @Override
    public String toString() {
        return "Total macronutrient of the meal: "
                + "Calories: " + totalCalories + " "
                + "Carbs: " + totalCarbs + " "
                + "Protein: " + totalProtein + " "
                + "Fats: " + totalFats + " "
                + "Fiber: " + totalFiber;
    }
}
