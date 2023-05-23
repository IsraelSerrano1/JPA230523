package com.softtek.jpa23052023.Controlador;

import com.softtek.jpa23052023.modelo.Producto;
import com.softtek.jpa23052023.servicio.IProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Controlador {
    @Autowired
    private IProductoServicio servicioProducto;


    public List<Producto> getProducts() {return servicioProducto.obtenerProductos();}

}
