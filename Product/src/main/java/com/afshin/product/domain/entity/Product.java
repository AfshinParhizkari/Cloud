package com.afshin.product.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 3:27 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:RootAggregate=RootEntity
 */
@Entity//RootAggregate=RootEntity
public class Product {
    private Integer productpk;
    private String productname;
    private Integer categoryfk;
    private String vendor;
    private Integer quantity;
    private String unit;
    private BigDecimal buyprice;
    private BigDecimal saleprice;
    private Boolean active;
    private String description;
    private Category categoryByCategoryfk;

    @Id
    @Column(name = "productpk")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //specify the generation strategy used for the primary key.
    public Integer getProductpk() {
        return productpk;
    }
    public void setProductpk(Integer productpk) {
        this.productpk = productpk;
    }

    @Basic
    @Column(name = "productname")
    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }

    @Basic
    @Column(name = "categoryfk")
    public Integer getCategoryfk() {
        return categoryfk;
    }
    public void setCategoryfk(Integer categoryfk) {
        this.categoryfk = categoryfk;
    }

    @Basic
    @Column(name = "vendor")
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "buyprice")
    public BigDecimal getBuyprice() {
        return buyprice;
    }
    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    @Basic
    @Column(name = "saleprice")
    public BigDecimal getSaleprice() {
        return saleprice;
    }
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    @Basic
    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productpk, product.productpk) && Objects.equals(productname, product.productname) && Objects.equals(categoryfk, product.categoryfk) && Objects.equals(vendor, product.vendor) && Objects.equals(quantity, product.quantity) && Objects.equals(unit, product.unit) && Objects.equals(buyprice, product.buyprice) && Objects.equals(saleprice, product.saleprice) && Objects.equals(active, product.active) && Objects.equals(description, product.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(productpk, productname, categoryfk, vendor, quantity, unit, buyprice, saleprice, active, description);
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
