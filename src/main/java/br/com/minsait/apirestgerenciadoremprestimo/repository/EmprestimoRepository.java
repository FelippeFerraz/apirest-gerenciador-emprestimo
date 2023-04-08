package br.com.minsait.apirestgerenciadoremprestimo.repository;

import br.com.minsait.apirestgerenciadoremprestimo.model.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {

}
