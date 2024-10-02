package dbmanagermain;

import pe.edu.pucp.comerzia.config.DBManager;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.AlmacenBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;

public class DBManagerMain {

    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        System.out.println(dbManager);
        dbManager = DBManager.getInstance();
        System.out.println(dbManager);
        //ProductoBOTest();
        //ProductoBOTest();
        AlmacenBOTest();
    }

    private static void ProductoBOTest() {
        ProductoBO productoBO = new ProductoBO();

        // Insertar un nuevo producto
        System.out.println("==> Insertando un nuevo producto:");
        Integer idInsertado = productoBO.insertar("Laptop Asus", 2500.00, 8);
        
        if (idInsertado != null) {
            System.out.println("Producto insertado ");
        } else {
            System.out.println("Error al insertar el producto.");
        }
       
       Integer idInsertado1 = productoBO.insertar("Mouse Logitech", 50.00, 15);
        if (idInsertado1 != null) {
            System.out.println("Producto insertado ");
        } else {
            System.out.println("Error al insertar el producto.");
        }

       
         // Listar todos los productos
        System.out.println("\n==> Listando todos los productos después de la inserción:");
        ArrayList<Producto> productos = productoBO.listarTodos();
        for (Producto p : productos) {
            System.out.println("ID: " + p.getIdProducto() + ", Nombre: " + p.getNombreProducto() + ", Precio: " + p.getPrecio() + ", Stock Mínimo: " + p.getStockMinimo());
        }

        // Modificar el producto recién insertado
        System.out.println("\n==> Modificando el producto con ID: " + idInsertado);
        Integer idModificado = productoBO.modificar(idInsertado, "Laptop Dell", 3000.00, 10);
        if (idModificado != null) {
            System.out.println("Producto modificado con éxito.");
        } else {
            System.out.println("Error al modificar el producto.");
        }

        // Listar todos los productos después de la modificación
        System.out.println("\n==> Listando todos los productos después de la modificación:");
        productos = productoBO.listarTodos();
        for (Producto p : productos) {
            System.out.println("ID: " + p.getIdProducto() + ", Nombre: " + p.getNombreProducto() + ", Precio: " + p.getPrecio() + ", Stock Mínimo: " + p.getStockMinimo());
        }

        // Eliminar el producto
        System.out.println("\n==> Eliminando el producto con ID: " + idInsertado);
        Producto productoEliminar = new Producto(idInsertado, "Laptop Gaming", 3000.00, 5);
        Integer idEliminado = productoBO.eliminar(productoEliminar);
        if (idEliminado != null) {
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el producto.");
        }

        // Listar todos los productos después de la eliminación
        System.out.println("\n==> Listando todos los productos después de la eliminación:");
        productos = productoBO.listarTodos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos en la lista.");
        } else {
            for (Producto p : productos) {
                System.out.println("ID: " + p.getIdProducto() + ", Nombre: " + p.getNombreProducto() + ", Precio: " + p.getPrecio() + ", Stock Mínimo: " + p.getStockMinimo());
            }
        }
    }
    private static void AlmacenBOTest() {
        AlmacenBO almacenBO = new AlmacenBO();

        // Insertar un nuevo almacen
        System.out.println("==> Insertando un nuevo almacen:");
        Integer idInsertado = almacenBO.insertar("AlmacenA", "EstadoA", "DescripcionA");
        
        if (idInsertado != null) {
            System.out.println("Almacen insertado ");
        } else {
            System.out.println("Error al insertar el almacen.");
        }
       
       Integer idInsertado1 = almacenBO.insertar("AlmacenB", "EstadoB", "DescripcionB");
        if (idInsertado1 != null) {
            System.out.println("Almacen insertado ");
        } else {
            System.out.println("Error al insertar el almacen.");
        }

       
         // Listar todos los almacenes
        System.out.println("\n==> Listando todos los almacenes después de la inserción:");
        ArrayList<Almacen> almacenes = almacenBO.listarTodos();
        for (Almacen a : almacenes) {
            System.out.println("ID: " + a.getIdAlmacen() + ", Nombre: " + a.getNombre() + ", Estado: " + a.getEstado() + ", Descripcion: " + a.getDescripcion());
        }

        // Modificar el almacen recién insertado
        System.out.println("\n==> Modificando el almacen con ID: " + idInsertado);
        Integer idModificado = almacenBO.modificar(idInsertado, "AlmacenAA", "EstadoA", "DescripcionA");
        if (idModificado != null) {
            System.out.println("Almacen modificado con éxito.");
        } else {
            System.out.println("Error al modificar el almacen.");
        }

        // Listar todos los almacenes después de la modificación
        System.out.println("\n==> Listando todos los almacenes después de la modificación:");
        almacenes = almacenBO.listarTodos();
        for (Almacen a : almacenes) {
            System.out.println("ID: " + a.getIdAlmacen() + ", Nombre: " + a.getNombre() + ", Estado: " + a.getEstado() + ", Descripcion: " + a.getDescripcion());
        }

        // Eliminar el almacen
        System.out.println("\n==> Eliminando el producto con ID: " + idInsertado);
        Almacen almacenEliminar = new Almacen(idInsertado, "AlmacenAA", "EstadoA", "DescripcionA");
        Integer idEliminado = almacenBO.eliminar(almacenEliminar);
        if (idEliminado != null) {
            System.out.println("Almacen eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el almacen.");
        }

        // Listar todos los almacen después de la eliminación
        System.out.println("\n==> Listando todos los productos después de la eliminación:");
        almacenes = almacenBO.listarTodos();
        if (almacenes.isEmpty()) {
            System.out.println("No hay productos en la lista.");
        } else {
            for (Almacen a : almacenes) {
                System.out.println("ID: " + a.getIdAlmacen() + ", Nombre: " + a.getNombre() + ", Estado: " + a.getEstado() + ", Descripcion: " + a.getDescripcion());
            }
        }
    }
}