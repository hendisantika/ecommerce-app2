INSERT INTO user (id, account_failed_attempt_count, account_lock_time, account_status_non_locked, address, city, created_at, email, is_enable, mobile, name, password, pin_code, profile_image, reset_tokens, role, state, updated_at) VALUES (1, 0, null, true, 'KONOHA', 'BANDUNG', '2025-09-16 06:46:37.740830', 'hendi@yopmail.com', true, '+6281321411881', 'HENDI', '$2a$10$nJjvJeS8jXmiy2Jh67rqSuHVj0/885JSpkho1fCh05rxXgNtB37RO', '40377', 'whatsapp.png', null, 'ROLE_ADMIN', 'WEST JAVA', '2025-09-16 06:46:37.740870');
INSERT INTO user (id, account_failed_attempt_count, account_lock_time, account_status_non_locked, address, city, created_at, email, is_enable, mobile, name, password, pin_code, profile_image, reset_tokens, role, state, updated_at) VALUES (2, 0, null, true, 'KONOHA', 'BANDUNG', '2025-09-16 06:46:37.740830', 'yuji@yopmail.com', true, '+6281321411881', 'YUJI', '$2a$10$nJjvJeS8jXmiy2Jh67rqSuHVj0/885JSpkho1fCh05rxXgNtB37RO', '40377', 'whatsapp.png', null, 'ROLE_USER', 'WEST JAVA', '2025-09-16 06:46:37.740870');

-- Insert 10 default categories with real online images (only if they don't exist)
INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Electronics', 'https://images.unsplash.com/photo-1531297484001-80022131f5a1?w=400&h=300&fit=crop', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Clothing & Fashion', 'https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=400&h=300&fit=crop', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Home & Garden', 'https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=400&h=300&fit=crop', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Sports & Outdoors', 'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400&h=300&fit=crop', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Books & Media', 'https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400&h=300&fit=crop', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Health & Beauty', 'https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=400&h=300&fit=crop', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Toys & Games', 'https://wl-img-prd.s3-accelerate.amazonaws.com/aebe6096-d265-412c-aaaa-b6d283f61a14-h.png', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Automotive', 'https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?w=400&h=300&fit=crop', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Food & Beverages', 'https://picpradhitya.sch.id/wp-content/uploads/2023/11/FnB-Pic.jpg', true, NOW(), NOW());

INSERT IGNORE INTO category (category_name, category_image, is_active, created_at, updated_at) VALUES
('Office Supplies', 'https://garafour.com/wp-content/uploads/2024/11/office-supplies-list.jpg', true, NOW(), NOW());

-- Insert 10 default products with real online images
INSERT INTO product (product_title, product_description, product_category, product_price, product_stock, product_image, discount, discount_price, is_active, created_at, updated_at) VALUES
('Samsung Galaxy S24 Ultra', 'Latest flagship smartphone with advanced camera system, 512GB storage, and premium design. Features include 200MP camera, S Pen support, and all-day battery life.', 'Electronics', 1299.99, 50, 'https://radarjabar.disway.id/upload/eb62cd9ed2aa665d8a4fc45a2979086f.jpeg', 10, 1169.99, true, NOW(), NOW()),
('Nike Air Max 270 Sneakers', 'Comfortable and stylish running shoes with Air Max technology. Perfect for everyday wear and light workouts. Available in multiple colors and sizes.', 'Clothing & Fashion', 149.99, 100, 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/awjogtdnqxniqqk0wpgf/air-max-270-mens-shoes-KkLcGR.png', 15, 127.49, true, NOW(), NOW()),
('Instant Pot Duo 7-in-1', 'Multi-functional electric pressure cooker that replaces 7 kitchen appliances. Perfect for quick and healthy meal preparation for families.', 'Home & Garden', 79.99, 75, 'https://www.thespruceeats.com/thmb/tckTkrH4Stz7oaROI0JEi6XioCE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/1SP4067671-HeroSquare-feb46737e905436c814a0c92e1eeb860.jpg', 20, 63.99, true, NOW(), NOW()),
('Adidas Ultraboost 22', 'Premium running shoes with responsive cushioning and energy return. Designed for serious runners and fitness enthusiasts.', 'Sports & Outdoors', 189.99, 80, 'https://img.ncrsport.com/img/storage/large/GY8688-1.jpg', 12, 167.19, true, NOW(), NOW()),
('The Psychology of Money', 'Bestselling book by Morgan Housel about financial wisdom and behavioral economics. A must-read for anyone interested in personal finance.', 'Books & Media', 24.99, 200, 'https://m.media-amazon.com/images/I/81cpDaCJJCL._SY522_.jpg', 0, 24.99, true, NOW(), NOW()),
('CeraVe Hydrating Cleanser', 'Gentle facial cleanser suitable for all skin types. Developed with dermatologists and contains essential ceramides for healthy skin barrier.', 'Health & Beauty', 12.99, 150, 'https://medias.watsons.co.id/publishing/WTCID-38641-extra5-zoom.jpg?version=1728586277', 8, 11.95, true, NOW(), NOW()),
('LEGO Creator 3-in-1 Deep Sea Creatures', 'Build and rebuild 3 different sea creatures with this creative LEGO set. Perfect for kids aged 7+ and encourages imaginative play.', 'Toys & Games', 15.99, 120, 'https://images-cdn.ubuy.co.id/635438d770b1bd34a27e2f14-lego-creator-3in1-deep-sea-creatures.jpg', 5, 15.19, true, NOW(), NOW()),
('Castrol GTX High Mileage Motor Oil', 'Premium motor oil designed for vehicles with over 75,000 miles. Helps reduce oil burn-off and provides superior engine protection.', 'Automotive', 29.99, 60, 'https://images-cdn.ubuy.co.id/663950f9244cad76ef03e596-castrol-gtx-high-mileage-5w-20-synthetic.jpg', 0, 29.99, true, NOW(), NOW()),
('Starbucks Pike Place Roast Coffee', 'Medium roast whole bean coffee with smooth, balanced flavor. Perfect for everyday coffee lovers who appreciate quality and consistency.', 'Food & Beverages', 14.99, 180, 'https://kaffekapslen.media/media/catalog/product/cache/74c1057f7991b4edb2bc7bdaa94de933/c/b/cb-starbucks-450g-pike-place-roast-0001.jpg', 10, 13.49, true, NOW(), NOW()),
('HP OfficeJet Pro 9015e Printer', 'All-in-one wireless printer with print, scan, copy, and fax capabilities. Perfect for home offices and small businesses with high-quality output.', 'Office Supplies', 199.99, 40, 'https://images-cdn.ubuy.co.id/658a715b2784102df20274f9-hp-officejet-pro-9015e-wireless.jpg', 25, 149.99, true, NOW(), NOW());