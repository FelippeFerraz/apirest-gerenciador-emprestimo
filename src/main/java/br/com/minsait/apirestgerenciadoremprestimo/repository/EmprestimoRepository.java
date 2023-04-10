package br.com.minsait.apirestgerenciadoremprestimo.repository;

import br.com.minsait.apirestgerenciadoremprestimo.model.EmprestimoEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM TB_EMPRESTIMOS a WHERE a.cliente_cpf = :cpf")
    List<EmprestimoEntity> findAllByCpfCliente(@Param("cpf") String cpf);

    @Query(nativeQuery = true, value = "SELECT * FROM TB_EMPRESTIMOS a WHERE a.cliente_cpf = :cpf AND a.id = :id")
    Optional<EmprestimoEntity> findByCpfClienteAndId(@Param("cpf") String cpf, @Param("id") Long id);

}
