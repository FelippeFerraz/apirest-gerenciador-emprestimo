package br.com.minsait.apirestgerenciadoremprestimo.model;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteDto;
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


    @Id
    private String cpf;
    @Column(name = "NOME")
    private String nome;


    @Embedded
    private Endereco endereco;
    @Column(name = "RENDIMENTO_MENSAL")
    private BigDecimal rendimentoMensal;
    @Column(name = "NIVEL_RELACIONAMENTO")
    @Enumerated(EnumType.STRING)
    private NivelRelacionamento relacionamento;


    public ClienteEntity(ClienteDto dto){

        this.nome = dto.nome();
        this.cpf =dto.cpf();
        this.endereco = dto.endereco();
        this.rendimentoMensal = dto.rendimentoMensal();
        this.relacionamento = dto.relacionamento();

    }
}
