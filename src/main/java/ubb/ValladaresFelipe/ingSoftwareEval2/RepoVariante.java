package ubb.ValladaresFelipe.ingSoftwareEval2;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

//Implementado según documentación en Spring.io

public interface RepoVariante extends CrudRepository<Variante, Integer> {
}