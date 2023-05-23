package com.softtek.jpa23052023.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="orders")
public class Orden {
    @Id
    @Column(name="order_id")
    private Short id;
    @Column(name="customer_id")
    private String idCliente;
    @Column(name="employee_id")
    private Short idEmpleado;
    @Column(name="order_date")
    private java.sql.Date fecha;
    @Column(name="shipped_date")
    private Date fechaEnvio;
    @Column(name="ship_via")
    private Short shipVia;
    @Column(name="freight")
    private Double transporte;
    @Column(name="ship_name")
    private String shipName;
    @Column(name="ship_address")
    private String shipAddress;
    @Column(name="ship_city")
    private String shipCity;
    @Column(name="ship_region")
    private String shipRegion;
    @Column(name="ship_postal_code")
    private String shipPostalCode;
    @Column(name="ship_country")
    private String shipCountry;
}
