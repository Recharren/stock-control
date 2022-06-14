package com.controlstock.galvamen.entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private String sobrante, cantIngresada,autorUltimo;

    private Integer stock, categoria, codigo;
    private Double precio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ultModif;

    //----------------------------------GETTER Y SETTERS--------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSobrante() {
        return sobrante;
    }

    public void setSobrante(String sobrante) {
        this.sobrante = sobrante;
    }

    public String getCantIngresada() {
        return cantIngresada;
    }

    public void setCantIngresada(String cantIngresada) {
        this.cantIngresada = cantIngresada;
    }

    public String getAutorUltimo() {
        return autorUltimo;
    }

    public void setAutorUltimo(String autorUltimo) {
        this.autorUltimo = autorUltimo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getUltModif() {
        return ultModif;
    }

    public void setUltModif(Date ultModif) {
        this.ultModif = ultModif;
    }
}
// categorias
// 1- planchuelas / 2- angulos / 3-redondos / 4- Upn / 5- caños / 6-perfil T