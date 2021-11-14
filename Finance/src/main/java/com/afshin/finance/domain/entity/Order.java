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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    private Integer orderpk;
    private Integer customerfk;
    private Timestamp orderdate;
    private Timestamp shippdate;
    private String state;
    private String note;
    private Collection<Orderitem> orderitemsByOrderpk;
    private Collection<Payment> paymentsByOrderpk;

    public Order(Integer customerfk, Timestamp shippdate, String state) {
        this.customerfk = customerfk;
        this.shippdate = shippdate;
        this.state = state;
    }
    public Order() {}

    @Id
    @Column(name = "orderpk")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //specify the generation strategy used for the primary key.
    public Integer getOrderpk() {
        return orderpk;
    }
    public void setOrderpk(Integer orderpk) {
        this.orderpk = orderpk;
    }

    @Basic
    @Column(name = "customerfk")
    @NotNull(message = "کد مشتری نمی تواند خالی باشد")
    public Integer getCustomerfk() {
        return customerfk;
    }
    public void setCustomerfk(Integer customerfk) {
        this.customerfk = customerfk;
    }

    @Basic
    @Column(name = "orderdate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getOrderdate() {
        return orderdate;
    }
    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
    }

    @Basic
    @Column(name = "shippdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getShippdate() {
        return shippdate;
    }
    public void setShippdate(Timestamp shippdate) {
        this.shippdate = shippdate;
    }

    @Basic
    @Column(name = "state")
    @NotEmpty(message = "وضعیت سفارش نمی تواند خالی باشد")
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderpk, order.orderpk) && Objects.equals(customerfk, order.customerfk) && Objects.equals(orderdate, order.orderdate) && Objects.equals(shippdate, order.shippdate) && Objects.equals(state, order.state) && Objects.equals(note, order.note);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderpk, customerfk, orderdate, shippdate, state, note);
    }

    @OneToMany(mappedBy = "orderByOrderfk")
    public Collection<Orderitem> getOrderitemsByOrderpk() {
        return orderitemsByOrderpk;
    }
    public void setOrderitemsByOrderpk(Collection<Orderitem> orderitemsByOrderpk) {
        this.orderitemsByOrderpk = orderitemsByOrderpk;
    }

    @OneToMany(mappedBy = "orderByOrderfk")
    @JsonIgnore
    public Collection<Payment> getPaymentsByOrderpk() {
        return paymentsByOrderpk;
    }
    public void setPaymentsByOrderpk(Collection<Payment> paymentsByOrderpk) {
        this.paymentsByOrderpk = paymentsByOrderpk;
    }
}
