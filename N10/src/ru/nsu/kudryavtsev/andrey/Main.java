package ru.nsu.kudryavtsev.andrey;

class Restaurant
{
    private int orders = 0;

    public void addOrder()
    {
        orders++;
    }

    public int getOrders()
    {
        return orders;
    }
}

class Meal
{
    private boolean isReady = false;

    public void setReady(boolean flag)
    {
        isReady = flag;
    }

    public boolean isReady()
    {
        return isReady;
    }
}

class Waiter implements Runnable
{
    private final Restaurant restaurant;
    private final Meal meal;

    public Waiter(Restaurant restaurant, Meal meal)
    {
        this.restaurant = restaurant;
        this.meal = meal;
    }

    private void serve()
    {
        System.out.println("Serve " + restaurant.getOrders() + " meal");
    }

    @Override
    public void run()
    {
        while (restaurant.getOrders() != 10)
        {
            synchronized (meal)
            {
                try
                {
                    meal.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            serve();
            synchronized (meal)
            {
                meal.notify();
            }
        }
    }
}

class Chef implements Runnable
{
    private final Restaurant restaurant;
    private final Meal meal;

    public Chef(Restaurant restaurant, Meal meal)
    {
        this.restaurant = restaurant;
        this.meal = meal;
    }

    private void cook()
    {
        restaurant.addOrder();
        System.out.println("Cook " + restaurant.getOrders() + " meal");
    }

    @Override
    public void run()
    {
        while (restaurant.getOrders() != 10)
        {
            cook();
            synchronized (meal)
            {
                meal.notify();
                try
                {
                    meal.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args)
    {
        Meal meal = new Meal();
        Restaurant restaurant = new Restaurant();
	    Thread waiter = new Thread(new Waiter(restaurant, meal));
	    Thread chief = new Thread(new Chef(restaurant, meal));

	    waiter.start();
	    chief.start();
    }
}
