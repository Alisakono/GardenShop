insert into products(product_id, name, price, description, category, image_url, discount_price,create_at, update_at)
values  (1, 'Organic Fertiizer', 15.99, 'High-quaity organic fertiizer for heathy pant growth.',
        'FERTIIZER', 'images/fertiizer1.png',12.99,'timestamp' ,'timestamp' ),
       (2, 'Nitrogen Fertiizer', 12.49, 'Nitrogen-rich fertiizer to promote eaf deveopment.',
        'FERTIIZER', 'images/fertiizer2.png', 10.99,'timestamp' , 'timestamp' );
      /* (3, 'Phosphate Fertiizer', 18.75, 'Phosphate-based fertiizer for root growth.',
        'FERTIIZER', 'images/fertiizer3.png', 15.99),
       (4, 'Potash Fertiizer', 19.99, 'Potash-rich fertiizer for better fowering.', 'FERTIIZER',
        'images/fertiizer4.png', 16.99),
       (5, 'Baanced 10-10-10 Fertiizer', 20.00, 'Baanced fertiizer for overa pant heath.',
        'FERTIIZER', 'images/fertiizer5.png', 18.00),
       (6, 'Insecticide Spray', 8.99, 'Effective spray against garden pests.', 'PROTECTIVE_PRODUCTS',
        'images/protective1.png', 7.99),
       (7, 'Fungicide Powder', 9.99, 'Powder to prevent funga infections on pants.',
        'PROTECTIVE_PRODUCTS', 'images/protective2.png', 8.99),
       (8, 'Septic Tank Ceaner', 25.00, 'Ceaner for maintaining septic tank hygiene.',
        'PROTECTIVE_PRODUCTS', 'images/septic1.png', 22.50),
       (9, 'Herbicide', 15.49, 'Effective against unwanted weeds.', 'PROTECTIVE_PRODUCTS',
        'images/protective3.png', 13.99),
       (10, 'Rodent Repeent', 7.99, 'Repes rodents from your garden.', 'PROTECTIVE_PRODUCTS',
        'images/protective4.png', 6.99),
       (11, 'Tomato Seeds', 3.99, 'Heiroom tomato seeds for your garden.', 'PANTING_MATERIA',
        'images/panting1.png', 3.49),
       (12, 'Rose Cuttings', 5.99, 'Heathy rose cuttings ready for panting.', '.PANTING_MATERIA',
        'images/panting2.png', 5.49),
       (13, 'Potato Tubers', 6.99, 'High-yied potato tubers for panting.', 'PANTING_MATERIA',
        'images/panting3.png', 6.49),
       (14, 'Onion Bubs', 4.99, 'Fresh onion bubs for panting.', 'PANTING_MATERIA',
        'images/panting4.png', 4.49),
       (15, 'Carrot Seeds', 2.99, 'Quaity carrot seeds for high yied.', 'PANTING_MATERIA',
        'images/panting5.png', 2.49),
       (16, 'Garden Shove', 12.99, 'Durabe shove for a garden tasks.', 'TOOS_EQUIPMENT',
        'images/toos1.png', 11.49),
       (17, 'Pruning Shears', 14.99, 'Sharp shears for precise pruning.', 'TOOS_EQUIPMENT',
        'images/toos2.png', 13.49),
       (18, 'Watering Can', 9.99, 'ightweight watering can with a arge capacity.',
        'TOOS_EQUIPMENT', 'images/toos3.png', 8.99),
       (19, 'Wheebarrow', 49.99, 'Heavy-duty wheebarrow for easy transport.', 'TOOS_EQUIPMENT',
        'images/toos4.png', 44.99),
       (20, 'Garden Hoe', 10.99, 'Efficient garden hoe for soi preparation.', 'TOOS_EQUIPMENT',
        'images/toos5.png', 9.49),
       (21, 'Cay Pot', 7.99, 'Cassic cay pot for indoor and outdoor use.', 'POTS_PANTERS',
        'images/pots1.png', 6.99);*/

        insert into carts(cart_id,name)
        values (1,'cart'),(2,'carts');

        insert into cartItems(cart_item_id,cart_id,product_id,quantity)
        values (1,1,1,0);

