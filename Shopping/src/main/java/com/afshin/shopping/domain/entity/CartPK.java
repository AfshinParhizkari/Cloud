package com.afshin.shopping.domain.entity;
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

public class CartPK implements Serializable {
    private Integer customerfk;
    private Integer productfk;

    @Column(name = "cartpk")
    @Id
    public Integer getCustomerfk() {
        return customerfk;
    }
    public void setCustomerfk(Integer customerfk) {
        this.customerfk = customerfk;
    }

    @Column(name = "productfk")
    @Id
    public Integer getProductfk() {
        return productfk;
    }
    public void setProductfk(Integer productfk) {
        this.productfk = productfk;
    }
}
