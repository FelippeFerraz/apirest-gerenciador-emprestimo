package br.com.minsait.apirestgerenciadoremprestimo.model;

import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    private ClienteEntity cliente;

    @Column(name = "VALOR_INICIAL")
    private BigDecimal valorInicial;
    @Column(name = "VALOR_FINAL")
    private BigDecimal valorFinal;
    @Column(name = "DATA_INICIAL")
    private Date dataInicial;
    @Column(name = "DATA_FINAL")
    private Date dataFinal;



    public EmprestimoEntity() {
    }


}
