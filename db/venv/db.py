# psql -h csce-315-db.engr.tamu.edu -U tchristopher457 -d postgres
# 627002599
# /c db907_group10_project2

# How to make venv
# python -m venv venv
# cd venv/Scripts
# . activate
# cd ../
# now you are sitting venv folder using python venv.

from csv import reader
import pandas as pd
import random
import names
import math

# This was used to create the allNamesDatesOrders_new.csv file from the original one given to us
def create_new_allNamesDatesOrders_file():
    columns = ['name', 'order_date', 'order_contents']
    data = []

    with open('../allNamesDatesOrders/allNamesDatesOrders.csv', 'r') as read_obj:
        csv_reader = reader(read_obj)

        for row in csv_reader:
            temp = []
            temp.append(' '.join(item.strip() for item in row[:2]))
            temp.append(row[2].strip())
            temp.append(' '.join(item.strip() for item in row[3:]))

            data.append(temp)

    df = pd.DataFrame(data, columns = columns)
    df.to_csv('../allNamesDatesOrders/allNamesDatesOrders_new.csv', index = False)

def create_customer_df():
    data = pd.read_csv('../allNamesDatesOrders/allNamesDatesOrders_new.csv')

    columns = ['id', 'name', 'address', 'email']

    customer_data = []

    customer_names = set(data['name'])

    count = 0
    for customer_name in customer_names:
        customer_data.append([str(count), customer_name, '', ''])
        count += 1

    return pd.DataFrame(customer_data, columns = columns)

def create_order_df(customer_name_to_id):
    data = pd.read_csv('../allNamesDatesOrders/allNamesDatesOrders_new.csv')

    columns = ['id', 'customer_id', 'employee_id', 'date', 'time', 'contents']

    order_data = []

    count = 1
    for index, row in data.iterrows():
        order_data.append([str(count), customer_name_to_id[row['name']], '', row['order_date'], '', row['order_contents']])
        count += 1

    return pd.DataFrame(order_data, columns = columns)

def create_customer_and_order_csv():
    customer_df = create_customer_df()

    customer_name_to_id = {}
    for index, row in customer_df.iterrows():
        if row['name'] not in customer_name_to_id:
            customer_name_to_id[row['name']] = row['id']

    order_df = create_order_df(customer_name_to_id)

    customer_df.to_csv('../tableData/customers.csv', index = False)
    order_df.to_csv('../tableData/orders.csv', index = False)

def create_employee_csv():
    columns = ['id', 'name', 'address', 'email']

    employee_data = []

    count = 1
    for i in range(1, 3 + 1):
        employee_data.append([str(count), names.get_last_name() + ' ' + names.get_first_name(), '', ''])
        count += 1
    
    employee_df = pd.DataFrame(employee_data, columns = columns)
    employee_df.to_csv('../tableData/employees.csv', index = False)

def create_entrees_csv():
    columns = ['id', 'name', 'price', 'calories', 'toppings']

    entrees_data = []
    toppings_data = pd.read_csv('../tableData/toppings.csv')

    count = 1
    for i in range(1, 7 + 1):
        random_toppings = []

        count = 0
        while count < 3:
            choice = random.choice(toppings_data['name'])
            if choice not in random_toppings:
                random_toppings.append(choice)
                count += 1

        entrees_data.append([str(count), 'E' + str(i), round(random.uniform(8.99, 10.99), 2), math.floor(random.uniform(1500, 2100)), ' '.join(topping for topping in random_toppings)])
        count += 1
    
    entrees_df = pd.DataFrame(entrees_data, columns = columns)
    entrees_df.to_csv('../tableData/entrees.csv', index = False)

def create_sides_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    sides_data = []

    count = 1
    for i in range(1, 4 + 1):
        sides_data.append([str(count), 'S' + str(i), round(random.uniform(1.99, 4.99), 2), math.floor(random.uniform(200, 500))])
        count += 1
    
    sides_df = pd.DataFrame(sides_data, columns = columns)
    sides_df.to_csv('../tableData/sides.csv', index = False)

def create_beverages_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    beverages_data = []

    count = 1
    for i in range(1, 5 + 1):
        beverages_data.append([str(count), 'B' + str(i), round(random.uniform(1.99, 4.99), 2), math.floor(random.uniform(200, 500))])
        count += 1
    
    beverages_df = pd.DataFrame(beverages_data, columns = columns)
    beverages_df.to_csv('../tableData/beverages.csv', index = False)

def create_desserts_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    desserts_data = []

    count = 1
    for i in range(1, 3 + 1):
        desserts_data.append([str(count), 'D' + str(i), round(random.uniform(1.99, 4.99), 2), math.floor(random.uniform(200, 500))])
        count += 1
    
    desserts_df = pd.DataFrame(desserts_data, columns = columns)
    desserts_df.to_csv('../tableData/desserts.csv', index = False)

def create_toppings_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    toppings_data = []

    count = 1
    for i in range(1, 5 + 1):
        toppings_data.append([str(count), 'T' + str(i), round(random.uniform(0.99, 1.49), 2), math.floor(random.uniform(50, 150))])
        count += 1
    
    toppings_df = pd.DataFrame(toppings_data, columns = columns)
    toppings_df.to_csv('../tableData/toppings.csv', index = False)

