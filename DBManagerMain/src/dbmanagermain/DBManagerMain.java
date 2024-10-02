package dbmanagermain;

import pe.edu.pucp.comerzia.config.DBManager;
import java.util.ArrayList;
import java.sql.Date;

// Importar las clases BO correspondientes
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.AlmacenBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoAlmacenadoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.AdministradorBO;

// Importar otras clases BO según sea necesario
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.PersonaBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.EmpleadoBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.TrabajadorDeAlmacenBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.VendedorBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;

import pe.edu.pucp.comerzia.RelacionesComerciales.bo.EmpresaBO;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.ClienteBO;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.ProveedorBO;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.VisitaBO;
import pe.edu.pucp.comerzia.gestioncomercial.bo.DocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.bo.LineaDocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;

public class DBManagerMain {

    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        System.out.println("Conexión DBManager: " + dbManager);

        // Ejecutar funciones de prueba en orden debido a dependencias
        Integer idPersona1 = PersonaTest();
        Integer idPersona2 = PersonaTest();

        Integer idEmpresa1 = EmpresaTest();
        Integer idEmpresa2 = EmpresaTest();

        Integer idEmpleado1 = EmpleadoTest(idPersona1);
        Integer idEmpleado2 = EmpleadoTest(idPersona2);

        Integer idAlmacen1 = AlmacenTest();
        Integer idAlmacen2 = AlmacenTest();

        AdministradorTest(idEmpleado1, idAlmacen1);
        TrabajadorDeAlmacenTest(idEmpleado2, idAlmacen2);

        Integer idVendedor1 = VendedorTest(idEmpleado1);
        Integer idVendedor2 = VendedorTest(idEmpleado2);

        ProveedorTest(idEmpresa2);
        ClienteTest(idEmpresa1);

        ProductoTest();
        ProductoAlmacenadoTest();

        DocumentoTest();
        LineaDocumentoTest();

        VisitaTest(idVendedor1);

        // Listar todos los registros de una tabla como ejemplo
        listarPersonas();

