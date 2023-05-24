package com.softtek.jpa23052023;

import com.softtek.jpa23052023.modelo.Cliente;
import com.softtek.jpa23052023.modelo.Producto;
import com.softtek.jpa23052023.servicio.ClienteServicio;
import com.softtek.jpa23052023.servicio.OrdenServicio;
import com.softtek.jpa23052023.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;

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
    cs.obtenerClientes().forEach(System.out::println);

        //Obtener todos los registros y todos los campos de la tabla de productos
        System.out.println("\n Obtener todos los registros y todos los campos de la tabla de productos");
        ps.obtenerProductos().forEach(System.out::println);

        //Obtener una consulta con Productid, productname, supplierid, categoryId, UnistsinStock, UnitPrice
        System.out.println("\n Obtener una consulta con Productid, productname, " +
                "supplierid, categoryId, UnistsinStock, UnitPrice");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre() +
                " " + producto.getProveedor() + " " + producto.getCategoria() + " " + producto.getUnidadesDisponibles() +
                " " + producto.getPrecio()).forEach(System.out::println);

        //Crear una consulta para obtener el OrderId, EmployeeId, Fecha de la orden.
        System.out.println("\n Crear una consulta para obtener el OrderId, EmployeeId, Fecha de la orden.");
        os.obtenerOrdenes().stream().map(orden -> orden.getId() + " " + orden.getIdEmpleado() +
                " " + orden.getFecha()).forEach(System.out::println);

        //Obtener una consulta con Productid, productname y valor del inventario, valor inventrio (UnitsinStock * UnitPrice)
        System.out.println("\n Obtener una consulta con Productid, productname y " +
                "valor del inventario, valor inventario (UnitsinStock * UnitPrice)");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre() +
                " " + (producto.getUnidadesDisponibles() * producto.getPrecio())).forEach(System.out::println);

        //Cuanto vale el punto de reorden
        System.out.println("\n Cuanto vale el punto de reorden");
        ps.obtenerProductos().stream().map(producto -> producto.getNivelReorder() != null && producto.getPrecio() != null
                ? producto.getNivelReorder() * producto.getPrecio()
                : 0).forEach(System.out::println);

        //Mostrar una consulta con Productid, productname y precio, el nombre del producto debe estar en mayuscula
        System.out.println("\n Mostrar una consulta con Productid, productname y precio, " +
                "el nombre del producto debe estar en mayuscula");
        ps.obtenerProductos().stream().map(producto -> producto.getId() + " " + producto.getNombre().toUpperCase() +
                " " + producto.getPrecio()).forEach(System.out::println);

        //Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener unicamente 10 caracteres
        System.out.println("\n Mostrar una consulta con Productid, productname y precio, " +
                "el nombre del producto debe contener unicamente 10 caracteres");
        ps.obtenerProductos().stream().map(producto -> {
            String nombre = producto.getNombre().length() == 10 ? producto.getNombre() : null;
            return nombre != null ? producto.getId() + " " + nombre + " " + producto.getPrecio() : null;
        }).filter(producto -> producto != null).forEach(System.out::println);

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
        ps.obtenerProductos().stream().map(producto -> {
            String nombre = producto.getNombre().length() == 10 ? producto.getNombre().toUpperCase() : null;
            return nombre != null ? producto.getId() + " " + nombre + " " + producto.getPrecio() : null;
        }).filter(producto -> producto != null).forEach(System.out::println);

        //Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais
        // Obtener los clientes cuyo pais sea Spain
        System.out.println("\n Obtener de la tabla de Customers las columnas CustomerId, CompanyName, " +
                "Pais Obtener los clientes cuyo pais sea Spain");
        cs.obtenerClientes().stream().filter(cliente -> cliente.getCountry().equalsIgnoreCase("Spain"))
                .map(cliente -> cliente.getCustomerId() + " " + cliente.getCompanyName() + " " + cliente.getCountry())
                .forEach(System.out::println);

        //Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais,
        // Obtener los clientes cuyo pais comience con la letra U
        System.out.println("\n Obtener de la tabla de Customers las columnas CustomerId, CompanyName, " +
                "Pais, Obtener los clientes cuyo pais comience con la letra U");
        cs.obtenerClientes().stream().filter(cliente -> cliente.getCountry().startsWith("U"))
                .map(cliente -> cliente.getCustomerId() + " " + cliente.getCompanyName() + " " + cliente.getCountry())
                .forEach(System.out::println);

        //Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais,
        // Obtener los clientes cuyo pais comience con la letra U,S,A
        System.out.println("\n Obtener de la tabla de Customers las columnas CustomerId, CompanyName, " +
                "Pais, Obtener los clientes cuyo pais comience con la letra U,S,A");
        cs.obtenerClientes().stream().filter(cliente -> cliente.getCountry().equalsIgnoreCase("USA"))
             .map(cliente -> cliente.getCustomerId() + " " + cliente.getCompanyName() + " " + cliente.getCountry())
             .forEach(System.out::println);

        //Obtener de la tabla de Productos las columnas productid, ProductName, UnitPrice cuyos precios esten entre 50 y 150
        System.out.println("\n Obtener de la tabla de Productos las columnas productid, ProductName, " +
                "UnitPrice cuyos precios esten entre 50 y 150");
        ps.obtenerProductos().stream().filter(producto ->
                        producto.getPrecio() >= 50 && producto.getPrecio() <= 150)
              .map(producto -> producto.getId() + " " + producto.getNombre() + " " + producto.getPrecio())
              .forEach(System.out::println);

        //Obtener de la tabla de Productos las columnas productid, ProductName, UnitPrice, UnitsInStock
        // cuyas existencias esten entre 50 y 100
        System.out.println("\n Obtener de la tabla de Productos las columnas productid, ProductName, " +
                "UnitPrice, UnitsInStock cuyas existencias esten entre 50 y 100");
        ps.obtenerProductos().stream().filter(producto ->
                producto.getUnidadesDisponibles() >= 50 && producto.getUnidadesDisponibles() <= 100)
                .map(producto ->producto.getId() + " " + producto.getNombre() + " " + producto.getPrecio()
                        + " " + producto.getUnidadesDisponibles())
                .forEach(System.out::println);
        //Obtener las columnas OrderId, CustomerId, employeeid de la tabla de ordenes cuyos empleados sean 1, 4, 9
        System.out.println("\n Obtener las columnas OrderId, CustomerId, employeeid de la tabla de ordenes " +
                "cuyos empleados sean 1, 4, 9");
        os.obtenerOrdenes().stream().filter(orden -> orden.getIdEmpleado() == 1 ||
                        orden.getIdEmpleado() == 4 || orden.getIdEmpleado() == 9)
              .map(orden -> orden.getId() + " " + orden.getIdCliente() + " " + orden.getIdEmpleado())
              .forEach(System.out::println);

        //Obtener la información de la tabla de Products, Ordenarlos por Nombre del Producto de forma ascendente
        System.out.println("\n Obtener la información de la tabla de Products, Ordenarlos por Nombre del Producto " +
                "de forma ascendente");
        ps.obtenerProductos().stream().sorted(Comparator.comparing(Producto::getNombre))
               .forEach(System.out::println);

        //Obtener la información de la tabla de Products, Ordenarlos por Categoria de forma ascendente
        // y por precio unitario de forma descendente
        System.out.println("\n Obtener la información de la tabla de Products, Ordenarlos por Categoria de forma ascendente " +
                "y por precio unitario de forma descendente");
        ps.obtenerProductos().stream()
                .sorted(Comparator.comparing(Producto::getCategoria)
                .thenComparing(Comparator.comparing(Producto::getPrecio).reversed()))
                .forEach(System.out::println);

        //Obtener la información de la tabla de Clientes, Customerid, CompanyName, city, country ordenar por pais,
        // ciudad de forma ascendente
        System.out.println("\n Obtener la información de la tabla de Clientes, Customerid, CompanyName, " +
                "city, country ordenar por pais, ciudad de forma ascendente");
        cs.obtenerClientes().stream().sorted(Comparator.comparing(Cliente::getCity)
              .thenComparing(Comparator.comparing(Cliente::getCountry)))
              .forEach(System.out::println);

        //Obtener los productos productid, productname, categoryid, supplierid
        // ordenar por categoryid y supplier únicamente mostrar aquellos cuyo precio esté entre 25 y 200
        System.out.println("\nObtener los productos productid, productname, categoryid, supplierid, ordenar por categoryid y supplier, y mostrar aquellos cuyo precio esté entre 25 y 200");
        ps.obtenerProductos().stream()
                .filter(producto -> producto.getPrecio() >= 25 && producto.getPrecio() <= 200)
                .sorted(Comparator.comparing(Producto::getCategoria)
                        .thenComparing(Producto::getProveedor))
                .map(producto -> producto.getId() + " " + producto.getNombre() + " " +
                        producto.getCategoria() + " " + producto.getProveedor())
                .forEach(System.out::println);

        //Cuantos productos hay en la tabla de productos
        System.out.println("\n Cuantos productos hay en la tabla de productos");
        System.out.println(ps.obtenerProductos().size());

        //de la tabla de productos Sumar las cantidades en existencia
        System.out.println("\n de la tabla de productos Sumar las cantidades en existencia");
        System.out.println(ps.obtenerProductos().stream().mapToInt(Producto::getUnidadesDisponibles).sum());

        //Promedio de los precios de la tabla de productos
        System.out.println("\n Promedio de los precios de la tabla de productos");
        System.out.println(ps.obtenerProductos().stream().mapToDouble(Producto::getPrecio).average().orElse(0));

        //Obtener los datos de productos ordenados descendentemente por precio unitario de la categoría 1
        System.out.println("\nObtener los datos de productos ordenados descendentemente por precio unitario de la categoría 1");
        ps.obtenerProductos().stream()
                .filter(producto -> producto.getCategoria() == 1)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .forEach(System.out::println);

        //Obtener los datos de los clientes(Customers) ordenados descendentemente por nombre(CompanyName)
        // que se encuentren en la ciudad(city) de barcelona, Lisboa
        System.out.println("\nObtener los datos de los clientes(Customers) ordenados descendentemente " +
                "por nombre(CompanyName) que se encuentren en la ciudad(city) de barcelona, Lisboa");
        cs.obtenerClientes().stream()
               .filter(cliente -> cliente.getCity().equalsIgnoreCase("Barcelona") ||
                       cliente.getCity().equalsIgnoreCase("Lisboa"))
               .sorted(Comparator.comparing(Cliente::getCompanyName).reversed())
               .forEach(System.out::println);


    }


    }

