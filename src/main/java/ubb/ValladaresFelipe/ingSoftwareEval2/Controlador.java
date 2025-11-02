package ubb.ValladaresFelipe.ingSoftwareEval2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/bdd")
public class Controlador {
    @Autowired
    private RepoMueble muebles;
    @Autowired
    private RepoVariante variantes;

    @PostMapping(path="/addm") // Map ONLY POST Requests
    public @ResponseBody String crearMueble (@RequestParam String nombre
            , @RequestParam String tipo, @RequestParam Integer precio_base
            , @RequestParam Integer stock, @RequestParam String material
            , @RequestParam String estado, @RequestParam String tamaño) {
        Mueble n = new Mueble();
        n.setNombre_mueble(nombre);
        n.setTipo(tipo);
        n.setPrecio_base(precio_base);
        n.setStock(stock);
        n.setMaterial(material);
        n.setEstado(estado);
        n.setTamaño(tamaño);
        Mueble m = muebles.save(n);
        return crearVariante(m.getID_mueble(), "Normal", 0, stock);
    }

    @PostMapping(path="/addv") // Map ONLY POST Requests
    public @ResponseBody String crearVariante (@RequestParam Integer id_mueble
            , @RequestParam String modificacion, @RequestParam Integer precio_extra
            , @RequestParam Integer stock) {
        Variante n = new Variante();
        n.setId_mueble(id_mueble);
        n.setModificacion(modificacion);
        n.setPrecio_extra(precio_extra);
        n.setStock(stock);
        variantes.save(n);
        return "Creado";
    }

    @GetMapping(path="/onem")
    public @ResponseBody Mueble listarMueble(@RequestParam int id_mueble) {
        Optional<Mueble> n = muebles.findById(id_mueble);
        return n.orElse(null);
    }

    @GetMapping(path="/allm")
    public @ResponseBody Iterable<Mueble> listarMuebles() {
        // This returns a JSON or XML with the users
        return muebles.findAll();
    }

    @GetMapping(path="/onev")
    public @ResponseBody Variante listarVariante(@RequestParam int id_variante) {
        Optional<Variante> n = variantes.findById(id_variante);
        return n.orElse(null);
    }

    @GetMapping(path="/allv")
    public @ResponseBody Iterable<Variante> listarVariantes() {
        // This returns a JSON or XML with the users
        return variantes.findAll();
    }

    @GetMapping(path="/cot")
    public @ResponseBody Integer cotizar(@RequestParam int id_variante) {
        //encuentra la cotización total dada id_variante (precio_base + precio_extra)
        Variante v = listarVariante(id_variante);
        Mueble m = listarMueble(v.getId_mueble());

        return v.getPrecio_extra() + m.getPrecio_base();
    }

    @PostMapping(path="/ven")
    public @ResponseBody String venderVariante(@RequestParam int id_variante,
                                               @RequestParam int cantidad) throws Exception{
        Variante v = listarVariante(id_variante);
        if (v != null){
            Mueble m = listarMueble(v.getId_mueble());
            int stockv = v.getStock();
            int stockm = m.getStock();
            if (m.getEstado().equals("INACTIVO")){
                return "Mueble inactivo";
            }

            if (stockm >= cantidad && stockv >= cantidad) {
                v.setStock(stockv - cantidad);
                m.setStock(stockm - cantidad);
                return "Vendido";
            }else throw new Exception("Stock insuficiente en variante "+id_variante);

        }return "Variante inexistente";
    }

    @PostMapping(path="/com")
    public @ResponseBody String comprarVariante(int id_variante, int cantidad){
        Variante v = listarVariante(id_variante);
        if (v != null){
            Mueble m = listarMueble(v.getId_mueble());
            int stockv = v.getStock();
            int stockm = m.getStock();
            v.setStock(stockv + cantidad);
            m.setStock(stockm + cantidad);
            return "Vendido";
        }return "Variante inexistente";
    }

    @PostMapping(path="/updm")
    public @ResponseBody String actualizarMueble (
            @RequestParam Integer id_mueble, @RequestParam String nombre
            , @RequestParam String tipo, @RequestParam Integer precio_base
            , @RequestParam String material, @RequestParam String estado
            , @RequestParam String tamaño) {

        Optional<Mueble> m = muebles.findById(id_mueble);
        if (m.isPresent()) {
            Mueble n = m.get();
            n.setNombre_mueble(nombre);
            n.setTipo(tipo);
            n.setPrecio_base(precio_base);
            n.setMaterial(material);
            n.setEstado(estado);
            n.setTamaño(tamaño);
            muebles.save(n);
            return "Actualizado";
        }else{
            return "Mueble no encontrado";
        }

    }

    @PostMapping(path="/updv")
    public @ResponseBody String actualizarVariante (
            @RequestParam Integer id_variante, @RequestParam String modificacion
            , @RequestParam Integer precio_extra) {

        Optional<Variante> m = variantes.findById(id_variante);
        if (m.isPresent()) {
            Variante n = m.get();
            n.setModificacion(modificacion);
            n.setPrecio_extra(precio_extra);

            variantes.save(n);
            return "Actualizado";
        }else{
            return "Variante no encontrada";
        }

    }

    @PostMapping(path="/del")
    public @ResponseBody String desactivarMueble (@RequestParam Integer id_mueble) {
        //(no elimina, pero si se desactiva en bdd)
        if (muebles.existsById(id_mueble)){
            Mueble m = muebles.findById(id_mueble).get();
            m.setEstado("INACTIVO");
            muebles.save(m);
            return "Desactivado";
        }else{
            return "Mueble no encontrado";
        }
    }

    @PostMapping(path="/act")
    public @ResponseBody String activarMueble (@RequestParam Integer id_mueble) {
        if (muebles.existsById(id_mueble)){
            Mueble m = muebles.findById(id_mueble).get();
            m.setEstado("ACTIVO");
            muebles.save(m);
            return "Activado";
        }else{
            return "Mueble no encontrado";
        }
    }
}