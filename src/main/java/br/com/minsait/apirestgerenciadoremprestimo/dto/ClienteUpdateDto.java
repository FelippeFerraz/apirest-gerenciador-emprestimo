package br.com.minsait.apirestgerenciadoremprestimo.dto;

import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import br.com.minsait.apirestgerenciadoremprestimo.model.Endereco;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record ClienteUpdateDto(
        @NotBlank(message = "Nome é obrigatório") String nome,

        @Valid Endereco endereco,

        @Pattern(regexp = "\\d{11}", message = "Telefone inválido")
        String telefone,

        @NotNull(message = "Rendimento é obrigatorio")
        @Positive(message = "Rendimento deve ser maior que 0")
        BigDecimal rendimentoMensal,

        @NotNull(message = "Nivel de relacionamento é obrigatório")
        NivelRelacionamento relacionamento
) {
}
