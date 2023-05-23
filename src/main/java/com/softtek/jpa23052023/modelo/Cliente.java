package com.softtek.jpa23052023.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customers")
public class Cliente {
    @Id
    @Column(name="customer_id")
    private String customerId;
    @Column(name="company_name")
    private String companyName;
    @Column(name="contact_name")
    private String contactName;
    @Column(name="contact_title")
    private String contactTitle;
    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="region")
    private String region;
    @Column(name="postal_code")
    private String postalCode;
    @Column(name="country")
    private String country;
    @Column(name="phone")
    private String phone;
    @Column(name="fax")
    private String fax;



}
