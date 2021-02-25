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

    columns = ['id', 'name', 'address', 'email', 'previous_orders']

    customer_data = []

    customer_names = set(data['name'])

    count = 0
    for customer_name in customer_names:
        customer_data.append([str(count), customer_name, '', '', ''])
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

    customer_df.to_csv('../tableData/customer.csv', index = False)
    order_df.to_csv('../tableData/order.csv', index = False)

def create_employee_csv():
    columns = ['id', 'name', 'address', 'email']

    employee_data = []

    count = 1
    for i in range(1, 7 + 1):
        employee_data.append([str(count), '', '', ''])
        count += 1
    
    employee_df = pd.DataFrame(employee_data, columns = columns)
    employee_df.to_csv('../tableData/entrees.csv', index = False)

def create_entrees_csv():
    columns = ['id', 'name', 'price', 'calories', 'toppings']

    entrees_data = []

    count = 1
    for i in range(1, 7 + 1):
        entrees_data.append([str(count), 'E' + str(i), '', '', ''])
        count += 1
    
    entrees_df = pd.DataFrame(entrees_data, columns = columns)
    entrees_df.to_csv('../tableData/entrees.csv', index = False)

def create_sides_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    sides_data = []

    count = 1
    for i in range(1, 4 + 1):
        sides_data.append([str(count), 'S' + str(i), '', ''])
        count += 1
    
    sides_df = pd.DataFrame(sides_data, columns = columns)
    sides_df.to_csv('../tableData/sides.csv', index = False)

def create_beverages_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    beverages_data = []

    count = 1
    for i in range(1, 5 + 1):
        beverages_data.append([str(count), 'B' + str(i), '', ''])
        count += 1
    
    beverages_df = pd.DataFrame(beverages_data, columns = columns)
    beverages_df.to_csv('../tableData/beverages.csv', index = False)

def create_deserts_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    deserts_data = []

    count = 1
    for i in range(1, 2 + 1):
        deserts_data.append([str(count), 'D' + str(i), '', ''])
        count += 1
    
    deserts_df = pd.DataFrame(deserts_data, columns = columns)
    deserts_df.to_csv('../tableData/deserts.csv', index = False)

def create_toppings_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    toppings_data = []

    count = 1
    for i in range(1, 5 + 1):
        toppings_data.append([str(count), 'T' + str(i), '', ''])
        count += 1
    
    toppings_df = pd.DataFrame(toppings_data, columns = columns)
    toppings_df.to_csv('../tableData/toppings.csv', index = False)

def create_meal_csv():
    columns = ['id', 'name', 'price', 'calories']
    
    meal_data = []

    count = 1
    for i in range(1, 5 + 1):
        meal_data.append([str(count), 'M' + str(i), '', ''])
        count += 1
    
    meal_df = pd.DataFrame(meal_data, columns = columns)
    meal_df.to_csv('../tableData/meal.csv', index = False)

if __name__ == '__main__':
    # create_customer_and_order_csv()
    # create_beverages_csv()
    # create_deserts_csv()
    # This csv would be empty right now so don't make it
    # create_employee_csv()
    # create_entrees_csv()
    # create_sides_csv()
    # create_toppings_csv()
    create_meal_csv()

    print("Done.")