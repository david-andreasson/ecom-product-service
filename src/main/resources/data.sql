-- data.sql — seed categories, products (16 items / 4 categories), images & attributes
-- Designed for your Product-Service schema (tables: CATEGORIES, PRODUCTS, PRODUCT_IMAGES, PRODUCT_ATTRIBUTES)
-- Notes:
-- * Image URLs are from Wikimedia Commons (free licenses). See the commit message or your docs for attribution.
-- * Timestamps use CURRENT_TIMESTAMP to work in H2/Postgres.
-- * UUIDs are fixed so FK relations work reliably across inserts.

-- ======= CATEGORIES =======
INSERT INTO CATEGORIES (ID, NAME, SLUG, DESCRIPTION) VALUES
  ('11111111-1111-1111-1111-111111111111', 'Smartphones', 'smartphones', 'Mobiler och smartphones'),
  ('22222222-2222-2222-2222-222222222222', 'Laptops', 'laptops', 'Bärbara datorer'),
  ('33333333-3333-3333-3333-333333333333', 'Headphones', 'headphones', 'Hörlurar och headsets'),
  ('44444444-4444-4444-4444-444444444444', 'Cameras', 'cameras', 'Digitalkameror och tillbehör');

-- ======= PRODUCTS =======
-- Columns: ID, NAME, SLUG, DESCRIPTION, PRICE, CURRENCY, CATEGORY_ID, STOCK_QUANTITY, ACTIVE, VERSION, CREATED_AT, UPDATED_AT

-- Smartphones (4)
INSERT INTO PRODUCTS (ID, NAME, SLUG, DESCRIPTION, PRICE, CURRENCY, CATEGORY_ID, STOCK_QUANTITY, ACTIVE, VERSION, CREATED_AT, UPDATED_AT) VALUES
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Nordic One',  'nordic-one',  'Prisvärd smartphone med 6.1" skärm och 128 GB lagring.', 3999.00, 'SEK', '11111111-1111-1111-1111-111111111111', 35, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Nordic One Plus', 'nordic-one-plus', 'Snabbare processor och 256 GB lagring.', 4999.00, 'SEK', '11111111-1111-1111-1111-111111111111', 18, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'Aurora Mini', 'aurora-mini', 'Kompakt 5.8" med lång batteritid.', 3299.00, 'SEK', '11111111-1111-1111-1111-111111111111', 24, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'Aurora Pro',  'aurora-pro',  'OLED 6.7", trippelkamera och 5G.', 6999.00, 'SEK', '11111111-1111-1111-1111-111111111111', 12, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Laptops (4)
INSERT INTO PRODUCTS (ID, NAME, SLUG, DESCRIPTION, PRICE, CURRENCY, CATEGORY_ID, STOCK_QUANTITY, ACTIVE, VERSION, CREATED_AT, UPDATED_AT) VALUES
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'PolarBook 13', 'polarbook-13', '13" ultralätt kontorsdator.', 9999.00, 'SEK', '22222222-2222-2222-2222-222222222222', 20, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'PolarBook 15', 'polarbook-15', '15" allround med bra batteritid.', 10999.00, 'SEK', '22222222-2222-2222-2222-222222222222', 16, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'DevStation 15', 'devstation-15', 'Utvecklarfokuserad med 32 GB RAM.', 14999.00, 'SEK', '22222222-2222-2222-2222-222222222222', 10, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'Creator 16',  'creator-16',  '16" skärm och dedikerad grafik.', 18999.00, 'SEK', '22222222-2222-2222-2222-222222222222', 8, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Headphones (4)
INSERT INTO PRODUCTS (ID, NAME, SLUG, DESCRIPTION, PRICE, CURRENCY, CATEGORY_ID, STOCK_QUANTITY, ACTIVE, VERSION, CREATED_AT, UPDATED_AT) VALUES
  ('cccccccc-cccc-cccc-cccc-ccccccccccc1', 'Cloud Ears', 'cloud-ears', 'Over-ear med aktiv brusreducering.', 1490.00, 'SEK', '33333333-3333-3333-3333-333333333333', 40, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc2', 'Cloud Ears Pro', 'cloud-ears-pro', 'Premium ljud, multi-point BT.', 2190.00, 'SEK', '33333333-3333-3333-3333-333333333333', 22, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc3', 'StreetBuds', 'streetbuds', 'True wireless med snabbladdning.', 990.00, 'SEK', '33333333-3333-3333-3333-333333333333', 55, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc4', 'Studio Monitor', 'studio-monitor', 'Öppen konstruktion för studio.', 1790.00, 'SEK', '33333333-3333-3333-3333-333333333333', 14, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Cameras (4)
INSERT INTO PRODUCTS (ID, NAME, SLUG, DESCRIPTION, PRICE, CURRENCY, CATEGORY_ID, STOCK_QUANTITY, ACTIVE, VERSION, CREATED_AT, UPDATED_AT) VALUES
  ('dddddddd-dddd-dddd-dddd-ddddddddddd1', 'SnapShot X100', 'snapshot-x100', 'Kompaktkamera med 4K-video.', 3990.00, 'SEK', '44444444-4444-4444-4444-444444444444', 18, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd2', 'SnapShot X200', 'snapshot-x200', 'Större sensor och bättre AF.', 5990.00, 'SEK', '44444444-4444-4444-4444-444444444444', 12, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd3', 'ProShot S1', 'proshot-s1', 'Spegelös systemkamera för entusiaster.', 13990.00, 'SEK', '44444444-4444-4444-4444-444444444444', 9, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd4', 'ProShot S1 Kit', 'proshot-s1-kit', 'S1 med 24–70mm kit-objektiv.', 17990.00, 'SEK', '44444444-4444-4444-4444-444444444444', 6, TRUE, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ======= PRODUCT_IMAGES =======
-- Columns: PRODUCT_ID, POSITION, URL, FILE_NAME, CONTENT_TYPE, SIZE_BYTES
-- Re-using reliable Commons-hosted images per category.
-- Smartphone image (CC BY-SA 4.0): File:Smartphone with black background (link in docs)
INSERT INTO PRODUCT_IMAGES (PRODUCT_ID, POSITION, URL, FILE_NAME, CONTENT_TYPE, SIZE_BYTES) VALUES
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 0, 'https://upload.wikimedia.org/wikipedia/commons/e/ea/Smatphone.jpg', 'Smatphone.jpg', 'image/jpeg', 120000),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 0, 'https://upload.wikimedia.org/wikipedia/commons/e/ea/Smatphone.jpg', 'Smatphone.jpg', 'image/jpeg', 120000),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 0, 'https://upload.wikimedia.org/wikipedia/commons/e/ea/Smatphone.jpg', 'Smatphone.jpg', 'image/jpeg', 120000),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 0, 'https://upload.wikimedia.org/wikipedia/commons/e/ea/Smatphone.jpg', 'Smatphone.jpg', 'image/jpeg', 120000);

