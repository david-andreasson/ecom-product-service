INSERT INTO categories (id, name, slug, description)
VALUES ('11111111-1111-1111-1111-111111111111', 'Uncategorized', 'uncategorized', 'Standardkategori'),
       ('22222222-2222-2222-2222-222222222222', 'Phones', 'phones', 'Mobiltelefoner'),
       ('33333333-3333-3333-3333-333333333333', 'Laptops', 'laptops', 'Bärbara datorer'),
       ('44444444-4444-4444-4444-444444444444', 'Accessories', 'accessories', 'Tillbehör'),
       ('55555555-5555-5555-5555-555555555555', 'Audio', 'audio', 'Hörlurar & högtalare');


INSERT INTO products
(id, name, slug, description, price, currency, category_id, stock_quantity, active, version, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'iPhone 15', 'iphone-15', 'iPhone 15 med USB-C', 9999.00, 'SEK',
        '22222222-2222-2222-2222-222222222222', 25, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'iPhone 15 Pro', 'iphone-15-pro', 'Pro-modell i titan', 12999.00, 'SEK',
        '22222222-2222-2222-2222-222222222222', 15, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'Pixel 8', 'pixel-8', 'Googles Pixel 8', 7990.00, 'SEK',
        '22222222-2222-2222-2222-222222222222', 40, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'MacBook Air 13 M3', 'macbook-air-13-m3', 'Tunn och lätt, M3', 13990.00,
        'SEK', '33333333-3333-3333-3333-333333333333', 12, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa5', 'MacBook Pro 14 M3', 'macbook-pro-14-m3', 'Proffsprestanda', 23990.00,
        'SEK', '33333333-3333-3333-3333-333333333333', 8, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa6', 'Dell XPS 13', 'dell-xps-13', 'Snygg 13\" ultrabook', 15990.00, 'SEK',
        '33333333-3333-3333-3333-333333333333', 10, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa7', 'AirPods Pro 2', 'airpods-pro-2', 'Aktiv brusreducering', 3290.00,
        'SEK', '44444444-4444-4444-4444-444444444444', 50, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa8', 'Sony WH-1000XM5', 'sony-wh-1000xm5', 'Branschledande ANC', 3990.00,
        'SEK', '55555555-5555-5555-5555-555555555555', 22, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa9', 'iPhone 15 Skal', 'iphone-15-skal', 'MagSafe-kompatibelt skal', 399.00,
        'SEK', '44444444-4444-4444-4444-444444444444', 150, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa10', 'USB-C Kabel 2 m', 'usb-c-kabel-2m', 'Snabbladdning', 199.00, 'SEK',
        '44444444-4444-4444-4444-444444444444', 300, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
