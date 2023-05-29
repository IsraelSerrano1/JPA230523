package com.softtek.jpa23052023.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrecioPromedioCategoriaDTO {

    private short categoria;
    private double precioPromedio;
}
