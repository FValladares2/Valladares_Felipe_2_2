package ubb.ValladaresFelipe.ingSoftwareEval2.model;

import jakarta.persistence.*;

import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class Mueble {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_mueble")
    private Integer ID_mueble;

    @Column(name = "nombre_mueble")
    private String nombre_mueble;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "precio_base")
    private Integer precio_base;
    @Column(name = "stock")
    private Integer stock;

    @OneToMany(mappedBy = "mueble")
    private List<Variante> variantes;


//    enum Estado {
//        ACTIVO,
//        INACTIVO
//    }
    @Column(name = "estado")
    private String estado;

//    enum Tamaño {
//        GRANDE,
//        MEDIANO,
//        PEQUEÑO
//    }
    @Column(name = "tamano")
    private String tamano;
    @Column(name = "material")
    private String material;

    public Integer getID_mueble() {
        return ID_mueble;
    }

    public void setID_mueble(Integer ID_mueble) {
        this.ID_mueble = ID_mueble;
    }

    public String getNombre_mueble() {
        return nombre_mueble;
    }

    public void setNombre_mueble(String nombre_mueble) {
        this.nombre_mueble = nombre_mueble;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(Integer precio_base) {
        this.precio_base = precio_base;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}