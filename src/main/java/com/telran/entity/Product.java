package com.telran.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product {
    @Id
    private Integer id;
private String name;
private BigDecimal price;
private String description;
private String category;
private String imageURL;
private BigDecimal discountPrice;
private Timestamp createdAt;
private Timestamp updatedAt;

    //public static List<Product> products = new ArrayList<>(List.of(
            /*new Product(1L, "Organic Fertilizer", new BigDecimal("15.99"), "High-quality organic fertilizer for healthy plant growth.", "FERTILIZER", "images/fertilizer1.png", new BigDecimal("12.99")),
            new Product(2L, "Nitrogen Fertilizer", new BigDecimal("12.49"), "Nitrogen-rich fertilizer to promote leaf development.", "FERTILIZER", "images/fertilizer2.png", new BigDecimal("10.99")),
            new Product(3L, "Phosphate Fertilizer", new BigDecimal("18.75"), "Phosphate-based fertilizer for root growth.", "FERTILIZER", "images/fertilizer3.png", new BigDecimal("15.99")),
            new Product(4L, "Potash Fertilizer", new BigDecimal("19.99"), "Potash-rich fertilizer for better flowering.", "FERTILIZER", "images/fertilizer4.png", new BigDecimal("16.99")),
            new Product(5L, "Balanced 10-10-10 Fertilizer", new BigDecimal("20.00"), "Balanced fertilizer for overall plant health.", "FERTILIZER", "images/fertilizer5.png", new BigDecimal("18.00")),
            new Product(6L, "Insecticide Spray", new BigDecimal("8.99"), "Effective spray against garden pests.", "PROTECTIVE_PRODUCTS", "images/protective1.png", new BigDecimal("7.99")),
            new Product(7L, "Fungicide Powder", new BigDecimal("9.99"), "Powder to prevent fungal infections on plants.", "PROTECTIVE_PRODUCTS", "images/protective2.png", new BigDecimal("8.99")),
            new Product(8L, "Septic Tank Cleaner", new BigDecimal("25.00"), "Cleaner for maintaining septic tank hygiene.","PROTECTIVE_PRODUCTS", "images/septic1.png", new BigDecimal("22.50")),
            new Product(9L, "Herbicide", new BigDecimal("15.49"), "Effective against unwanted weeds.", "PROTECTIVE_PRODUCTS", "images/protective3.png", new BigDecimal("13.99")),
            new Product(10L, "Rodent Repellent", new BigDecimal("7.99"), "Repels rodents from your garden.", "PROTECTIVE_PRODUCTS", "images/protective4.png", new BigDecimal("6.99")),
            new Product(11L, "Tomato Seeds", new BigDecimal("3.99"), "Heirloom tomato seeds for your garden.", "PLANTING_MATERIAL", "images/planting1.png", new BigDecimal("3.49")),
            new Product(12L, "Rose Cuttings", new BigDecimal("5.99"), "Healthy rose cuttings ready for planting.", ".PLANTING_MATERIAL", "images/planting2.png", new BigDecimal("5.49")),
            new Product(13L, "Potato Tubers", new BigDecimal("6.99"), "High-yield potato tubers for planting.", "PLANTING_MATERIAL", "images/planting3.png", new BigDecimal("6.49")),
            new Product(14L, "Onion Bulbs", new BigDecimal("4.99"), "Fresh onion bulbs for planting.","PLANTING_MATERIAL", "images/planting4.png", new BigDecimal("4.49")),
            new Product(15L, "Carrot Seeds", new BigDecimal("2.99"), "Quality carrot seeds for high yield.", "PLANTING_MATERIAL", "images/planting5.png", new BigDecimal("2.49")),
            new Product(16L, "Garden Shovel", new BigDecimal("12.99"), "Durable shovel for all garden tasks.", "TOOLS_EQUIPMENT", "images/tools1.png", new BigDecimal("11.49")),
            new Product(17L, "Pruning Shears", new BigDecimal("14.99"), "Sharp shears for precise pruning.", "TOOLS_EQUIPMENT", "images/tools2.png", new BigDecimal("13.49")),
            new Product(18L, "Watering Can", new BigDecimal("9.99"), "Lightweight watering can with a large capacity.", "TOOLS_EQUIPMENT", "images/tools3.png", new BigDecimal("8.99")),
            new Product(19L, "Wheelbarrow", new BigDecimal("49.99"), "Heavy-duty wheelbarrow for easy transport.", "TOOLS_EQUIPMENT", "images/tools4.png", new BigDecimal("44.99")),
            new Product(20L, "Garden Hoe", new BigDecimal("10.99"), "Efficient garden hoe for soil preparation.", "TOOLS_EQUIPMENT", "images/tools5.png", new BigDecimal("9.49")),
            new Product(21L, "Clay Pot", new BigDecimal("7.99"), "Classic clay pot for indoor and outdoor use.", "POTS_PLANTERS", "images/pots1.png", new BigDecimal("6.99"))*/

    //));

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }


}
