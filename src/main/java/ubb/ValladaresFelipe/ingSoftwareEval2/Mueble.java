package ubb.ValladaresFelipe.ingSoftwareEval2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Mueble {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID_mueble;

    private String nombre_mueble;

    private String tipo;

    private Integer precio_base;

    private Integer stock;

//    enum Estado {
//        ACTIVO,
//        INACTIVO
//    }

    private String estado;

//    enum Tamaño {
//        GRANDE,
//        MEDIANO,
//        PEQUEÑO
//    }

    private String tamaño;

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

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}