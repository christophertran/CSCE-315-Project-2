/* 
    1. Connect to VPN

    2. Move this file into the same folder that you will be running the following command

    2. Connect to psql server using
        psql "-h csce-315-db.engr.tamu.edu -U tchristopher457 -d postgres"

    3. Once in the psql server, run
        psql \i database_correctness.sql

    4. Exit the server by using:
        \q
    
    5. View the output by using:
        vim "database_correctness_output.txt"
 */

\o database_correctness_output.txt
\c db907_group10_project2
\pset null (null)
\pset linestyle unicode
\pset border 2

\qecho '1. Orders from January 2018'
SELECT * FROM "orders" WHERE "date" BETWEEN '2018-01-01' AND '2018-01-31' LIMIT 10;

\qecho '2a. Unfulfilled orders'
SELECT * FROM "orders" WHERE "fulfilled" = 'false' LIMIT 10;

\qecho '2b. Fulfilled orders'
SELECT * FROM "orders" WHERE "fulfilled" = 'true' LIMIT 10;

\qecho '3. Meals between 2500 and 2600 calories'
SELECT * FROM "meals" WHERE "calories" BETWEEN 2500 AND 2600;

\qecho '4. Orders taken down by a specific employee (Mayer Robert)'
SELECT orders.id, orders.customer_id, orders.date, employees.name AS "employee_name" FROM "orders"
INNER JOIN "employees" ON orders.employee_id = employees.id
WHERE "employee_id" = 1
LIMIT 10;

\qecho '5. Orders by a specific customer'
SELECT orders.id, orders.date, customers.name, customers.email, orders.contents
FROM "orders"
INNER JOIN "customers" ON orders.customer_id = customers.id
WHERE "name" = 'LEWIS SARAH'
LIMIT 10;

\qecho '6. Total number of orders by day'
SELECT COUNT("date") AS "orders_on_date", "date" FROM "orders"
GROUP BY "date"
LIMIT 10;

\qecho '7. List of Employees'
SELECT * FROM "employees" LIMIT 10;

\qecho '8. List of Customers'
SELECT * FROM "customers" LIMIT 10;

\qecho '9. List of meals'
SELECT * FROM "meals" LIMIT 10;

\qecho '10. Average number of calories in meals'
SELECT AVG("calories") FROM "meals";

\qecho '11. Average price of meals'
SELECT AVG("price") FROM "meals";

\qecho '12. Name of recommendation when you start searching a customer name'
SELECT * FROM "customers" WHERE "name" LIKE 'D%' 
LIMIT 10;

\qecho '13. Insert into the meals table'
INSERT INTO meals("id", "name", "price", "calories", "contents")
VALUES(6, 'M6', 9.99, 700, 'E2 S1 B1');

\qecho '14. Changing the contents of an order'

\qecho '15. Deleting a meal'
DELETE FROM "meals" WHERE "id" = 6;

\qecho '16. Adding a topping to an order'
UPDATE "orders" SET "contents" = CONCAT("contents", ' T2')
WHERE "id" = 5944;

\qecho '17. Looking up a specific order'
SELECT * FROM "orders" WHERE "id" = 5944;

\qecho '18. Cheapest meal for recommendation'
SELECT MIN("price") FROM "meals";

\qecho '19. Lowest calorie food for recommendation'
SELECT MIN("calories") FROM "meals";

\qecho '20. Most expensive meal to provide a deal'
SELECT MAX("price") FROM "meals";
