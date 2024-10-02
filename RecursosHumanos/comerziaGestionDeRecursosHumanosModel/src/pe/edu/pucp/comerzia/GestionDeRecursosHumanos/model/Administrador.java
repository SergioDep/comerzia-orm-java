/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;

/**
 * s
 *
 * @author chumbi
 */
public class Administrador {

    private Integer idAdministrador;
    private static int idCorrelativo = 1;

    private Integer idAlmacen;
    // private Almacen almacen;

    public Administrador(Integer idAdministrador, Integer idAlmacen) {
        this.idAdministrador = idAdministrador;
        this.idAlmacen = idAlmacen;
    }

    public Administrador(Integer idAlmacen) {
        this.idAdministrador = idCorrelativo++;
        this.idAlmacen = idAlmacen;
    }

    // null
    public Administrador() {
        this.idAdministrador = null;
        this.idAlmacen = null;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
}
