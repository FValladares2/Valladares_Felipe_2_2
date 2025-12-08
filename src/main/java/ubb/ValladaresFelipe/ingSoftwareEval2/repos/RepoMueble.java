package ubb.ValladaresFelipe.ingSoftwareEval2.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ubb.ValladaresFelipe.ingSoftwareEval2.model.Mueble;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

//Implementado según documentación en Spring.io

public interface RepoMueble extends CrudRepository<Mueble, Integer> {
    @Query("SELECT a from Mueble a where a.nombre_mueble = ?1")
    Optional<Mueble> findMuebleByNombre(String nombreMueble);
}