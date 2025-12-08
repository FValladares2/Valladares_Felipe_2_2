package ubb.ValladaresFelipe.ingSoftwareEval2.control;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ubb.ValladaresFelipe.ingSoftwareEval2.model.Mueble;
import ubb.ValladaresFelipe.ingSoftwareEval2.model.venta;
import ubb.ValladaresFelipe.ingSoftwareEval2.repos.RepoMueble;
import ubb.ValladaresFelipe.ingSoftwareEval2.repos.RepoVariante;
import ubb.ValladaresFelipe.ingSoftwareEval2.model.Variante;

import java.util.Optional;

@Controller
public class Controlador {
    @Autowired
    private RepoMueble muebles;
    @Autowired
    private RepoVariante variantes;

    // métodos front-end
    @GetMapping("/")
    public String homepage(Model model) {
        return "index";
    }
    @GetMapping("/clientes")
    public String entradaClientes(Model model) {
        model.addAttribute("muebles", listarMuebles());
        model.addAttribute("variantes", listarVariantes());
        return "listar_paraClientes";
    }
    @GetMapping("/admin")
    public String entradaAdmin(Model model) {
        model.addAttribute("muebles", listarMuebles());
        model.addAttribute("variantes", listarVariantes());
        return "listar_muebles";
    }

    @GetMapping("/editMueble/{id}")
    public String editarMueble(@PathVariable(value = "id") int id, Model model) {
        Mueble m = listarMueble(id);
        if (m != null || id != -1) model.addAttribute("mueble", m);
        else model.addAttribute("mueble", new Mueble());
        model.addAttribute("idM", id);
        return "editar_mueble";
    }

    @PostMapping("/guardaMueble")
    public String guardarMueble(@ModelAttribute("mueble") Mueble mueble) {
        if (mueble.getID_mueble() == null)
            crearMueble(mueble.getNombre_mueble(), mueble.getTipo(), mueble.getPrecio_base(),
                mueble.getStock(), mueble.getMaterial(), mueble.getEstado(), mueble.getTamano());
        else
            actualizarMueble(mueble.getID_mueble(), mueble.getNombre_mueble(), mueble.getTipo(),
                    mueble.getPrecio_base(), mueble.getMaterial(), mueble.getEstado(), mueble.getTamano());
        return "redirect:/";
    }

    @GetMapping("/elimMueble/{id}")
    public String eliminarMueble(@PathVariable(value = "id") int id, Model model) {
        eliminarMueble(id);
        return "redirect:/";
    }

    @GetMapping("/newVariante/{id}")
    public String nuevaVariante(@PathVariable(value = "id") int id, Model model) {
        Variante v = new Variante();
        Mueble m = listarMueble(id);
        v.setMueble(m);
        model.addAttribute("variante", v);
        return "editar_variante";
    }

    @GetMapping("/editVariante/{id}")
    public String editarVariante(@PathVariable(value = "id") int id, Model model) {
        Variante v = listarVariante(id);
        model.addAttribute("variante", v);
        return "editar_variante";
    }

    @PostMapping("/guardaVariante")
    public String guardarVariante(@ModelAttribute("variante") Variante variante) {
        if (variante.getId_variante() == null)
            crearVariante(variante.getMueble().getID_mueble(), variante.getModificacion(),
                    variante.getPrecio_extra(), variante.getStock());
        else
            actualizarVariante(variante.getId_variante(), variante.getModificacion(), variante.getPrecio_extra());
        return "redirect:/";
    }

    @GetMapping("/venta/{id}")
    public String ventaVariante(@PathVariable(value = "id") int id, Model model) {
        Variante v = listarVariante(id);
        model.addAttribute("variante", v);
        model.addAttribute("venta", new venta(id, 0, ""));
        return "vender_variante";
    }
    @PostMapping("/venderVariante")
    public String venderVariante(@ModelAttribute("venta") venta v) throws Exception {
        if (v.getCompra().equals("venta")) venderVariante(v.getId_variante(), v.getCantidad());
        else comprarVariante(v.getId_variante(), v.getCantidad());
        return "redirect:/";
    }

