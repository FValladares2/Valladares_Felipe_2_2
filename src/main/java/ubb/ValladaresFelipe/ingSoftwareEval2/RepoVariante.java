package ubb.ValladaresFelipe.ingSoftwareEval2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

//Implementado según documentación en Spring.io

public interface RepoVariante extends CrudRepository<Variante, Integer> {
    @Query("SELECT a from Variante a where a.mueble.ID_mueble = ?1")
    Iterable<Variante> findAllByIdMueble(Integer idMueble);
    @Query("SELECT a from Variante a where a.modificacion = ?1")
    Optional<Variante> findVarianteByModif(String modificacion);
}