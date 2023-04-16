package com.example.stockmanagement.productservice.repository.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product",schema = "stock_management")
public class Product {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private double price;

    @Column(name="product_updated_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productUpdatedDate;

    @Column(name="product_created_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productCreatedDate;

    @Column(name="is_deleted")
    private boolean deleted;
}
