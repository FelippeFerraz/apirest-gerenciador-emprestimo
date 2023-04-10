package br.com.minsait.apirestgerenciadoremprestimo.dto;

import javax.validation.constraints.NotBlank;

public record TesteSaveDto(
        @NotBlank String nome

) {
}
