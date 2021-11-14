package com.afshin.shopping.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 3:27 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:Entity
 */
import javax.persistence.*;
import java.math.BigDecimal;

public class Product {
    private Integer productpk;
    private String productname;
    private Integer categoryfk;
    private String vendor;
    private Integer quantity;
    private String unit;
    private BigDecimal saleprice;
    private String description;
    private Category categoryByCategoryfk;

    @Id
    public Integer getProductpk() {
        return productpk;
    }
    public void setProductpk(Integer productpk) {
        this.productpk = productpk;
    }

    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getCategoryfk() {
        return categoryfk;
    }
    public void setCategoryfk(Integer categoryfk) {
        this.categoryfk = categoryfk;
    }

    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getSaleprice() {
        return saleprice;
    }
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "categoryfk", referencedColumnName = "categorypk",insertable = false,updatable = false)
    public Category getCategoryByCategoryfk() {
        return categoryByCategoryfk;
    }
    public void setCategoryByCategoryfk(Category categoryByCategoryfk) {
        this.categoryByCategoryfk = categoryByCategoryfk;
    }
}
