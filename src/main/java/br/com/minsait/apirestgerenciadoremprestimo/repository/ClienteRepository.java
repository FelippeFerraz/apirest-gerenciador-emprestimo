package br.com.minsait.apirestgerenciadoremprestimo.repository;

import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    Boolean existsByCpf(String cpf);

    Optional<ClienteEntity> findByCpf(String cpf);

}
