package ubb.ValladaresFelipe.ingSoftwareEval2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_variante;

    private Integer id_mueble;

    private String modificacion;

    private Integer precio_extra;

    private Integer stock;

    public Integer getId_variante() {
        return id_variante;
    }

    public void setId_variante(Integer id_variante) {
        this.id_variante = id_variante;
    }

    public Integer getId_mueble() {
        return id_mueble;
    }

    public void setId_mueble(Integer id_mueble) {
        this.id_mueble = id_mueble;
    }

    public String getModificacion() {
        return modificacion;
    }

    public void setModificacion(String modificacion) {
        this.modificacion = modificacion;
    }

    public Integer getPrecio_extra() {
        return precio_extra;
    }

    public void setPrecio_extra(Integer precio_extra) {
        this.precio_extra = precio_extra;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}