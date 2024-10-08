-- liquibase formatted sql
-- changeset Konovalov:002

insert into users( name,email,phone_number,password_hash,role)
values  ('Alise Smith', 'alise@example.com',1234567890,'$2a$10$iNZkAmiHLhedzjkQxz6onuZ96QQIA4ixdleFOnUZVBhpNFDLYo45.','ADMIN'),
        ('Bob Johnson', 'bob@example.com', 2345678901,'$2a$10$iNZkAmiHLhedzjkQxz6onuZ96QQIA4ixdleFOnUZVBhpNFDLYo45.', 'MANAGER'),
        ('Charlie Davis', 'charlie@example.com', 3456789012,'$2a$10$iNZkAmiHLhedzjkQxz6onuZ96QQIA4ixdleFOnUZVBhpNFDLYo45.', 'USER'),
        ('Diana King', 'diana@example.com', 4567890123,'$2a$10$iNZkAmiHLhedzjkQxz6onuZ96QQIA4ixdleFOnUZVBhpNFDLYo45.', 'USER'),
        ('Ethan Brown', 'ethan@example.com', 5678901234,'$2a$10$iNZkAmiHLhedzjkQxz6onuZ96QQIA4ixdleFOnUZVBhpNFDLYo45.', 'MANAGER');

insert into categories(category_id,category_name)
values ('1','Fertilizer'),
       ('2','Protective products and septic tanks'),
       ('3','Planting material'),
       ('4','Tools and equipment'),
       ('5','Pots and planters');

insert into products( name, price, description, image_url, discount_price,created_at, updated_at, category_id)
values  ('Organic Fertilizer', 15.99, 'High-quantity organic fertilizer for heath pant growth.',
         'images/fertilizer1.png',12.99,NOW() ,NOW(),'1'),
       ( 'Nitrogen Fertilizer', 12.49, 'Nitrogen-rich fertilizer to promote eaf development.',
         'images/fertilizer2.png', 10.99,NOW() , NOW(), '1'),
       ('Phosphate Fertilizer', 18.75, 'Phosphate-based fertilizer for root growth.',
         'images/fertilizer3.png', 15.99, NOW(),NOW(),'1'),
      ('Potash Fertilizer', 19.99, 'Potash-rich fertilizer for better powering.',
        'images/fertilizer4.png', 16.99, NOW(),NOW(),'1'),
       ('Balanced 10-10-10 Fertilizer', 20.00, 'Balanced fertilizer for over pant heath.',
         'images/fertilizer5.png', 18.00, NOW(),NOW(),'1'),
       ('Insecticide Spray', 8.99, 'Effective spray against garden pests.',
        'images/protective1.png', 7.99,NOW(),NOW(),'2'),
       ( 'Fungicide Powder', 9.99, 'Powder to prevent fungal infections on pants.',
         'images/protective2.png', 8.99,NOW(),NOW(),'2'),
       ('Septic Tank Caner', 25.00, 'Caner for maintaining septic tank hygiene.',
         'images/septic1.png', 22.50,NOW(),NOW(),'2'),
       ('Herbicide', 15.49, 'Effective against unwanted weeds.',
        'images/protective3.png', 13.99,NOW(),NOW(),'2'),
       ('Rodent Repent', 7.99, 'Repos rodents from your garden.',
        'images/protective4.png', 6.99,NOW(),NOW(),'2'),
       ( 'Tomato Seeds', 3.99, 'Heirloom tomato seeds for your garden.',
        'images/panting1.png', 3.49,NOW(),NOW(),'3'),
       ('Rose Cuttings', 5.99, 'Heath rose cuttings ready for panting.',
        'images/panting2.png', 5.49,NOW(),NOW(),'3'),
       ( 'Potato Tubers', 6.99, 'High-yield potato tubers for panting.',
        'images/panting3.png', 6.49,NOW(),NOW(),'3'),
       ( 'Onion Bubs', 4.99, 'Fresh onion bubs for panting.',
        'images/panting4.png', 4.49,NOW(),NOW(),'3'),
       ( 'Carrot Seeds', 2.99, 'Quality carrot seeds for high yield.',
        'images/panting5.png', 2.49,NOW(),NOW(),'3'),
       ('Garden Shove', 12.99, 'Durable shove for a garden tasks.',
        'images/toos1.png', 11.49,NOW(),NOW(),'4'),
       ('Pruning Shears', 14.99, 'Sharp shears for precise pruning.',
        'images/toos2.png', 13.49,NOW(),NOW(),'4'),
       ('Watering Can', 9.99, 'lightweight watering can with a are capacity.',
         'images/toos3.png', 8.99,NOW(),NOW(),'4'),
       ( 'Wheelbarrow', 49.99, 'Heavy-duty wheelbarrow for easy transport.',
        'images/toos4.png', 44.99,NOW(),NOW(),'4'),
       ('Garden Hoe', 10.99, 'Efficient garden hoe for soi preparation.',
        'images/toos5.png', 9.49,NOW(),NOW(),'4'),
       ('Cay Pot', 7.99, 'Classic cay pot for indoor and outdoor use.',
        'images/pots1.png', 6.99,NOW(),NOW(),'5');

insert into carts(email)
values ('alise@example.com'),
        ('bob@example.com'),
        ('charlie@example.com'),
        ('diana@example.com'),
        ('ethan@example.com');

insert into cart_items(cart_id,product_id,quantity)
values ('1','1',2),
       ('2','3',4),
       ('3','2',4),
       ('4','4',4),
       ('5','3',4);

insert into orders(email, created_at, delivery_address, contact_phone, delivery_method, status, updated_at)
VALUES ('alise@example.com',NOW(),'Berlin Mapper Str.20',1234567890,'courier','AWAITING_PAYMENT',NOW());

insert into order_items(order_id, product_id, quantity, price_at_purchase)
VALUES ('1','2',5,12.99);


insert into favorites(product_id,email)
VALUES (1,'alise@example.com')