"""
SHOPPING CART
December 2nd, 2023
Peter Faux
pfaux 


This program provides a comprehensive system for managing a shopping environment, including inventory management,
shopping cart functionality, and a product catalog.
"""


# Product class deals with instances of a new product including attributes for the name, price and category.
# Provides cases for equality between objects of the product class and data retrieval and string representation
class Product:
    # Initalize a new instance of Product with name, price and category
    # name is the name of the product
    # price is the price of the product
    # category is the category in which the product belongs
    def __init__(self, name, price, category):
        # Initialize product attributes
        self._name = name
        self._price = price
        self._category = category

    # Define how products are classified
    # two products are considered equal if they have the same name, price and category
    def __eq__(self, other):
        if isinstance(other, Product):
            if ((self._name == other._name and self._price == other._price) and (self._category == other._category)):
                return True
            else:
                return False
        else:
            return False

    # retrieval of the name of the product
    def get_name(self):
        return self._name

    # retrieval of the price of the product
    def get_price(self):
        return self._price

    # retrieval of the category of the product
    def get_category(self):
        return self._category

    # Implement string representation
    # returns the string 'rep' that represents the instance of the product
    def __repr__(self):
        rep = 'Product(' + self._name + ',' + str(self._price) + ',' + self._category + ')'
        return rep


# The inventory class is used to deal with the inventory for products using a dictionary that the 'cart' can select from
# includes adding, removing products and displaying the inventory as a whole
class Inventory:
    # creates an instance dictionary 'inventDict' for the inventory for products to be retrieved from
    def __init__(self):
        self.inventDict = {}

    # adds a product to the dictionary using the product name as the key and contains the product price and quantity
    # in a list as the value, in that order
    # productName is the name of the product
    # productPrice is the price of the product
    # productQuantity is the quantity of the specific product available
    def add_to_productInventory(self, productName, productPrice, productQuantity):
        self.inventDict[productName] = [int(productPrice), int(productQuantity)]

    # adds given product quantity to specified product
    # addQuantity is the quantity to be added to the particular product of the inventory
    def add_productQuantity(self, nameProduct, addQuantity):
        self.inventDict[nameProduct][1] += addQuantity

    # removes given product quantity to specified product
    # removeQuantity is the quantity to be added to the particular product of the inventory
    def remove_productQuantity(self, nameProduct, removeQuantity):
        self.inventDict[nameProduct][1] -= removeQuantity

    # retrieves price of specified product
    def get_productPrice(self, nameProduct):
        return self.inventDict[nameProduct][0]

    # obtains the quantity price of specified product and if it does not exist returns none
    def get_productQuantity(self, nameProduct):
        if nameProduct in self.inventDict:
            return self.inventDict[nameProduct][1]
        else:
            return

    # displays the inventory dictionary with name, price and quantity of each product
    # item represents the current product being delt with in the loop through the inventDict inventory dictionary
    def display_Inventory(self):
        for item in self.inventDict:
            print(f"{item}, {self.inventDict[item][0]}, {self.inventDict[item][1]}")


