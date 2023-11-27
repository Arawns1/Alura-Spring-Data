package br.com.alura.springdata.repository;

import br.com.alura.springdata.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {
}