        // Obtener los objetos para pasarlos a las funciones
        // Persona persona1 = obtenerPersonaPorId(idPersona1);
        // modificarPersona(persona1);
        // persona1 = obtenerPersonaPorId(idPersona1);
        // eliminarPersona(persona1);
        // persona1 = obtenerPersonaPorId(idPersona1);
        // if (persona1 == null) {
        //     System.out.println("La persona ha sido eliminada correctamente.");
        // }
        // // Repite para otras entidades
        // Empleado empleado1 = obtenerEmpleadoPorId(idEmpleado1);
        // modificarEmpleado(empleado1);
        // empleado1 = obtenerEmpleadoPorId(idEmpleado1);
        // eliminarEmpleado(empleado1);
        // empleado1 = obtenerEmpleadoPorId(idEmpleado1);
        // if (empleado1 == null) {
        //     System.out.println("El empleado ha sido eliminado correctamente.");
        // }
        // Producto producto1 = obtenerProductoPorId(1); // Asumiendo que el ID del producto es 1
        // modificarProducto(producto1);
        // producto1 = obtenerProductoPorId(1);
        // eliminarProducto(producto1);
        // producto1 = obtenerProductoPorId(1);
        // if (producto1 == null) {
        //     System.out.println("El producto ha sido eliminado correctamente.");
        // }
        ProductoBOTestB();
        AlmacenBOTestB();
        ProductoAlmacenadoTestB();
    }

    // Funciones de prueba para cada tabla
    private static Integer PersonaTest() {
        PersonaBO personaBO = new PersonaBO();
        Persona persona = new Persona();
        persona.setDni("12345678A");
        persona.setNombreCompleto("Juan Pérez");
        persona.setTelefono("987654321");
        persona.setCorreo("juan.perez@example.com");
        persona.setDireccion("Calle Falsa 123");

        Integer idInsertado = personaBO.insertar(persona.getDni(), persona.getNombreCompleto(),
                persona.getTelefono(), persona.getCorreo(),
                persona.getDireccion());
        System.out.println("Persona insertada con ID: " + idInsertado);
        return idInsertado;
    }

    private static Integer EmpleadoTest(Integer idPersona) {
        EmpleadoBO empleadoBO = new EmpleadoBO();
        EstadoEmpleado estado = EstadoEmpleado.ACTIVO;
        String nombreUsuario = "jperez";
        String contrasenha = "password123";
        double salario = 3000.00;
        Date fechaContratacion = new Date(System.currentTimeMillis());

        Integer idInsertado = empleadoBO.insertar(idPersona, estado, nombreUsuario,
                contrasenha, salario, fechaContratacion);
        System.out.println("Empleado insertado con ID: " + idInsertado);
        return idInsertado;
    }

    private static Integer AlmacenTest() {
        AlmacenBO almacenBO = new AlmacenBO();
        String nombre = "Almacén Central";
        String estado = "OPERATIVO";
        String descripcion = "Almacén principal de la empresa.";

        Integer idInsertado = almacenBO.insertar(nombre, estado, descripcion);
        System.out.println("Almacén insertado con ID: " + idInsertado);
        return idInsertado;
    }

    private static void AdministradorTest(Integer idEmpleado, Integer idAlmacen) {
        AdministradorBO administradorBO = new AdministradorBO();
        Integer idInsertado = administradorBO.insertar(idEmpleado, idAlmacen);
        System.out.println("Administrador insertado con ID: " + idInsertado);
    }

    private static void TrabajadorDeAlmacenTest(Integer idEmpleado, Integer idAlmacen) {
        TrabajadorDeAlmacenBO trabajadorBO = new TrabajadorDeAlmacenBO();
        boolean licenciaMontacarga = true;
        Integer idInsertado = trabajadorBO.insertar(idEmpleado, idAlmacen, licenciaMontacarga);
        System.out.println("Trabajador de Almacén insertado con ID: " + idInsertado);
    }

    private static Integer VendedorTest(Integer idEmpleado) {
        VendedorBO vendedorBO = new VendedorBO();
        double ingresosVentas = 50000.00;
        double porcentajeComision = 10.0;

        Integer idInsertado = vendedorBO.insertar(idEmpleado, ingresosVentas, porcentajeComision);
        System.out.println("Vendedor insertado con ID: " + idInsertado);
        return idInsertado;
    }

    private static Integer EmpresaTest() {
        EmpresaBO empresaBO = new EmpresaBO();
        String nombre = "Comerzia S.A.";
        String direccion = "Av. Empresarial 456";
        String telefono = "912345678";
        String email = "contacto@comerzia.com";
        String tipoIndustria = "Comercio";

        Integer idInsertado = empresaBO.insertar(nombre, direccion, telefono, email, tipoIndustria);
        System.out.println("Empresa insertada con ID: " + idInsertado);
        return idInsertado;
    }

    private static Integer ProveedorTest(Integer idEmpresa) {
        ProveedorBO proveedorBO = new ProveedorBO();
        Date fechaAfiliacion = new Date(System.currentTimeMillis());
        String RUC = "20-12345678-1";
        String razonSocial = "Proveedor S.A.";
        double calificacion = 4.5;
        String pais = "Perú";

        Integer idInsertado = proveedorBO.insertar(idEmpresa, fechaAfiliacion, RUC,
                razonSocial, calificacion, pais);
        System.out.println("Proveedor insertado con ID: " + idInsertado);
        return idInsertado;
    }

    private static Integer ClienteTest(Integer idEmpresa) {
        ClienteBO clienteBO = new ClienteBO();
        Date fechaRegistro = new Date(System.currentTimeMillis());
        Date fechaUltimaCompra = new Date(System.currentTimeMillis());

        Integer idInsertado = clienteBO.insertar(idEmpresa, fechaRegistro, fechaUltimaCompra);
        System.out.println("Cliente insertado con ID: " + idInsertado);
        return idInsertado;
    }

    private static void ProductoTest() {
        ProductoBO productoBO = new ProductoBO();
        Producto producto = new Producto();
        producto.setNombreProducto("Producto A");
        producto.setPrecio(100.0);
        producto.setStockMinimo(10);

        Integer idInsertado = productoBO.insertar(producto.getNombreProducto(),
                producto.getPrecio(),
                producto.getStockMinimo());
        System.out.println("Producto insertado con ID: " + idInsertado);
    }

    private static void ProductoAlmacenadoTest() {
        ProductoAlmacenadoBO paBO = new ProductoAlmacenadoBO();
        Date fechaAlmacenado = new Date(System.currentTimeMillis());
        int stockActual = 50;
        int idProducto = 1; // Asegúrate de que este ID exista
        int idAlmacen = 1;  // Asegúrate de que este ID exista

        Integer idInsertado = paBO.insertar(idAlmacen, fechaAlmacenado, stockActual, idProducto);
        System.out.println("ProductoAlmacenado insertado con ID: " + idInsertado);
    }

    private static void DocumentoTest() {
        DocumentoBO documentoBO = new DocumentoBO();
        int idEmpresa = 1; // Asegúrate de que este ID exista
        Integer idVendedor = 1; // Puede ser null si aplica
        Integer idAdministrador = 1; // Puede ser null si aplica
        Integer idTrabajadorDeAlmacen = 1; // Puede ser null si aplica

        // Integer idInsertado = documentoBO.insertar(idEmpresa, estado, tipo, idVendedor, idAdministrador, idTrabajadorDeAlmacen);
        // String cannot be converted to Estado
        Integer idInsertado = documentoBO.insertar(idEmpresa, Estado.SOLICITUD, Tipo.COMPRA, idVendedor, idAdministrador, idTrabajadorDeAlmacen);
        System.out.println("Documento insertado con ID: " + idInsertado);
    }

    private static void LineaDocumentoTest() {
        LineaDocumentoBO lineaBO = new LineaDocumentoBO();
        int idDocumento = 1; // Asegúrate de que este ID exista
        int idProducto = 1;  // Asegúrate de que este ID exista
        int cantidad = 5;
        double precioUnitario = 100.0;

        Integer idInsertado = lineaBO.insertar(idDocumento, idProducto, cantidad, precioUnitario);
        System.out.println("LineaDocumento insertado con ID: " + idInsertado);
    }

    private static void VisitaTest(Integer idVendedor) {
        VisitaBO visitaBO = new VisitaBO();
        Date fecha = new Date(System.currentTimeMillis());
        java.sql.Time duracion = new java.sql.Time(System.currentTimeMillis());
        int idCliente = 1; // Asegúrate de que este ID exista

        Integer idInsertado = visitaBO.insertar(fecha, duracion, idCliente, idVendedor);
        System.out.println("Visita insertada con ID: " + idInsertado);
    }

    // Funciones para listar y obtener registros como ejemplos
    private static void listarPersonas() {
        PersonaBO personaBO = new PersonaBO();
        ArrayList<Persona> personas = personaBO.listarTodos();
        System.out.println("Listado de Personas:");
        for (Persona p : personas) {
            System.out.println("ID: " + p.getIdPersona() + ", Nombre: " + p.getNombreCompleto());
        }
    }

    private static void modificarPersona(Persona persona) {
        PersonaBO personaBO = new PersonaBO();
        if (persona != null) {
            persona.setNombreCompleto("Juan Pérez Modificado");
            persona.setTelefono("999888777");
            persona.setCorreo("juan.perez_modificado@example.com");
            persona.setDireccion("Avenida Siempre Viva 742");
            int registrosAfectados = personaBO.modificar(persona.getIdPersona(), persona.getDni(), persona.getNombreCompleto(),
                    persona.getTelefono(), persona.getCorreo(), persona.getDireccion());
            System.out.println("Persona modificada. Registros afectados: " + registrosAfectados);
        } else {
            System.out.println("La persona proporcionada es nula.");
        }
    }

    private static void eliminarPersona(Persona persona) {
        PersonaBO personaBO = new PersonaBO();
        int registrosAfectados = personaBO.eliminar(persona);
        System.out.println("Persona eliminada. Registros afectados: " + registrosAfectados);
    }

    private static Persona obtenerPersonaPorId(Integer idPersona) {
        PersonaBO personaBO = new PersonaBO();
        return personaBO.obtenerPorId(idPersona);
    }

