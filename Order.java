/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 12051033
 */

     
   public class Order       
    {
   //Declare variables, constants and data structures
    private final String pizza;
    private final String size;
    private final String drinks;
        //Constructor
        public Order(String pizza, String size, int type, String drinks)
        {
         //initialized value to instance variables
         this.pizza = pizza;
         this.size = size;
         this.drinks = drinks;
        }
        //Getters
        //getter for string pizza value    
        public String getPizza()
            {
                return pizza; // return the value
            }
         //getter for string size value    
            public String getSize()
            {
                return size; // return the value
            }
             //getter for string drinks value    
            public String getDrinks()
            {
                return drinks; // return the value
            }
    //declare double variables for the menu prices
    double pizzaPrice;
    double sizePrice;
    double drinkPrice;
    //Methods
    //method for pizza price
    public double pizzaPrices()
    {
        //set option for pizza type
        switch (getPizza()) {
            case "Hawaiian":
                pizzaPrice = 6.90; //price for Hawaiian
                break;
            case "BBQ Meatlovers":
                pizzaPrice = 7.00; // price for Meatlovers
                break;
            case "Chicken and Feta":
                pizzaPrice = 6.50; // price for Chicken and Feta
                break;
            case "Vegorama":
                pizzaPrice = 6.70; // price for Vegorama
                break;
            default:
                //print out put when when no pizza has been ordered
                System.out.println("No pizza has been ordered!");
                break;
        }
        return pizzaPrice; //get the pizza price depending on the selected item
    }
    //method to set price for size difference
    public double sizePrices()
    {
        //set option for sizes
        switch (getSize()) {
            case "Large":
                sizePrice = 2.00; // price for large
                break;
            case "Medium":
                sizePrice = 1.00; // price for medium
                break;
            case "Small":
                sizePrice = 0.00; // when it is small
                break;
            default:
                //print when no size is picked
                System.out.println("No Sizes has been picked!");
                break;
        }
        return sizePrice; //get the size price depending on the selected item
    }
    //method for drinks prices
    public double drinkPrices()
    {
        //set option for drinks
        switch (getDrinks()) {
            case "Juice":
                drinkPrice = 2.50; //price for juice
                break;
            case "Water":
                drinkPrice = 1.00; //price for water
                break;
            case "Coke":
                drinkPrice = 3.00; // price for coke
                break;
            case "Fanta":
                drinkPrice = 3.00; // price for fanta
                break;
            default:
                // when no drink is picked
                System.out.println("No Drinks has been picked!");
                break;
        }
        return drinkPrice; //get the drinks price depending on the selected item
    }   
   }






