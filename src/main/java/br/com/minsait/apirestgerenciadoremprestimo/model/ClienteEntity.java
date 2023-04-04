package br.com.minsait.apirestgerenciadoremprestimo.model;

import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClienteEntity {
    @Column(name = "NOME")
    private String nome;
    @Id
    @Column(name = "CPF")
    private String cpf;
    @Embedded
    private Endereco endereco;
    @Column(name = "RENDIMENTO_MENSAL")
    private BigDecimal rendimentoMensal;
    @Column(name = "NIVEL_RELACIONAMENTO")
    @Enumerated(EnumType.STRING)
    private NivelRelacionamento relacionamento;


    public ClienteEntity(String nome, String cpf, Endereco endereco, BigDecimal rendimentoMensal){

        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.rendimentoMensal = rendimentoMensal;

    }
}