-- Laptop image (CC BY 2.0): File:Laptop.jpg
INSERT INTO PRODUCT_IMAGES (PRODUCT_ID, POSITION, URL, FILE_NAME, CONTENT_TYPE, SIZE_BYTES) VALUES
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 0, 'https://upload.wikimedia.org/wikipedia/commons/8/8b/Laptop.jpg', 'Laptop.jpg', 'image/jpeg', 90000),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 0, 'https://upload.wikimedia.org/wikipedia/commons/8/8b/Laptop.jpg', 'Laptop.jpg', 'image/jpeg', 90000),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 0, 'https://upload.wikimedia.org/wikipedia/commons/8/8b/Laptop.jpg', 'Laptop.jpg', 'image/jpeg', 90000),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 0, 'https://upload.wikimedia.org/wikipedia/commons/8/8b/Laptop.jpg', 'Laptop.jpg', 'image/jpeg', 90000);

-- Headphones image (GFDL/CC BY-SA): File:Headphones 1.jpg
INSERT INTO PRODUCT_IMAGES (PRODUCT_ID, POSITION, URL, FILE_NAME, CONTENT_TYPE, SIZE_BYTES) VALUES
  ('cccccccc-cccc-cccc-cccc-ccccccccccc1', 0, 'https://upload.wikimedia.org/wikipedia/commons/3/3b/Headphones_1.jpg', 'Headphones_1.jpg', 'image/jpeg', 178000),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc2', 0, 'https://upload.wikimedia.org/wikipedia/commons/3/3b/Headphones_1.jpg', 'Headphones_1.jpg', 'image/jpeg', 178000),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc3', 0, 'https://upload.wikimedia.org/wikipedia/commons/3/3b/Headphones_1.jpg', 'Headphones_1.jpg', 'image/jpeg', 178000),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc4', 0, 'https://upload.wikimedia.org/wikipedia/commons/3/3b/Headphones_1.jpg', 'Headphones_1.jpg', 'image/jpeg', 178000);

