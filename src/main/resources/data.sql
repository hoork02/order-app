-- Insert 5 Sample Customers (FR-06, Assignment Requirement)
INSERT INTO customers (name, email, phone, address) VALUES
('Ali Khan', 'ali.khan@example.com', '+92 300 1234567', 'Lahore, Pakistan'),
('Sara Ahmed', 'sara.ahmed@example.com', '+92 321 7654321', 'Karachi, Pakistan'),
('Usman Raza', 'usman.raza@example.com', '+92 333 9876543', 'Islamabad, Pakistan'),
('Fatima Noor', 'fatima.noor@example.com', '+92 312 4567890', 'Faisalabad, Pakistan'),
('Zain Malik', 'zain.malik@example.com', '+92 345 6789012', 'Rawalpindi, Pakistan')
ON CONFLICT (email) DO NOTHING;

-- Insert 10 Sample Products (FR-06, Assignment Requirement)
INSERT INTO products (product_name, description, unit_price)
SELECT seed.product_name, seed.description, seed.unit_price
FROM (VALUES
    ('Mechanical Keyboard', 'RGB Mechanical Gaming Keyboard', 400.00),
    ('Wireless Mouse', 'Ergonomic Optical Mouse', 150.00),
    ('27-inch Monitor', '4K UHD IPS Display', 1200.00),
    ('USB-C Hub', '7-in-1 Multi-port Adapter', 80.00),
    ('HD Webcam', '1080p Autofocus Streaming Camera', 250.00),
    ('Noise-Canceling Headphones', 'Over-ear Wireless Headset', 600.00),
    ('Laptop Stand', 'Aluminum Adjustable Riser', 120.00),
    ('External SSD 1TB', 'USB 3.2 High-Speed Solid State Drive', 350.00),
    ('Desk Mat', 'Anti-slip Gaming Mouse Pad', 45.00),
    ('Microphone', 'Studio USB Condenser Mic', 300.00)
) AS seed(product_name, description, unit_price)
WHERE NOT EXISTS (
    SELECT 1
    FROM products existing
    WHERE existing.product_name = seed.product_name
);