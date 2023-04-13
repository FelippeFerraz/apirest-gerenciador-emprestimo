package br.com.minsait.apirestgerenciadoremprestimo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Endereco {

    @NotBlank(message = "Rua é obrigatória")
    private String rua;

    @NotNull(message = "Numero é obrigatório")
    private Integer numero;

    @Pattern(regexp = "\\d{8}", message = "CEP inválido")
    private String cep;


    public Endereco(String rua, int numero, String cep) {

        this.rua = rua;
        this.numero = numero;
        this.cep = cep;

    }
}
