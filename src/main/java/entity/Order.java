package entity;

import org.springframework.core.GenericTypeResolver;

import javax.annotation.processing.Generated;
import java.sql.Timestamp;

public class Order {

    private Long id;
    private User user;
    private Timestamp createdAT;
    private String deliveryAddress;
    private String contactPhone;
    private String deliveryMethod;
    private Enum status;
    private Timestamp updatedAT;
}
