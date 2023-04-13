package br.com.minsait.apirestgerenciadoremprestimo.model;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteUpdateDto;
import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_CLIENTES")
@Getter
@Setter
@NoArgsConstructor
public class ClienteEntity {


    @Id
    private String cpf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TELEFONE")
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Column(name = "RENDIMENTO_MENSAL")
    private BigDecimal rendimentoMensal = new BigDecimal(0);

    @Column(name = "NIVEL_RELACIONAMENTO")
    @Enumerated(EnumType.STRING)
    private NivelRelacionamento relacionamento;

    public ClienteEntity(ClienteSaveDto dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.endereco = dto.endereco();
        this.rendimentoMensal = dto.rendimentoMensal();
        this.relacionamento = dto.relacionamento();
        this.telefone = dto.telefone();
    }

    public void update(ClienteUpdateDto dto) {
        this.nome = dto.nome();
        this.endereco = dto.endereco();
        this.rendimentoMensal = dto.rendimentoMensal();
        this.relacionamento = dto.relacionamento();
        this.telefone = dto.telefone();
    }

}
