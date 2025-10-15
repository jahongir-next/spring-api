INSERT INTO categories (name)
VALUES ('Fruits & Vegetables'),
       ('Dairy & Eggs'),
       ('Bakery'),
       ('Beverages'),
       ('Snacks');
INSERT INTO products (name, price, description, category_id)
VALUES
-- Fruits & Vegetables
('Bananas', 1.29, 'Fresh organic bananas, rich in potassium and perfect for smoothies or snacks.', 1),
('Tomatoes', 2.49, 'Juicy red tomatoes, ideal for salads, sauces, or sandwiches.', 1),

-- Dairy & Eggs
('Whole Milk 1L', 1.89, 'Fresh whole cow’s milk, pasteurized and rich in calcium.', 2),
('Free-Range Eggs (12 pack)', 3.99, 'Organic free-range chicken eggs, large size.', 2),

-- Bakery
('Whole Wheat Bread', 2.59, 'Soft and healthy whole wheat bread baked fresh daily.', 3),
('Croissant (Pack of 4)', 3.49, 'Buttery French-style croissants with a light flaky texture.', 3),

-- Beverages
('Orange Juice 1L', 2.99, '100% pure orange juice with no added sugar or preservatives.', 4),
('Mineral Water 1.5L', 0.89, 'Natural mineral water sourced from mountain springs.', 4),

-- Snacks
('Potato Chips (Salted)', 1.49, 'Crispy salted potato chips made from farm-fresh potatoes.', 5),
('Chocolate Bar', 1.99, 'Smooth milk chocolate bar with creamy texture and rich flavor.', 5);
