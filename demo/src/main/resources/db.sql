-- Accorder les droits de lecture et d'écriture sur toutes les tables du schéma public
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO DeliciosoDB_owner;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO DeliciosoDB_owner;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO DeliciosoDB_owner;
GRANT USAGE ON SCHEMA public TO DeliciosoDB_owner;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO DeliciosoDB_owner;
GRANT CREATE ON SCHEMA public TO DeliciosoDB_owner;
-- Pour les nouvelles tables qui seront créées à l'avenir dans ce schéma
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO DeliciosoDB_owner;

CREATE TABLE restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    cuisine_type VARCHAR(50),
    opening_hours VARCHAR(100)
);

ALTER TABLE restaurants ALTER COLUMN id TYPE INT USING id::INT;
ALTER SEQUENCE restaurants_id_seq AS INT;

INSERT INTO restaurants (name, address, phone_number, cuisine_type, opening_hours) VALUES
('Chez Gourmet', '123 Rue de Paris, 75001 Paris', '01 23 45 67 89', 'Française', '09:00 - 23:00'),
('La Bella Notte', '456 Via Roma, 00184 Roma', '+39 06 12345678', 'Italienne', '12:00 - 00:00'),
('Sushi Dreams', '789 Nihonbashi, Chuo, Tokyo 103-0027', '+81 3-1234-5678', 'Japonaise', '10:00 - 22:00');

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

ALTER TABLE roles ALTER COLUMN id TYPE INT USING id::INT;
ALTER SEQUENCE roles_id_seq AS INT;

INSERT INTO roles (role_name) VALUES
('User'),
('DeleveryMan');


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    username VARCHAR(50) UNIQUE NOT NULL,
    date_of_birth DATE,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    address VARCHAR(255),
    postal_code VARCHAR(20),
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    payment_id VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE SET NULL
);

ALTER TABLE users ALTER COLUMN id TYPE INT USING id::INT;
ALTER SEQUENCE users_id_seq AS INT;

INSERT INTO users (first_name, last_name, username, date_of_birth, email, password, role_id, address, postal_code, created_at) VALUES
('Charles', 'Radolanirina', 'charlesrado', '2002-07-18', 'charles.radolanirina@gmail.com', 'password123', 1, '45 Avenue Victor Hugo', '75116', NOW()),
('Giulietta', 'Pancari-Fauret', 'miapanc', '2002-05-17', 'giuliettapancari@gmail.com', 'password456', 2, '55 Rue du Faubourg Saint-Antoine', '75011', NOW()),
('Renata', 'Tejeda Mercado', 'renatatejmer', '2002-11-30', 'retm63397@eleve.isep.fr', 'password789', 1, '128 Rue de Rivoli', '75001', NOW()),
('Émile', 'Durand', 'emiledurand', '2001-03-12', 'emile.durand@gmail.com', 'motdepasse765', 1, '10 Rue de la Paix', '75002', NOW());

ALTER TABLE users DROP COLUMN role_id;
DELETE FROM users WHERE email= 'charles.radolanirina@gmail.com';
DELETE FROM users WHERE email= 'charles@gmail.com';
DELETE FROM users WHERE email= 'giuliettapancari@gmail.com';
DELETE FROM users WHERE email= 'retm63397@eleve.isep.fr';
DELETE FROM users WHERE email= 'emile.durand@gmail.com';

CREATE TABLE Products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    restaurant_id INT NOT NULL,
    price DECIMAL(10, 2),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(id)
);
ALTER TABLE products ALTER COLUMN product_id TYPE INT USING product_id::INT;
ALTER TABLE products ALTER COLUMN restaurant_id TYPE INT USING restaurant_id::INT;
ALTER SEQUENCE products_product_id_seq AS INT;

INSERT INTO Products (product_name, description, restaurant_id, price) VALUES
('Tarte Tatin', 'Traditional French upside-down apple tart, served warm.', 1, 12.50),
('Coq au Vin', 'Chicken braised with wine, mushrooms, and garlic.', 1, 18.00),
('Pizza Margherita', 'Classic Margherita with fresh tomatoes, mozzarella cheese, and basil.', 2, 15.00),
('Tiramisu', 'Coffee-flavoured Italian dessert made of ladyfingers dipped in coffee, layered with a whipped mixture of eggs, sugar, and mascarpone cheese.', 2, 7.50),
('Sushi Set', 'A variety set of nigiri and maki rolls.', 3, 20.00),
('Ramen', 'Traditional Japanese noodle soup with pork broth, noodles, eggs, and fresh vegetables.', 3, 14.00);


CREATE TABLE Reviews (
    review_id SERIAL PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL,
    restaurant_id INT NOT NULL,
    review_description TEXT,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(id)
);

ALTER TABLE reviews ALTER COLUMN review_id TYPE INT USING review_id::INT;
ALTER TABLE reviews ALTER COLUMN restaurant_id TYPE INT USING restaurant_id::INT;
ALTER SEQUENCE reviews_review_id_seq AS INT;


