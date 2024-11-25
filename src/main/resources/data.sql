use midtermjava;

INSERT INTO category(name) VALUES
                               ('Laptops'),
                               ('Smartphones'),
                               ('Tablets'),
                               ('Accessories'),
                               ('Software');

INSERT INTO product(name, category_id, price, weight, description, image_product, brand, color) VALUES

                                                                                                    ('LG UltraWide Monitor 34-inch', 3, 799, 5.0, 'A 34-inch ultra-wide monitor with 144Hz refresh rate, perfect for multitasking and gaming.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329427/ECommerce/lg-ultrawide.png', 'LG', 'Black'),
                                                                                                    ('Bose QuietComfort 35 II', 5, 299, 0.24, 'Noise-cancelling headphones with superior sound quality, comfortable fit, and long battery life.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329424/ECommerce/bose-headphones.png', 'Dell', 'White'),
                                                                                                    ('Apple Watch Series 8', 4, 399, 0.03, 'Smartwatch with advanced health features, fitness tracking, and cellular connectivity.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329426/ECommerce/apple-watch.png', 'MSI', 'Blue'),
                                                                                                    ('Razer DeathAdder V2', 4, 69, 0.08, 'High-precision gaming mouse with 20,000 DPI, Razer Optical Mouse Switches, and ergonomic design.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329425/ECommerce/razer-mouse.png', 'Logitech', 'Black'),
                                                                                                    ('Google Drive 1TB', 5, 99, 0.0, 'Cloud storage plan offering 1TB of space, perfect for storing and sharing files with ease.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329423/ECommerce/google-drive.png', 'Google', 'Green'),
                                                                                                    ('Seagate Backup Plus 5TB', 5, 129, 0.6, 'Portable external hard drive with 5TB capacity, perfect for backing up your important data.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329427/ECommerce/seagate-drive.png', 'Microsoft', 'White'),
                                                                                                    ('Sony WH-1000XM5', 1, 349, 0.25, 'Premium noise-cancelling headphones with exceptional sound, comfort, and up to 30 hours of battery life.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329426/ECommerce/sony-headphones.png', 'Sony', 'Black'),
                                                                                                    ('Samsung Galaxy Watch 5', 2, 279, 0.04, 'Smartwatch with health monitoring features, a sleek design, and long-lasting battery life.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329424/ECommerce/samsung-watch.png', 'SamSung', 'Black'),
                                                                                                    ('Logitech G Pro X Wireless', 1, 149, 0.1, 'Wireless gaming headset with Blue VO!CE technology, pro-level sound, and comfort for long gaming sessions.', 'https://res.cloudinary.com/dvzhmi7a9/image/upload/v1727329425/ECommerce/logitech-headset.png', 'Logitech', 'White');