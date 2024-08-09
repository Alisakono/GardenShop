package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.security.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity

public class Product {
private Long id;
private String name;
private BigDecimal price;
private Text description;
private Category category;
private String imageURL;
private BigDecimal discountPrice;
private DateTimeFormat createdAt;
private Timestamp updatedAt;
}