-- Cameras image (CC BY-SA 4.0): File:Kodak Professional digital products photo.jpg
INSERT INTO PRODUCT_IMAGES (PRODUCT_ID, POSITION, URL, FILE_NAME, CONTENT_TYPE, SIZE_BYTES) VALUES
  ('dddddddd-dddd-dddd-dddd-ddddddddddd1', 0, 'https://upload.wikimedia.org/wikipedia/commons/0/0f/Kodak_Professional_digital_products_photo.jpg', 'Kodak_Professional_digital_products_photo.jpg', 'image/jpeg', 1860000),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd2', 0, 'https://upload.wikimedia.org/wikipedia/commons/0/0f/Kodak_Professional_digital_products_photo.jpg', 'Kodak_Professional_digital_products_photo.jpg', 'image/jpeg', 1860000),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd3', 0, 'https://upload.wikimedia.org/wikipedia/commons/0/0f/Kodak_Professional_digital_products_photo.jpg', 'Kodak_Professional_digital_products_photo.jpg', 'image/jpeg', 1860000),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd4', 0, 'https://upload.wikimedia.org/wikipedia/commons/0/0f/Kodak_Professional_digital_products_photo.jpg', 'Kodak_Professional_digital_products_photo.jpg', 'image/jpeg', 1860000);

-- ======= PRODUCT_ATTRIBUTES =======
-- Example attributes per product
INSERT INTO PRODUCT_ATTRIBUTES (PRODUCT_ID, ATTR_KEY, ATTR_VALUE) VALUES
  -- Smartphones
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'brand', 'MolnTech'), ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'storage', '128 GB'), ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'color', 'svart'),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'brand', 'MolnTech'), ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'storage', '256 GB'), ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'color', 'silver'),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'brand', 'Nordic'),   ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'storage', '128 GB'), ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'color', 'blå'),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'brand', 'Nordic'),   ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'storage', '256 GB'), ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'color', 'grön'),

  -- Laptops
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'brand', 'Polar'),    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'ram', '16 GB'), ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'storage', '512 GB SSD'),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'brand', 'Polar'),    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'ram', '16 GB'), ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'storage', '1 TB SSD'),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'brand', 'DevCo'),    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'ram', '32 GB'), ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'gpu', 'RTX 4060'),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'brand', 'Creator'),  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'ram', '32 GB'), ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'storage', '1 TB SSD'),

  -- Headphones
  ('cccccccc-cccc-cccc-cccc-ccccccccccc1', 'type', 'over-ear'), ('cccccccc-cccc-cccc-cccc-ccccccccccc1', 'anc', 'true'),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc2', 'type', 'over-ear'), ('cccccccc-cccc-cccc-cccc-ccccccccccc2', 'anc', 'true'),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc3', 'type', 'in-ear'),   ('cccccccc-cccc-cccc-cccc-ccccccccccc3', 'anc', 'false'),
  ('cccccccc-cccc-cccc-cccc-ccccccccccc4', 'type', 'over-ear'), ('cccccccc-cccc-cccc-cccc-ccccccccccc4', 'open_back', 'true'),

  -- Cameras
  ('dddddddd-dddd-dddd-dddd-ddddddddddd1', 'type', 'compact'), ('dddddddd-dddd-dddd-dddd-ddddddddddd1', 'video', '4K'),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd2', 'type', 'compact'), ('dddddddd-dddd-dddd-dddd-ddddddddddd2', 'af', 'phase detect'),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd3', 'type', 'mirrorless'), ('dddddddd-dddd-dddd-dddd-ddddddddddd3', 'mount', 'X-mount'),
  ('dddddddd-dddd-dddd-dddd-ddddddddddd4', 'type', 'mirrorless'), ('dddddddd-dddd-dddd-dddd-ddddddddddd4', 'lens', '24-70mm');