package com.softtek.jpa23052023.repository;

import com.softtek.jpa23052023.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepo extends JpaRepository<Producto, Short> {
}