    //métodos back-end (los podría haber hecho servicios)
    @PostMapping(path="/addm")
    public @ResponseBody String crearMueble (@RequestParam String nombre
            , @RequestParam String tipo, @RequestParam Integer precio_base
            , @RequestParam Integer stock, @RequestParam String material
            , @RequestParam String estado, @RequestParam String tamaño) {
        Mueble n = new Mueble();
        n.setNombre_mueble(nombre);
        n.setTipo(tipo);
        n.setPrecio_base(precio_base);
        n.setStock(0);
        n.setMaterial(material);
        n.setEstado(estado);
        n.setTamano(tamaño);
        Mueble m = muebles.save(n);
        return crearVariante(m.getID_mueble(), "Normal", 0, stock);
    }

    @PostMapping(path="/addv")
    public @ResponseBody String crearVariante (@RequestParam Integer id_mueble
            , @RequestParam String modificacion, @RequestParam Integer precio_extra
            , @RequestParam Integer stock) {
        Variante n = new Variante();
        n.setModificacion(modificacion);
        n.setPrecio_extra(precio_extra);
        n.setStock(stock);
        Mueble m = muebles.findById(id_mueble).orElse(null);
        if (m == null) {return "No se encontró el mueble";}
        n.setMueble(m);
        int stockm = m.getStock();
        m.setStock(stockm + stock);
        variantes.save(n);
        return "Creado";
    }

    @GetMapping(path="/onem")
    public @ResponseBody Mueble listarMueble(@RequestParam int id_mueble) {
        Optional<Mueble> n = muebles.findById(id_mueble);
        return n.orElse(null);
    }

    @GetMapping(path="/onemnombre")
    public @ResponseBody Mueble listarMueble(@RequestParam String nombre) {
        Optional<Mueble> n = muebles.findMuebleByNombre(nombre);
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

    @GetMapping(path="/onevnombre")
    public @ResponseBody Variante listarVariante(@RequestParam String modificacion) {
        Optional<Variante> n = variantes.findVarianteByModif(modificacion);
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

        return v.getPrecio_extra() + v.getMueble().getPrecio_base();
    }

    @PostMapping(path="/ven")
    public @ResponseBody String venderVariante(@RequestParam int id_variante,
                                               @RequestParam int cantidad) throws Exception{
        Variante v = listarVariante(id_variante);
        if (v != null){
            int stockv = v.getStock();
            int stockm = v.getMueble().getStock();
            if (v.getMueble().getEstado().equals("INACTIVO")){
                return "Mueble inactivo";
            }

            if (stockm >= cantidad && stockv >= cantidad) {
                v.setStock(stockv - cantidad);
                v.getMueble().setStock(stockm - cantidad);
                variantes.save(v);
                return "Vendido: "+cantidad;
            }else throw new Exception("Stock insuficiente en variante "+id_variante);

        }return "Variante inexistente";
    }

    @PostMapping(path="/com")
    public @ResponseBody String comprarVariante(int id_variante, int cantidad){
        Variante v = listarVariante(id_variante);
        if (v != null){
            int stockv = v.getStock();
            int stockm = v.getMueble().getStock();
            v.setStock(stockv + cantidad);
            v.getMueble().setStock(stockm + cantidad);
            variantes.save(v);
            return "Comprado: "+cantidad;
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
            n.setTamano(tamaño);
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

    @PostMapping(path="/drp")
    public @ResponseBody String eliminarMueble (@RequestParam Integer id_mueble) {
        //(elimina en bdd. No se pide, pero se ve necesario por si acaso)
        if (muebles.existsById(id_mueble)){
            Iterable<Variante> v = variantes.findAllByIdMueble(id_mueble); //realiza query simple
            variantes.deleteAll(v); //liberar variantes creadas
            muebles.deleteById(id_mueble);
            return "Eliminado";
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
