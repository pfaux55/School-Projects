#  Shopping Cart System

## Author

Peter Faux

## Description

This project provides a comprehensive system for managing a shopping environment, including inventory management, shopping cart functionality, and a product catalog. The system is implemented in Python and includes classes and methods to manage products, inventory, shopping carts, and product catalogs.

## Requirements

- Python 3.x installed on your system

## Project Structure

- **@shoppingCart.py:** Contains the core classes (`Product`, `Inventory`, `ShoppingCart`, `ProductCatalog`) and functions for managing the shopping system.
- **driver.py:** Example script to demonstrate the functionality of the shopping cart system.
- **inventory.csv:** CSV file containing the initial inventory data.

## How to Use

### Running the Program

1. Ensure the `inventory.csv` file is present in the project directory. This file contains the initial inventory data.

2. Run the driver program to execute the shopping cart system:
   ```bash
   python driver.py
### Example Interaction
Upon running the program, you will see an output similar to the following:

    Current Inventory:
    Product1, Price1, Quantity1
    Product2, Price2, Quantity2

    Current Catalog:
    Product1, Price1, Category1
    Product2, Price2, Category2

    Price Category:
    Number of low price items: X
    Number of medium price items: Y
    Number of high price items: Z

    Adding products to cart:
    Filled the order

    Current cart:
    Product1, Quantity1

    Total: TotalPrice
    Buyer Name: BuyerName


## How It Works

The Shopping Cart and Inventory Management System is designed to simulate the operations of an online shopping platform. Below is a step-by-step explanation of how the system functions:

#### Product Class:
   Each product in the system is represented by the `Product` class. This class stores details such as the product's name, price, and category.
   Products are compared and managed based on these attributes, allowing the system to handle various operations like adding to inventory or a shopping cart.

#### Inventory Management:
   The `Inventory` class manages the available stock of products. It uses a dictionary where each product is associated with its price and available quantity.
   Products can be added or removed from the inventory, and the inventory can be displayed to show all available products along with their prices and quantities.

#### Shopping Cart:
   The `ShoppingCart` class represents a buyer's shopping cart. It tracks the products added to the cart and ensures that the inventory is updated accordingly.
   When a product is added to the cart, its quantity in the inventory is decreased. Conversely, when a product is removed from the cart, its quantity in the inventory is increased.

#### Product Catalog:
   The `ProductCatalog` class organizes the available products into different price categories: low, medium, and high.
   This classification helps in providing users with a better shopping experience by allowing them to browse products based on their budget.

#### Program Execution:
   The system starts by populating the inventory and product catalog using data from a CSV file (`inventory.csv`).
   The user can interact with the system through the `driver.py` script, which demonstrates adding products to the cart, removing products, and viewing both the cart and the inventory.

#### Example Interaction:
   As the program runs, it displays the current state of the inventory and shopping cart at each step. This interaction helps visualize how products move between the inventory and the cart, and how the total cost is calculated.

This section should help you understand the flow of operations in the system and how each component interacts with the others to manage the shopping environment.
