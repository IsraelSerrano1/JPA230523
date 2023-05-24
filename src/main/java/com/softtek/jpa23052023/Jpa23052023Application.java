package com.softtek.jpa23052023;

import com.softtek.jpa23052023.modelo.Cliente;
import com.softtek.jpa23052023.modelo.Orden;
import com.softtek.jpa23052023.modelo.Producto;
import com.softtek.jpa23052023.servicio.ClienteServicio;
import com.softtek.jpa23052023.servicio.OrdenServicio;
import com.softtek.jpa23052023.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        ps.obtenerProductos().forEach(System.out::println);
        os.obtenerOrdenes().forEach(System.out::println);
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
                .map(producto -> producto.getId() + " " + producto.getNombre() + " " + producto.getPrecio()
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

        //Obtener los datos de las ordenes, ordenados descendentemente por la fecha de la orden cuyo cliente(CustomerId) sea ALFKI
        System.out.println("\nObtener los datos de las ordenes, ordenados descendentemente " +
                "por la fecha de la orden cuyo cliente(CustomerId) sea ALFKI");
        os.obtenerOrdenes().stream()
                .filter(orden -> Objects.equals(orden.getIdCliente(), "ALFKI"))
                .sorted(Comparator.comparing(Orden::getFecha).reversed())
                .forEach(System.out::println);

        //Obtener los datos de las ordenes ordenados ascendentemente por la fecha de la orden cuyo empleado sea 2 o 4
        System.out.println("\nObtener los datos de las ordenes ordenados ascendentemente " +
                "por la fecha de la orden cuyo empleado sea 2 o 4");
        os.obtenerOrdenes().stream()
                .filter(orden -> orden.getIdEmpleado() == 2 || orden.getIdEmpleado() == 4)
                .sorted(Comparator.comparing(Orden::getFecha))
                .forEach(System.out::println);

        //Obtener los productos cuyo precio están entre 30 y 60 ordenado por nombre
        System.out.println("\nObtener los productos cuyo precio están entre 30 y 60 ordenado por nombre");
        ps.obtenerProductos().stream()
                .filter(producto -> producto.getPrecio() >= 30 && producto.getPrecio() <= 60)
                .sorted(Comparator.comparing(Producto::getNombre))
                .forEach(System.out::println);

        //Numero de productos por categoria
        System.out.println("\nNumero de productos por categoria");
        ps.obtenerProductos().stream()
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.counting()))
                .forEach((categoria, count) ->
                        System.out.println(categoria + " " + count));

        //Obtener el precio promedio por proveedor de la tabla de productos
        System.out.println("\nObtener el precio promedio por proveedor de la tabla de productos");
        ps.obtenerProductos().stream()
                .collect(Collectors.groupingBy(Producto::getProveedor, Collectors.averagingDouble(Producto::getPrecio)))
                .forEach((proveedor, precio) ->
                        System.out.println(proveedor + " " + precio));

        //Obtener la suma de inventario (UnitsInStock) por SupplierID De la tabla de productos (Products)
        System.out.println("\nObtener la suma de inventario (UnitsInStock) por SupplierID De la tabla de productos (Products)");
        ps.obtenerProductos().stream()
                .collect(Collectors.groupingBy(Producto::getProveedor, Collectors.summingDouble(Producto::getUnidadesDisponibles)))
                .forEach((proveedor, suma) ->
                        System.out.println(proveedor + " " + suma));

        //Contar las ordenes por cliente de la tabla de orders
        System.out.println("\nContar las ordenes por cliente de la tabla de orders");
        os.obtenerOrdenes().stream()
                .collect(Collectors.groupingBy(Orden::getIdCliente, Collectors.counting()))
                .forEach((cliente, count) ->
                        System.out.println(cliente + " " + count));

        //Contar las ordenes por empleado de la tabla de ordenes unicamente del empleado 1,3,5,6
        System.out.println("\nContar las ordenes por empleado de la tabla de ordenes unicamente del empleado 1,3,5,6");
        os.obtenerOrdenes().stream()
                .filter(orden -> orden.getIdEmpleado() == 1 || orden.getIdEmpleado() == 3 || orden.getIdEmpleado() == 5 || orden.getIdEmpleado() == 6)
               .collect(Collectors.groupingBy(Orden::getIdEmpleado, Collectors.counting()))
               .forEach((empleado, count) ->
                        System.out.println(empleado + " " + count));

        //Obtener la suma del envío (freight) por cliente
        System.out.println("\nObtener la suma del envío (freight) por cliente");
        os.obtenerOrdenes().stream()
                .filter(orden -> orden.getTransporte() != null)
               .collect(Collectors.groupingBy(Orden::getIdCliente, Collectors.summingDouble(Orden::getTransporte)))
               .forEach((cliente, suma) ->
                        System.out.println(cliente + " " + suma));

        //De la tabla de ordenes únicamente de los registros cuya ShipCity sea Madrid, Sevilla,
        // Barcelona, Lisboa, London Ordenado por el campo de suma del envio
        System.out.println("\nDe la tabla de ordenes únicamente de los registros cuya ShipCity sea Madrid, " +
                "Sevilla, Barcelona, Lisboa, London Ordenado por el campo de suma del envio");
        os.obtenerOrdenes().stream()
              .filter(orden -> orden.getShipCity() != null && (orden.getShipCity().equals("Madrid") || orden.getShipCity().equals("Sevilla") ||
                      orden.getShipCity().equals("Barcelona") || orden.getShipCity().equals("Lisboa") ||
                      orden.getShipCity().equals("London")))
              .collect(Collectors.groupingBy(Orden::getShipCity, Collectors.summingDouble(orden ->
                      Optional.ofNullable(orden.getTransporte()).orElse(0.0))))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));


        //obtener el precio promedio de los productos por categoria sin contar con los productos descontinuados (Discontinued)
        System.out.println("\nObtener el precio promedio de los productos por categoria sin contar con los productos descontinuados (Discontinued)");
        ps.obtenerProductos().stream()
               .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.averagingDouble(Producto::getPrecio)))
               .forEach((categoria, precio) ->
                        System.out.println(categoria + " " + precio));

        //Obtener la cantidad de productos por categoria,  aquellos cuyo precio se encuentre entre 10 y 60 que
        // tengan más de 12 productos
        System.out.println("\nObtener la cantidad de productos por categoria,  aquellos cuyo precio se encuentre entre 10 y 60 que " +
                "tengan más de 12 productos");
        ps.obtenerProductos().stream()
              .filter(producto -> producto.getPrecio() >= 10 && producto.getPrecio() <= 60)
              .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 9)
                .forEach(entry -> System.out.println("Categoría: " + entry.getKey() + ", Cantidad: " + entry.getValue()));

        //OBTENER LA SUMA DE LAS UNIDADES EN EXISTENCIA (UnitsInStock) POR CATEGORIA,
        // Y TOMANDO EN CUENTA UNICAMENTE LOS PRODUCTOS CUYO PROVEEDOR (SupplierID) SEA IGUAL A 17, 19, 16.
        //--cuya categoria tenga menos de 100 unidades ordenado por unidades
        System.out.println("\nOBTENER LA SUMA DE LAS UNIDADES EN EXISTENCIA (UnitsInStock) POR CATEGORIA, " +
                "Y TOMANDO EN CUENTA UNICAMENTE LOS PRODUCTOS CUYO PROVEEDOR (SupplierID) SEA IGUAL A 17, 19, 16.");
        ps.obtenerProductos().stream()
                .filter(producto -> producto.getProveedor() == 17 || producto.getProveedor() == 19 || producto.getProveedor() == 16)
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.summingInt(Producto::getUnidadesDisponibles)))
                .entrySet().stream()
                .filter(entry -> entry.getValue() < 200)
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .forEach(entry -> System.out.println("Categoría: " + entry.getKey() + ", Suma de unidades: " + entry.getValue()));
    }
}

