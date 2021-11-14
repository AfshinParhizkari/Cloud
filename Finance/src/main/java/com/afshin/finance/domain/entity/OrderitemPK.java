package com.afshin.finance.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 2:57 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderitemPK implements Serializable {
    private Integer orderfk;
    private Integer productfk;

    @Column(name = "orderfk")
    @Id
    public Integer getOrderfk() {
        return orderfk;
    }
    public void setOrderfk(Integer orderfk) {
        this.orderfk = orderfk;
    }

    @Column(name = "productfk")
    @Id
    public Integer getProductfk() {
        return productfk;
    }
    public void setProductfk(Integer productfk) {
        this.productfk = productfk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderitemPK that = (OrderitemPK) o;
        return Objects.equals(orderfk, that.orderfk) && Objects.equals(productfk, that.productfk);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderfk, productfk);
    }
}
