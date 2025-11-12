package ubb.ValladaresFelipe.ingSoftwareEval2;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class IngSoftwareEval2ApplicationTests {
    @Autowired
    private Controlador c;
    //@Autowired
    //EntityManagerFactory emf;
	@Test
	void contextLoads() {
        assertThat(c).isNotNull();
	}

    @Test
    void testCRUD() {
        //No utiliza mockito para testear en mock environment
        // Es necesario tener la bdd corriendo con XAMPP para que funcione

        //EntityManager em = emf.createEntityManager();
        //Create mueble
        assertEquals("Creado", c.crearMueble("testcrud", "mesa", 15,
                8, "madera", "activo", "mediano"));
        //Read mueble
        assertEquals("mesa_madera", c.listarMueble(2).getNombre_mueble());
        Mueble m = c.listarMueble("testcrud");
        assertEquals("testcrud", c.listarMueble(m.getID_mueble()).getNombre_mueble());
        //em.persist(m);
        //Update mueble
        assertEquals("Actualizado", c.actualizarMueble(1, "mueble_ejemplo",
                "silla", 120, "madera", "activo", "mediano"));
        //Delete mueble (desactivar)
        assertEquals("Desactivado", c.desactivarMueble(2));
        assertEquals("Activado", c.activarMueble(2));

        //Create variante
        m = c.listarMueble("testcrud");
        assertEquals("Creado", c.crearVariante(m.getID_mueble(), "test_variante", 40, 8));
        //Read variante
        assertEquals("ruedas de escritorio", c.listarVariante(1).getModificacion());
        Variante v = c.listarVariante("test_variante");
        //em.persist(v);
        //Update variante
        assertEquals("Comprado: 4", c.comprarVariante(v.getId_variante(), 4));
        try {
            assertEquals("Vendido: 4", c.venderVariante(v.getId_variante(), 4));
        }catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        assertEquals("Actualizado", c.actualizarVariante(1, "ruedas de escritorio", 500));
        //Delete variante (con mueble asociado)
        //comentar eliminar para ver si funciona en bdd
        assertEquals("Eliminado", c.eliminarMueble(m.getID_mueble()));

        //em.close();
    }

    @Test
    void testCotizacion(){
        // ID variante 1 (en bdd) = mueble_ejemplo (id 1), variante ruedas de escritorio
        // precio_extra = 500, Precio base = 120. Total cotizado debería ser 620
        assertEquals(620, c.cotizar(1));
    }

    @Test
    void testStockError(){
        // ID variante 1 (en bdd) tiene un stock de 2. Venter 20 stock debe fallar.
        assertThrows(Exception.class, () -> c.venderVariante(1, 20));
    }
}
