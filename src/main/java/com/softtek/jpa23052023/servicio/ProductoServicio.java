package com.softtek.jpa23052023.servicio;

import com.softtek.jpa23052023.dto.PrecioPromedioCategoriaDTO;
import com.softtek.jpa23052023.dto.PromedioProveedorDTO;
import com.softtek.jpa23052023.dto.SumaInventarioDTO;
import com.softtek.jpa23052023.modelo.Producto;
import com.softtek.jpa23052023.repository.IProductoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServicio implements IProductoServicio{
    @Autowired
    private IProductoRepo repo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<Producto> obtenerProductos() {
        return repo.findAll() ;
    }

    public List<PromedioProveedorDTO> promedioProveedor() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(Producto::getProveedor, Collectors.averagingDouble(Producto::getPrecio)))
                .entrySet().stream()
                .map(entry -> {
                    PromedioProveedorDTO dto = new PromedioProveedorDTO();
                    dto.setProveedor(entry.getKey());
                    dto.setPrecioPromedio(entry.getValue());
                    return mapper.map(dto, PromedioProveedorDTO.class);
                })
                .collect(Collectors.toList());
    }

    public List<SumaInventarioDTO> sumaInventario() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(Producto::getProveedor, Collectors.summingInt(Producto::getUnidadesDisponibles)))
                .entrySet().stream()
                .map(entry -> {
                    SumaInventarioDTO dto = new SumaInventarioDTO();
                    dto.setProveedor(entry.getKey());
                    dto.setSumaInventario(entry.getValue());
                    return mapper.map(dto, SumaInventarioDTO.class);
                })
                .collect(Collectors.toList());
    }

    public List<PrecioPromedioCategoriaDTO> PrecioPromedioCategoria() {
        return repo.findAll().stream()
                .filter(producto -> producto.getDiscontinued() != 1) // Filtrar productos no descontinuados
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.averagingDouble(Producto::getPrecio)))
                .entrySet().stream()
                .map(entry -> {
                    PrecioPromedioCategoriaDTO dto = new PrecioPromedioCategoriaDTO();
                    dto.setCategoria(entry.getKey());
                    dto.setPrecioPromedio(entry.getValue());
                    return mapper.map(dto, PrecioPromedioCategoriaDTO.class);
                })
                .collect(Collectors.toList());
    }


}