def create_meals_csv():
    columns = ['id', 'name', 'price', 'calories', 'contents']
    
    meals_data = []

    entrees_data = pd.read_csv('../tableData/entrees.csv')
    sides_data = pd.read_csv('../tableData/sides.csv')
    beverages_data = pd.read_csv('../tableData/beverages.csv')

    count = 1
    for i in range(1, 5 + 1):
        entree = random.choice(entrees_data['name'])
        side = random.choice(sides_data['name'])
        beverage = random.choice(beverages_data['name'])

        meal_price = float(entrees_data.loc[entrees_data['name'] == entree]['price']) + float(sides_data.loc[sides_data['name'] == side]['price']) + float(beverages_data.loc[beverages_data['name'] == beverage]['price'])
        meal_calories = int(entrees_data.loc[entrees_data['name'] == entree]['calories']) + int(sides_data.loc[sides_data['name'] == side]['calories']) + int(beverages_data.loc[beverages_data['name'] == beverage]['calories'])

        contents = [entree, side, beverage]
        meals_data.append([str(count), 'M' + str(i), round(meal_price, 2), meal_calories, ' '.join(item for item in contents)])
        count += 1
    
    meals_df = pd.DataFrame(meals_data, columns = columns)
    meals_df.to_csv('../tableData/meals.csv', index = False)

def add_total_price_to_orders():
    entrees_data = pd.read_csv('../tableData/entrees.csv')
    sides_data = pd.read_csv('../tableData/sides.csv')
    beverages_data = pd.read_csv('../tableData/beverages.csv')
    desserts_data = pd.read_csv('../tableData/desserts.csv')

    entree_names = entrees_data['name'].tolist()
    side_names = sides_data['name'].tolist()
    beverage_names = beverages_data['name'].tolist()
    dessert_names = desserts_data['name'].tolist()

    orders_data = pd.read_csv('../tableData/orders.csv')

    orders_total_price = []

    previous_orders_to_price = {}

    for index, row in orders_data.iterrows():
        orders_total_price.append(0.0)
        order_contents = row['contents']

        if order_contents not in previous_orders_to_price:
            for item in order_contents.split():
                if item in entree_names:
                    orders_total_price[-1] += float(entrees_data.loc[entrees_data['name'] == item]['price'])
                elif item in side_names:
                    orders_total_price[-1] += float(sides_data.loc[sides_data['name'] == item]['price'])
                elif item in beverage_names:
                    orders_total_price[-1] += float(beverages_data.loc[beverages_data['name'] == item]['price'])
                elif item in dessert_names:
                    orders_total_price[-1] += float(desserts_data.loc[desserts_data['name'] == item]['price'])
                else:
                    print('Unknown: \'' + item + '\'')
            orders_total_price[-1] = round(orders_total_price[-1], 2)
            previous_orders_to_price[order_contents] = orders_total_price[-1]
        else:
            orders_total_price[-1] = previous_orders_to_price[order_contents]
    
    orders_data['total_price'] = orders_total_price
    orders_data.to_csv('../tableData/orders.csv', index = False)

def generate_new_order_data(n):
    entrees_data = pd.read_csv('../tableData/entrees.csv')
    sides_data = pd.read_csv('../tableData/sides.csv')
    beverages_data = pd.read_csv('../tableData/beverages.csv')
    desserts_data = pd.read_csv('../tableData/desserts.csv')
    toppings_data = pd.read_csv('../tableData/toppings.csv')

    entree_names = entrees_data['name'].tolist()
    side_names = sides_data['name'].tolist()
    beverage_names = beverages_data['name'].tolist()
    dessert_names = desserts_data['name'].tolist()
    topping_names = toppings_data['name'].tolist()

    orders_data = pd.read_csv('../tableData/orders.csv')

    customers_data = pd.read_csv('../tableData/customers.csv')

    customer_names = set(customers_data['name'].tolist())

    new_customer_names = set()
    new_orders_contents = []

    for i in range(n):
        # Generate new customer name
        new_customer_name = names.get_last_name() + " " + names.get_first_name()

        while (new_customer_name in customer_names or new_customer_name in new_customer_names):
            new_customer_name = names.get_last_name() + " " + names.get_first_name()

        new_customer_names.add(new_customer_name)

        # Generate new order contents
        new_order_contents = []
        # Randomly decide how many orders are in this one order (1 order is entree + side + drink)
        for i in range(random.randint(1, 4)):
            random_entree = random.choice(entree_names)
            
            # Between 0-2 customizations to the entree
            for i in range(random.randint(0, 2)):
                if (random.randint(0, 1)):
                    random_entree += "+" + random.choice(topping_names)
                else:
                    random_entree += "-" + random.choice(topping_names)

            new_order_contents.append(random_entree)
            new_order_contents.append(random.choice(side_names))
            new_order_contents.append(random.choice(beverage_names))
            if (random.randint(0, 1)):
                new_order_contents.append(random.choice(dessert_names))
        
        new_orders_contents.append(" ".join(item for item in new_order_contents))

    df = pd.DataFrame()
    df['customer_name'] = list(new_customer_names)
    df['order_contents'] = new_orders_contents
    df.to_csv('../tableData/newOrders.csv', index = False)

if __name__ == '__main__':
    # create_customer_and_order_csv()
    # create_employee_csv()

    # create_beverages_csv()
    # create_desserts_csv()
    # create_toppings_csv()
    # create_sides_csv()

    # entrees table and meals table depend on the above to be created first.
    # create_entrees_csv()
    # create_meals_csv()

    # add_total_price_to_orders()

    generate_new_order_data(1000)

    print("Done.")