INSERT INTO Reviews (user_email, restaurant_id, review_description, rating) VALUES
('charles.radolanirina@gmail.com', 1, 'Excellent food and great service! Highly recommend the seafood platter.', 5),
('giuliettapancari@gmail.com', 1, 'Lovely atmosphere and friendly staff. The pasta was a bit overcooked.', 5),
('retm63397@eleve.isep.fr', 2, 'Great experience, the sushi rolls were fantastic!', 4),
('emile.durand@gmail.com', 3, 'Not worth the price. The steak was undercooked and service was slow.', 2);


CREATE TABLE Orders (
    order_id SERIAL PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL,
    restaurant_id INT NOT NULL,
    order_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    temperature DECIMAL(5, 2),
    product_id INT NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

ALTER TABLE orders ALTER COLUMN order_id TYPE INT USING order_id::INT;
ALTER TABLE orders ALTER COLUMN restaurant_id TYPE INT USING restaurant_id::INT;
ALTER TABLE orders ALTER COLUMN product_id TYPE INT USING product_id::INT;
ALTER SEQUENCE orders_order_id_seq AS INT;

INSERT INTO Orders (user_email, restaurant_id, order_time, temperature, product_id) VALUES
('charles.radolanirina@gmail.com', 1, '2024-05-17 12:00:00', 23.5, 1),
('giuliettapancari@gmail.com', 1, '2024-05-17 13:00:00', 24.0, 2),
('retm63397@eleve.isep.fr', 2, '2024-05-18 14:30:00', 22.0, 3),
('emile.durand@gmail.com', 3, '2024-05-19 17:30:00', 25.0, 4);


CREATE TABLE Deliveries (
    delivery_id SERIAL PRIMARY KEY,
    delivery_name VARCHAR(255),
    location TEXT,
    vehicle_type VARCHAR(50),
    restaurant_id INT NOT NULL,
    order_id INT NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(id),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

ALTER TABLE deliveries ALTER COLUMN delivery_id TYPE INT USING delivery_id::INT;
ALTER TABLE deliveries ALTER COLUMN restaurant_id TYPE INT USING restaurant_id::INT;
ALTER TABLE deliveries ALTER COLUMN order_id TYPE INT USING order_id::INT;
ALTER SEQUENCE deliveries_delivery_id_seq AS INT;

INSERT INTO Deliveries (delivery_name, location, vehicle_type, restaurant_id, order_id) VALUES
('Fast Wheels', '123 Main St, City A', 'Scooter', 1, 1),
('Quick Move', '234 Elm St, City B', 'Bicycle', 1, 2),
('Speedy Delivery', '345 Pine St, City C', 'Car', 2, 3),
('Rapid Transit', '456 Oak St, City D', 'Electric Scooter', 2, 4),
('Express Carriers', '567 Maple St, City E', 'Motorcycle', 3, 5);


ALTER TABLE restaurants OWNER TO DeliciosoDB_owner;
ALTER TABLE roles OWNER TO DeliciosoDB_owner;
ALTER TABLE users OWNER TO DeliciosoDB_owner;
ALTER TABLE products OWNER TO DeliciosoDB_owner;
ALTER TABLE reviews OWNER TO DeliciosoDB_owner;
ALTER TABLE orders OWNER TO DeliciosoDB_owner;
ALTER TABLE deliveries OWNER TO DeliciosoDB_owner;

SELECT * FROM pg_roles WHERE rolname = 'deliciosodb_owner';




CREATE TABLE PaymentInfo (
    PaymentInfoID SERIAL PRIMARY KEY,
    UserID INT NOT NULL,
    CardholderName VARCHAR(255),
    CardNumber BYTEA,  -- Stockage sécurisé, envisagez l'utilisation d'un module de cryptage
    CardType VARCHAR(50),
    ExpiryMonth INT,
    ExpiryYear INT,
    CVV BYTEA,
    BillingAddress VARCHAR(255),
    ZipCode VARCHAR(20),
    Country VARCHAR(100),
    CreatedAt TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    UpdatedAt TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    FOREIGN KEY (UserID) REFERENCES users(id)
);

SELECT grantee, privilege_type
FROM information_schema.role_table_grants
WHERE table_name='restaurants';


ALTER TABLE users DROP CONSTRAINT users_role_check;
ALTER TABLE users ADD CONSTRAINT users_role_check CHECK (role IN ('USER', 'ADMIN', 'DELIVERYMAN'));
SELECT * FROM users WHERE role NOT IN ('USER', 'ADMIN', 'DELIVERYMAN');
UPDATE users SET role = 'USER' WHERE role NOT IN ('USER', 'ADMIN', 'DELIVERYMAN');
DELETE FROM users WHERE role NOT IN ('USER', 'ADMIN', 'DELIVERYMAN');


