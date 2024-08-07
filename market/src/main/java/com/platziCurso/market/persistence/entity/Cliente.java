package com.platzicurso.market.persistence.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    private String id;  // En este caso, nosotros no tenemos que generar el ID. La identificación del cliente es una credencial que el cliente ya tiene.
    private String nombre;
    private String apellidos;
    private Long celular;
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
