USE MidtermJava;

INSERT INTO category(name) VALUES
                               ('Laptops'),
                               ('Smartphones'),
                               ('Tablets'),
                               ('Accessories'),
                               ('Software');

INSERT INTO product(name, category_id, price, weight, description, image_name) VALUES

                                                                                   ('LG UltraWide Monitor 34-inch', 18, 799, 5.0, 'A 34-inch ultra-wide monitor with 144Hz refresh rate, perfect for multitasking and gaming.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329427/ECommerce/lg-ultrawide.png'),
                                                                                   ('Bose QuietComfort 35 II', 19, 299, 0.24, 'Noise-cancelling headphones with superior sound quality, comfortable fit, and long battery life.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329424/ECommerce/bose-headphones.png'),
                                                                                   ('Apple Watch Series 8', 20, 399, 0.03, 'Smartwatch with advanced health features, fitness tracking, and cellular connectivity.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329426/ECommerce/apple-watch.png'),
                                                                                   ('Razer DeathAdder V2', 21, 69, 0.08, 'High-precision gaming mouse with 20,000 DPI, Razer Optical Mouse Switches, and ergonomic design.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329425/ECommerce/razer-mouse.png'),
                                                                                   ('Google Drive 1TB', 22, 99, 0.0, 'Cloud storage plan offering 1TB of space, perfect for storing and sharing files with ease.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329423/ECommerce/google-drive.png'),
                                                                                   ('Seagate Backup Plus 5TB', 22, 129, 0.6, 'Portable external hard drive with 5TB capacity, perfect for backing up your important data.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329427/ECommerce/seagate-drive.png'),
                                                                                   ('Sony WH-1000XM5', 19, 349, 0.25, 'Premium noise-cancelling headphones with exceptional sound, comfort, and up to 30 hours of battery life.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329426/ECommerce/sony-headphones.png'),
                                                                                   ('Samsung Galaxy Watch 5', 20, 279, 0.04, 'Smartwatch with health monitoring features, a sleek design, and long-lasting battery life.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329424/ECommerce/samsung-watch.png'),
                                                                                   ('Logitech G Pro X Wireless', 21, 149, 0.1, 'Wireless gaming headset with Blue VO!CE technology, pro-level sound, and comfort for long gaming sessions.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329425/ECommerce/logitech-headset.png');