// Implementar funciones similares para las demás entidades
    private static void modificarEmpleado(Empleado empleado) {
        EmpleadoBO empleadoBO = new EmpleadoBO();
        if (empleado != null) {
            empleado.setNombreUsuario("jperez_mod");
            empleado.setContrasenha("newpassword123");
            empleado.setSalario(3500.00);
            empleado.setEstado(EstadoEmpleado.INACTIVO);
            int registrosAfectados = empleadoBO.modificar(empleado.getIdEmpleado(), empleado.getIdPersona(), empleado.getEstado(),
                    empleado.getNombreUsuario(), empleado.getContrasenha(), empleado.getSalario(),
                    empleado.getFechaContratacion());
            System.out.println("Empleado modificado. Registros afectados: " + registrosAfectados);
        } else {
            System.out.println("El empleado proporcionado es nulo.");
        }
    }

    private static void eliminarEmpleado(Empleado empleado) {
        EmpleadoBO empleadoBO = new EmpleadoBO();
        int registrosAfectados = empleadoBO.eliminar(empleado);
        System.out.println("Empleado eliminado. Registros afectados: " + registrosAfectados);
    }

    private static Empleado obtenerEmpleadoPorId(Integer idEmpleado) {
        EmpleadoBO empleadoBO = new EmpleadoBO();
        return empleadoBO.obtenerPorId(idEmpleado);
    }

