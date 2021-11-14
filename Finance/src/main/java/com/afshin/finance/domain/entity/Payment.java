package com.afshin.finance.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 2:57 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Root Aggregate
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity//Root Entity
public class Payment {
    private Integer paymentpk;
    private Integer orderfk;
    private Timestamp paymentdate;
    private BigDecimal amount;
    private String transaction;
    private Order orderByOrderfk;

    public Payment(Integer orderfk, BigDecimal amount, String transaction) {
        this.orderfk = orderfk;
        this.amount = amount;
        this.transaction = transaction;
    }

    public Payment() {}

    @Id
    @Column(name = "paymentpk")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //specify the generation strategy used for the primary key.
    public Integer getPaymentpk() {
        return paymentpk;
    }
    public void setPaymentpk(Integer paymentpk) {
        this.paymentpk = paymentpk;
    }

    @Basic
    @Column(name = "orderfk")
    @NotNull(message = "کد سفارش نمی تواند خالی باشد")
    public Integer getOrderfk() {
        return orderfk;
    }
    public void setOrderfk(Integer orderfk) {
        this.orderfk = orderfk;
    }

    @Basic
    @Column(name = "paymentdate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getPaymentdate() {
        return paymentdate;
    }
    public void setPaymentdate(Timestamp paymentdate) {
        this.paymentdate = paymentdate;
    }

    @Basic
    @Column(name = "amount")
    @NotNull(message = "مبلغ نمی تواند خالی باشد")
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "transaction")
    public String getTransaction() {
        return transaction;
    }
    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentpk, payment.paymentpk) && Objects.equals(orderfk, payment.orderfk) && Objects.equals(paymentdate, payment.paymentdate) && Objects.equals(amount, payment.amount) && Objects.equals(transaction, payment.transaction);
    }
    @Override
    public int hashCode() {
        return Objects.hash(paymentpk, orderfk, paymentdate, amount, transaction);
    }

    @ManyToOne
    @JoinColumn(name = "orderfk", referencedColumnName = "orderpk", nullable = false,insertable = false,updatable = false)
    public Order getOrderByOrderfk() {
        return orderByOrderfk;
    }
    public void setOrderByOrderfk(Order orderByOrderfk) {
        this.orderByOrderfk = orderByOrderfk;
    }
}
