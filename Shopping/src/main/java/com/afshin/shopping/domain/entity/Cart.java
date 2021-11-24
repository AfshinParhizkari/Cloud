package com.afshin.shopping.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 06
 * @Time 4:26 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: RootAggregate=RootEntity
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity//RootAggregate=RootEntity
@Table(name = "cart")
@IdClass(CartPK.class)
public class Cart implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer customerfk;
    private Integer productfk;
    private Integer quantity;
    private BigDecimal price;

    @Id
	@Column(name = "customerfk")
    @NotNull(message = "Customer Code should not be empty")
    public Integer getCustomerfk() {return customerfk;}
    public void setCustomerfk(Integer customerfk) {this.customerfk = customerfk;}

    @Id
	@Column(name = "productfk")
    @NotNull(message = "Product Code should not be empty")
    public Integer getProductfk() {return productfk;}
    public void setProductfk(Integer productfk) {this.productfk = productfk;}

	@Column(name = "quantity")
    @NotNull(message = "quantity should not be empty")
    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

	@Column(name = "price")
    @NotNull(message = "price should not be empty")
    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}

}