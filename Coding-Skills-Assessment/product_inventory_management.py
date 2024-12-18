class Product:
    def __init__(self, name, price, quantity):
        self.name = name
        self.price = price
        self.quantity = quantity

    def __repr__(self):
        return f"Product(name='{self.name}', price={self.price}, quantity={self.quantity})"


# List of products
products = [
    Product("Laptop", 999.99, 5),
    Product("Smartphone", 499.99, 10),
    Product("Tablet", 299.99, 0),
    Product("Smartwatch", 199.99, 3),
]

# 1. Calculate Total Inventory Value
def calculate_total_inventory_value(products):
    return sum(product.price * product.quantity for product in products)


# 2. Find the Most Expensive Product
def find_most_expensive_product(products):
    return max(products, key = lambda p: p.price)


# 3. Check if a Product is in Stock
def is_product_in_stock(products, product_name):
    for product in products:
        if product.name.lower() == product_name.lower():
            return product.quantity > 0
    return False


# 4. Sort Products by Price or Quantity
def sort_products(products, by = "price", ascending = True):
    key = lambda p: p.price if by == "price" else p.quantity
    return sorted(products, key = key, reverse = not ascending)


# Outputs for each task
# Task 1
total_inventory_value = calculate_total_inventory_value(products)
print(f"Total Inventory Value: ${total_inventory_value:.2f}")

# Task 2
most_expensive_product = find_most_expensive_product(products)
print(f"Most Expensive Product: {most_expensive_product}")

# Task 3
product_to_check = "Tablet"
in_stock = is_product_in_stock(products, product_to_check)
print(f"Is '{product_to_check}' in stock? {'Yes' if in_stock else 'No'}")

# Task 4
sorted_by_price_asc = sort_products(products, by = "price", ascending = True)
sorted_by_quantity_desc = sort_products(products, by = "quantity", ascending = False)
print("Products sorted by price (ascending):", sorted_by_price_asc)
print("Products sorted by quantity (descending):", sorted_by_quantity_desc)
