package com.softtek.jpa23052023;

import com.softtek.jpa23052023.Controlador.Controlador;
import com.softtek.jpa23052023.modelo.Producto;
import com.softtek.jpa23052023.servicio.ClienteServicio;
import com.softtek.jpa23052023.servicio.OrdenServicio;
import com.softtek.jpa23052023.servicio.ProductoServicio;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jpa23052023Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Jpa23052023Application.class, args);
    }
    @Autowired
    ProductoServicio ps;
    @Autowired
    OrdenServicio os;
    @Autowired
    ClienteServicio cs;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("SPRING Boot Application Running");


        //Obtener todos los registros y todos los campos de la tabla de productos
        System.out.println("\n Obtener todos los registros y todos los campos de la tabla de productos");
        ps.obtenerProductos().forEach(System.out::println);

        //Obtener una consulta con Productid, productname, supplierid, categoryId, UnistsinStock, UnitPrice
        System.out.println("\n Obtener una consulta con Productid, productname, supplierid, categoryId, UnistsinStock, UnitPrice");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre() +
                " " + producto.getProveedor() + " " + producto.getCategoria() + " " + producto.getUnidadesDisponibles() +
                " " + producto.getPrecio()).forEach(System.out::println);

        //Crear una consulta para obtener el OrderId, EmployeeId, Fecha de la orden.
        System.out.println("\n Crear una consulta para obtener el OrderId, EmployeeId, Fecha de la orden.");
        os.obtenerOrdenes().stream().map(orden -> orden.getId() + " " + orden.getIdEmpleado() +
                " " + orden.getFecha()).forEach(System.out::println);

        //Obtener una consulta con Productid, productname y valor del inventario, valor inventrio (UnitsinStock * UnitPrice)
        System.out.println("\n Obtener una consulta con Productid, productname y valor del inventario, valor inventario (UnitsinStock * UnitPrice)");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre() +
                " " + (producto.getUnidadesDisponibles() * producto.getPrecio())).forEach(System.out::println);

        //Cuanto vale el punto de reorden
//        System.out.println("\n Cuanto vale el punto de reorden");
//        ps.obtenerProductos().stream().map(producto -> producto.getNivelReorder() * producto.getPrecio()).forEach(System.out::println);

        //Mostrar una consulta con Productid, productname y precio, el nombre del producto debe estar en mayuscula
        System.out.println("\n Mostrar una consulta con Productid, productname y precio, el nombre del producto debe estar en mayuscula");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre().toUpperCase() +
                " " + producto.getPrecio()).forEach(System.out::println);

        //Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener unicamente 10 caracteres
        System.out.println("\n Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener unicamente 10 caracteres");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre().substring(0, 10) +
                " " + producto.getPrecio()).forEach(System.out::println);

        //Obtener una consulta que muestre la longitud del nombre del producto
        System.out.println("\n Obtener una consulta que muestre la longitud del nombre del producto");
        ps.obtenerProductos().stream().map(producto -> producto.getNombre().length()).forEach(System.out::println);

        //Obtener una consulta de la tabla de productos que muestre el nombre en minúscula
        System.out.println("\n Obtener una consulta de la tabla de productos que muestre el nombre en minúscula");
        ps.obtenerProductos().stream().map(producto -> producto.getNombre().toLowerCase()).forEach(System.out::println);

        //Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener unicamente
        // 10 caracteres y se deben mostrar en mayúscula
        System.out.println("\n Mostrar una consulta con Productid, productname y precio, el nombre del producto " +
                "debe contener unicamente 10 caracteres y se deben mostrar en mayúscula");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre().substring(0, 10).toUpperCase() +
                " " + producto.getPrecio()).forEach(System.out::println);

        //Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais Obtener los clientes cuyo pais sea Spain
//        System.out.println("\n Obtener de la tabla de Customers las columnas CustomerId, CompanyName, " +
//                "Pais Obtener los clientes cuyo pais sea Spain");
//        cs.obtenerClientes().stream().map(cliente -> cliente.get() + " " + cliente.getNombre() +
//                " " + cliente..forEach(System.out::println);



    }


    }

