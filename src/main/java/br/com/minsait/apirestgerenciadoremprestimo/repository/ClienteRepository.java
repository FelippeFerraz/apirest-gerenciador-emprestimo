package br.com.minsait.apirestgerenciadoremprestimo.repository;

import br.com.minsait.apirestgerenciadoremprestimo.dto.TesteSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.model.EmprestimoEntity;

import java.util.Optional;

import br.com.minsait.apirestgerenciadoremprestimo.projections.ClienteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    Boolean existsByCpf(String cpf);

    Optional<ClienteEntity> findByCpf(String cpf);

    @Query(nativeQuery = true, value = "SELECT a.nome FROM TB_CLIENTES a WHERE a.nome = :nome")
    Optional<ClienteProjection> fazendoTest(String nome);

}
