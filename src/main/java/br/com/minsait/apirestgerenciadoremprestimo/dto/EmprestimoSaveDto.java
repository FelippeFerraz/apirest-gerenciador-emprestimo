package br.com.minsait.apirestgerenciadoremprestimo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


public record EmprestimoSaveDto(
        @NotNull(message = "Valor Inicial é obrigatorio")
        @Positive(message = "Valor Inicial deve ser maior que 0")
        BigDecimal valorInicial,

        @NotBlank(message = "Nivel de relacionamento é obrigatório")
        String dataInicial,

        @NotBlank(message = "Nivel de relacionamento é obrigatório")
        String dataFinal
) {
}
