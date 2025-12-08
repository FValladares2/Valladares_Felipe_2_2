package ubb.ValladaresFelipe.ingSoftwareEval2.model;

import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_variante")
    private Integer id_variante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_mueble", nullable = false)
    private Mueble mueble;
    @Column(name = "modificacion")
    private String modificacion;
    @Column(name = "precio_extra")
    private Integer precio_extra;
    @Column(name = "stock")
    private Integer stock;

    public Integer getId_variante() {
        return id_variante;
    }

    public void setId_variante(Integer id_variante) {
        this.id_variante = id_variante;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
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