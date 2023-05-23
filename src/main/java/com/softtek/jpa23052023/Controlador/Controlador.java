package com.softtek.jpa23052023.Controlador;

import com.softtek.jpa23052023.modelo.Producto;
import com.softtek.jpa23052023.servicio.IProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ProductoControlador {
    @Autowired
    private IProductoServicio servicio;

    public List<Producto> getProducts() {return servicio.obtenerProductos();}
}
