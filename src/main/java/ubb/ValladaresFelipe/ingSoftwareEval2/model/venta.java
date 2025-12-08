package ubb.ValladaresFelipe.ingSoftwareEval2.model;

public class venta {
    int id_variante;
    int cantidad;
    String compra;

    public venta(int id_variante, int cantidad, String compra) {
        this.id_variante = id_variante;
        this.cantidad = cantidad;
        this.compra = compra;
    }
    public venta(){}

    public int getId_variante() {
        return id_variante;
    }

    public void setId_variante(int id_variante) {
        this.id_variante = id_variante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }
}