# The ShoppingCart class represents the shopping cart of a particular buyer. It handles buying and returning
# products and displaying what is in the cart from the inventory and updates the inventory when an item is bought or
# returned.
class ShoppingCart:

    # creates an instance variable for buyer name 'buyerName' and an instance dictionary for the shopping cart
    # inventory represents an instance of the inventory class
    def __init__(self, buyerName, inventory):
        self._buyerName = buyerName
        self.inventory = inventory
        self._shopping_cart = {}

    # adds a given quantity of a specified product to the cart and removes the given quantity of the specified
    # product from the inventory as long as the inventory contains enough of the specified product
    # requestedQuantity represents the quantity being added to the cart and removed from the inventory
    # inventQuantity represents the quantity of the product that exists in the inventory
    def add_to_cart(self, nameProduct, requestedQuantity):

        # inventQuantity represents the quantity in the inventory
        inventQuantity = self.inventory.get_productQuantity(nameProduct)

        # if the requested quantity is less then or equal to the quantity in the inventory and the product hasn't
        # been added to the cart yet and the product is in the inventory then the quantity of the product and the
        # product itself is added to the cart and removed from the inventory
        if (inventQuantity >= requestedQuantity) and (nameProduct not in self._shopping_cart) and (
                inventQuantity != 'None'):
            self._shopping_cart[nameProduct] = [self.inventory.get_productPrice(nameProduct), requestedQuantity]
            self.inventory.remove_productQuantity(nameProduct, requestedQuantity)
            return "Filled the order"

        # if the requested quantity is less then or equal to the quantity in the inventory and the product HAS been
        # added to the cart and the product is in the inventory then the quantity of the product is added to the cart
        # and removed from the inventory
        elif inventQuantity >= requestedQuantity and nameProduct in self._shopping_cart and (
                inventQuantity != 'None'):
            self._shopping_cart[nameProduct][1] += requestedQuantity
            self.inventory.remove_productQuantity(nameProduct, requestedQuantity)
            return "Filled the order"

        else:
            return "Can not fill the order"

    # this function removes a specified quantity of a specified product from the cart as long as there is enough of
    # the product to be removed and the product exists
    # requestedQuantity represents the quantity being removed from the cart and added to the inventory
    def remove_from_cart(self, nameProduct, requestedQuantity):
        # inventQuantity represents the quantity in the inventory
        inventQuantity = self.inventory.get_productQuantity(nameProduct)
        # if the product exists both in the inventory and in the shopping cart
        if (inventQuantity != 'None') and (nameProduct in self._shopping_cart):
            # if the quantity in the shopping cart is greater than or equal to what is requested to be taken out
            if self._shopping_cart[nameProduct][1] >= requestedQuantity:
                self.inventory.add_productQuantity(nameProduct, requestedQuantity)
                self._shopping_cart[nameProduct][1] -= requestedQuantity
                # if self._shopping_cart[nameProduct][1] == 0:
                #     self._shopping_cart.pop(nameProduct)
                return "Successful"
            else:
                return "The requested quantity to be removed from cart exceeds what is in the cart"
        else:
            return "Product not in the cart"

    # displays each product that is in the cart and how many of each, the total cost of what is in the cart and the
    # name of the buyer.
    # item represent the current product being printed
    def view_cart(self):
        total = 0
        for item in self._shopping_cart:
            print(f"{item}  {self._shopping_cart[item][1]}")
            total += (self._shopping_cart[item][0] * self._shopping_cart[item][1])

        print("Total: ", total)
        print("Buyer Name: ", self._buyerName)


# The ProductCatalog class represents the catalog of products available to be added to the cart, and it categorizes
# them by price
class ProductCatalog:
    # Initialization of a list of products and sets for each category for prices of products
    # products is a list of product objects
    # highPrices is a set of product object names with a price higher than 500
    # medium_prices is a set of product object names with a price between 500 and 100
    # low_prices is a set of product object names with a price under 100
    def __init__(self):
        self._products = []
        self._highPrices = set()
        self._medium_prices = set()
        self._low_prices = set()

    # addProduct adds a product object to the products list
    # product represents a product object
    def addProduct(self, product):
        self._products.append(product)

    # price_category manages the categorization of all the products into different price categories as specified in
    # the comments of the __init__() function of this class
    # product represents the current product being delt with while iterating through the products list
    def price_category(self):
        for product in self._products:
            if int(product.get_price()) > 500:
                self._highPrices.add(product.get_name)
            elif 499 >= int(product.get_price()) >= 100:
                self._medium_prices.add(product.get_name)
            elif 100 > int(product.get_price()) > 0:
                self._low_prices.add(product.get_name)

        print("Number of low price items: ", len(self._low_prices))
        print("Number of medium price items: ", len(self._medium_prices))
        print("Number of high price items: ", len(self._highPrices))

    # display_catalog outputs the entire catalog including the product name, price and the category that it is a part of
    # product represents the current product being delt with while iterating through the products list
    def display_catalog(self):
        for product in self._products:
            print(f"Product: {product.get_name()} Price: {product.get_price()} Category: {product.get_category()}")


# the populate_inventory function fills the inventory dictionary of the Inventory class with products that are in the
# csv file given by the driver program
# filename represents the name of the csv file to be opened and read
# lines is a list of all the lines in the file
# line represents the current line being processed in the lines list
# values is a list of all the separate values separated by commas in the csv file
# new_inventory represents the new object of the Inventory class being created with the values processed
def populate_inventory(filename):
    new_inventory = Inventory()
    with open(filename, "r") as file:
        lines = file.readlines()
        for line in lines:
            if line != "":
                values = line.split(",")
                new_inventory.add_to_productInventory(values[0], values[1], values[2])

    return new_inventory


# the populate_inventory function fills the products list of the ProductCatalog class with objects of products that
# are in the csv file given by the driver program
# fileName represents the name of the csv file to be opened and read
# new_catalog represents the new object of the ProductCatalog class being created
# lines is a list of all the lines in the file
# line represents the current line being processed in the lines list
# values is a list of all the separate values separated by commas in the csv file
# new_Product is an object of the product class being created to be added
# to the products list in the ProductCatalog class
def populate_catalog(fileName):
    new_catalog = ProductCatalog()
    with open(fileName, "r") as file:
        lines = file.readlines()
        for line in lines:
            if line != "":
                values = line.split(",")
                new_Product = Product(values[0], values[1], values[3])
                new_catalog.addProduct(new_Product)

    return new_catalog
