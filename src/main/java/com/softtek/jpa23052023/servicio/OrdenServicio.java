package com.softtek.jpa23052023.servicio;

import com.softtek.jpa23052023.modelo.Orden;
import com.softtek.jpa23052023.repository.IOrdenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdenServicio implements IOrdenServicio {
    @Autowired
    private IOrdenRepo ordenRepo;
    @Override
    public List<Orden> obtenerOrdenes() {
        return ordenRepo.findAll();
    }
}
