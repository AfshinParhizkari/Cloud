package com.afshin.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 3:27 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:Entity Object
 */
@Entity//Entity Object
public class Category {
    private Integer categorypk;
    private String categoryname;
    private String categorydescription;
    private Collection<Product> productsByCategorypk;

    @Id
    @Column(name = "categorypk")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //specify the generation strategy used for the primary key.
    public Integer getCategorypk() {
        return categorypk;
    }
    public void setCategorypk(Integer categorypk) {
        this.categorypk = categorypk;
    }

    @Basic
    @Column(name = "categoryname")
    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @Basic
    @Column(name = "categorydescription")
    public String getCategorydescription() {
        return categorydescription;
    }
    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categorypk, category.categorypk) && Objects.equals(categoryname, category.categoryname) && Objects.equals(categorydescription, category.categorydescription);
    }
    @Override
    public int hashCode() {
        return Objects.hash(categorypk, categoryname, categorydescription);
    }

    @OneToMany(mappedBy = "categoryByCategoryfk")
    @JsonIgnore
    public Collection<Product> getProductsByCategorypk() {
        return productsByCategorypk;
    }
    public void setProductsByCategorypk(Collection<Product> productsByCategorypk) {
        this.productsByCategorypk = productsByCategorypk;
    }
}