// Ejemplo para Producto
    private static void modificarProducto(Producto producto) {
        ProductoBO productoBO = new ProductoBO();
        if (producto != null) {
            producto.setNombreProducto("Producto A Modificado");
            producto.setPrecio(120.0);
            producto.setStockMinimo(15);
            int registrosAfectados = productoBO.modificar(producto.getIdProducto(), producto.getNombreProducto(),
                    producto.getPrecio(), producto.getStockMinimo());
            System.out.println("Producto modificado. Registros afectados: " + registrosAfectados);
        } else {
            System.out.println("El producto proporcionado es nulo.");
        }
    }

    private static void eliminarProducto(Producto producto) {
        ProductoBO productoBO = new ProductoBO();
        int registrosAfectados = productoBO.eliminar(producto);
        System.out.println("Producto eliminado. Registros afectados: " + registrosAfectados);
    }

    private static Producto obtenerProductoPorId(Integer idProducto) {
        ProductoBO productoBO = new ProductoBO();
        return productoBO.obtenerPorId(idProducto);
    }

    private static void ProductoBOTestB() {
        ProductoBO productoBO = new ProductoBO();

        // Insertar un nuevo producto
        System.out.println("==> Insertando un nuevo producto:");
        Integer idInsertado = productoBO.insertar("Laptop Asus", 2500.00, 8);

        if (idInsertado != null) {
            System.out.println("Producto insertado ");
        } else {
            System.out.println("Error al insertar el producto.");
            return;
        }

        Integer idInsertado1 = productoBO.insertar("Mouse Logitech", 50.00, 15);
        if (idInsertado1 != null) {
            System.out.println("Producto insertado ");
        } else {
            System.out.println("Error al insertar el producto.");
            return;
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
            return;
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
            return;
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

    private static void AlmacenBOTestB() {
        AlmacenBO almacenBO = new AlmacenBO();

        // Insertar un nuevo almacen
        System.out.println("==> Insertando un nuevo almacen:");
        Integer idInsertado = almacenBO.insertar("AlmacenA", "EstadoA", "DescripcionA");

        if (idInsertado != null) {
            System.out.println("Almacen insertado ");
        } else {
            System.out.println("Error al insertar el almacen.");
            return;
        }

        Integer idInsertado1 = almacenBO.insertar("AlmacenB", "EstadoB", "DescripcionB");
        if (idInsertado1 != null) {
            System.out.println("Almacen insertado ");
        } else {
            System.out.println("Error al insertar el almacen.");
            return;
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
            return;
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
            return;
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

    private static void ProductoAlmacenadoTestB() {
        ProductoAlmacenadoBO productoAlmacenadoBO = new ProductoAlmacenadoBO();

        // Insertar un nuevo producto almacenado
        System.out.println("==> Insertando un nuevo producto almacenado:");
        Integer idInsertado = productoAlmacenadoBO.insertar(4, new Date(System.currentTimeMillis()), 200, 3);

        if (idInsertado != null) {
            System.out.println("Producto almacenado insertado ");
        } else {
            System.out.println("Error al insertar el producto almacenado.");
            return;
        }

        // Listar todos los productos almacenados
        System.out.println("\n==> Listando todos los productos almacenados después de la inserción:");
        ArrayList<ProductoAlmacenado> productosAlmacenados = productoAlmacenadoBO.listarTodos();
        for (ProductoAlmacenado pa : productosAlmacenados) {
            System.out.println("ID: " + pa.getIdProductoAlmacenado() + ", ID_Producto: " + pa.getIdProducto() + ", ID_Almacen: " + pa.getIdAlmacen() + ", Stock actual: " + pa.getStockActual());
        }

        // Modificar el producto almacenado recién insertado
        System.out.println("\n==> Modificando el producto almacenado con ID: " + idInsertado);
        Integer idModificado = productoAlmacenadoBO.modificar(idInsertado, 4, new Date(System.currentTimeMillis()), 69, 3);
        if (idModificado != null) {
            System.out.println("Almacen modificado con éxito.");
        } else {
            System.out.println("Error al modificar el almacen.");
            return;
        }

        // Listar todos los productos almacenados después de la modificación
        System.out.println("\n==> Listando todos los productos almacenados después de la modificación:");
        productosAlmacenados = productoAlmacenadoBO.listarTodos();
        for (ProductoAlmacenado pa : productosAlmacenados) {
            System.out.println("ID: " + pa.getIdProductoAlmacenado() + ", ID_Producto: " + pa.getIdProducto() + ", ID_Almacen: " + pa.getIdAlmacen() + ", Stock actual: " + pa.getStockActual());
        }

        // Eliminar el producto almacenado
        System.out.println("\n==> Eliminando el producto almacenado con ID: " + idInsertado);
        ProductoAlmacenado productoAlmacenadoEliminar = new ProductoAlmacenado(idInsertado, 4, new Date(System.currentTimeMillis()), 69, 3);
        Integer idEliminado = productoAlmacenadoBO.eliminar(productoAlmacenadoEliminar);
        if (idEliminado != null) {
            System.out.println("Producto Almacenado eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el almacen.");
            return;
        }

        // Listar todos los productos almacenados después de la eliminación
        System.out.println("\n==> Listando todos los productos después de la eliminación:");
        productosAlmacenados = productoAlmacenadoBO.listarTodos();
        if (productosAlmacenados.isEmpty()) {
            System.out.println("No hay productos en la lista.");
        } else {
            for (ProductoAlmacenado pa : productosAlmacenados) {
                System.out.println("ID: " + pa.getIdProductoAlmacenado() + ", ID_Producto: " + pa.getIdProducto() + ", ID_Almacen: " + pa.getIdAlmacen() + ", Stock actual: " + pa.getStockActual());
            }
        }
    }
}
