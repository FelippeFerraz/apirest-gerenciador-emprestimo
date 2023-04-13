package br.com.minsait.apirestgerenciadoremprestimo.model;

import br.com.minsait.apirestgerenciadoremprestimo.dto.EmprestimoSaveDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_EMPRESTIMOS")
@Getter
@Setter
@NoArgsConstructor
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_CPF")
    private ClienteEntity cliente;

    @Column(name = "VALOR_INICIAL")
    private BigDecimal valorInicial;

    @Column(name = "VALOR_FINAL")
    private BigDecimal valorFinal;

    @Column(name = "DATA_INICIAL")
    private String dataInicial;

    @Column(name = "DATA_FINAL")
    private String dataFinal;

    public EmprestimoEntity(EmprestimoSaveDto dto) {
        this.valorInicial = dto.valorInicial();
        this.dataInicial = dto.dataInicial();
        this.dataFinal = dto.dataFinal();
    }

}
