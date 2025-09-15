-- Insert 10 default categories (only if they don't exist)
INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Electronics', 'electronics.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Clothing & Fashion', 'clothing.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Home & Garden', 'home-garden.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Sports & Outdoors', 'sports.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Books & Media', 'books.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Health & Beauty', 'health-beauty.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Toys & Games', 'toys.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Automotive', 'automotive.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Food & Beverages', 'food.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Office Supplies', 'office.jpg', true, NOW(), NOW());

-- Insert 10 default products
INSERT INTO product (product_title, product_description, product_category, product_price, product_stock, product_image, discount, discount_price, is_active, created_at, updated_at) VALUES
('Samsung Galaxy S24 Ultra', 'Latest flagship smartphone with advanced camera system, 512GB storage, and premium design. Features include 200MP camera, S Pen support, and all-day battery life.', 'Electronics', 1299.99, 50, 'samsung-galaxy-s24.jpg', 10, 1169.99, true, NOW(), NOW()),
('Nike Air Max 270 Sneakers', 'Comfortable and stylish running shoes with Air Max technology. Perfect for everyday wear and light workouts. Available in multiple colors and sizes.', 'Clothing & Fashion', 149.99, 100, 'nike-air-max-270.jpg', 15, 127.49, true, NOW(), NOW()),
('Instant Pot Duo 7-in-1', 'Multi-functional electric pressure cooker that replaces 7 kitchen appliances. Perfect for quick and healthy meal preparation for families.', 'Home & Garden', 79.99, 75, 'instant-pot-duo.jpg', 20, 63.99, true, NOW(), NOW()),
('Adidas Ultraboost 22', 'Premium running shoes with responsive cushioning and energy return. Designed for serious runners and fitness enthusiasts.', 'Sports & Outdoors', 189.99, 80, 'adidas-ultraboost-22.jpg', 12, 167.19, true, NOW(), NOW()),
('The Psychology of Money', 'Bestselling book by Morgan Housel about financial wisdom and behavioral economics. A must-read for anyone interested in personal finance.', 'Books & Media', 24.99, 200, 'psychology-of-money.jpg', 0, 24.99, true, NOW(), NOW()),
('CeraVe Hydrating Cleanser', 'Gentle facial cleanser suitable for all skin types. Developed with dermatologists and contains essential ceramides for healthy skin barrier.', 'Health & Beauty', 12.99, 150, 'cerave-cleanser.jpg', 8, 11.95, true, NOW(), NOW()),
('LEGO Creator 3-in-1 Deep Sea Creatures', 'Build and rebuild 3 different sea creatures with this creative LEGO set. Perfect for kids aged 7+ and encourages imaginative play.', 'Toys & Games', 15.99, 120, 'lego-deep-sea.jpg', 5, 15.19, true, NOW(), NOW()),
('Castrol GTX High Mileage Motor Oil', 'Premium motor oil designed for vehicles with over 75,000 miles. Helps reduce oil burn-off and provides superior engine protection.', 'Automotive', 29.99, 60, 'castrol-gtx-oil.jpg', 0, 29.99, true, NOW(), NOW()),
('Starbucks Pike Place Roast Coffee', 'Medium roast whole bean coffee with smooth, balanced flavor. Perfect for everyday coffee lovers who appreciate quality and consistency.', 'Food & Beverages', 14.99, 180, 'starbucks-pike-place.jpg', 10, 13.49, true, NOW(), NOW()),
('HP OfficeJet Pro 9015e Printer', 'All-in-one wireless printer with print, scan, copy, and fax capabilities. Perfect for home offices and small businesses with high-quality output.', 'Office Supplies', 199.99, 40, 'hp-officejet-pro.jpg', 25, 149.99, true, NOW(), NOW());