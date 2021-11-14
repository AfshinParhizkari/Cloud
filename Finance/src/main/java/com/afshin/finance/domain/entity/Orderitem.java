package com.afshin.finance.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 2:57 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Entity Object
 */
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@IdClass(OrderitemPK.class)
public class Orderitem {
    private Integer orderfk;
    private Integer productfk;
    private Integer quantity;
    private BigDecimal price;
    private Order orderByOrderfk;

    public Orderitem(Integer orderfk, Integer productfk, Integer quantity, BigDecimal price) {
        this.orderfk = orderfk;
        this.productfk = productfk;
        this.quantity = quantity;
        this.price = price;
    }

    public Orderitem() {}

    @Id
    @Column(name = "orderfk")
    public Integer getOrderfk() {
        return orderfk;
    }
    public void setOrderfk(Integer orderfk) {
        this.orderfk = orderfk;
    }

    @Id
    @Column(name = "productfk")
    public Integer getProductfk() {
        return productfk;
    }
    public void setProductfk(Integer productfk) {
        this.productfk = productfk;
    }

    @Basic
    @Column(name = "quantity")
    @NotNull(message = "تعداد نمی تواند خالی باشد")
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    @NotNull(message = "مبلغ نمی تواند خالی باشد")
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderitem orderitem = (Orderitem) o;
        return Objects.equals(orderfk, orderitem.orderfk) && Objects.equals(productfk, orderitem.productfk) && Objects.equals(quantity, orderitem.quantity) && Objects.equals(price, orderitem.price);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderfk, productfk, quantity, price);
    }

    @ManyToOne
    @JoinColumn(name = "orderfk", referencedColumnName = "orderpk", nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    public Order getOrderByOrderfk() {
        return orderByOrderfk;
    }
    public void setOrderByOrderfk(Order orderByOrderfk) {
        this.orderByOrderfk = orderByOrderfk;
    }
}
