package com.dhaya.giftportal.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="orders_data")
public class Orders {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;
    
    private String payment;
    private Date orderDate;
    private float deliveryDate;

    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private  Product product;

    @Transient
    private Long userId;

    @Transient
    private Long productId;


}
