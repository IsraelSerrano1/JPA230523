package com.softtek.jpa23052023.repository;

import com.softtek.jpa23052023.modelo.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenRepo extends JpaRepository<Orden, Short> {
}
