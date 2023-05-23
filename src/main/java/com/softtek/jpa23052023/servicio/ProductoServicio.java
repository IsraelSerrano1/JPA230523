package com.softtek.jpa23052023.servicio;

import com.softtek.jpa23052023.modelo.Producto;
import com.softtek.jpa23052023.repository.IProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServicio implements IProductoServicio{
    @Autowired
    private IProductoRepo repo;
    @Override
    public List<Producto> obtenerProductos() {
        return repo.findAll() ;
    }
}
