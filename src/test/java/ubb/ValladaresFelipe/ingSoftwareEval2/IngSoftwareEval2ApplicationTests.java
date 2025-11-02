package ubb.ValladaresFelipe.ingSoftwareEval2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class IngSoftwareEval2ApplicationTests {
    @Autowired
    private Controlador c;
	@Test
	void contextLoads() {
        assertThat(c).isNotNull();
	}

    @Test
    void testCRUD() {
        //Create
        assertEquals("Creado", c.crearMueble("testcrud", "mesa", 15,
                8, "madera", "activo", "mediano"));
        //Read
        assertEquals("mesa_madera", c.listarMueble(2).getNombre_mueble());

        //Update
        assertEquals("Actualizado", c.actualizarMueble(1, "mueble_ejemplo",
                "silla", 120, "madera", "activo", "mediano"));
        //Delete
        assertEquals("Desactivado", c.desactivarMueble(1));
        assertEquals("Activado", c.activarMueble(1));
    }

    @Test
    void testCotizacion(){
        assertEquals(620, c.cotizar(1));
    }

    @Test
    void testStockError(){
        assertThrows(Exception.class, () -> c.venderVariante(1, 20));
    }
}
