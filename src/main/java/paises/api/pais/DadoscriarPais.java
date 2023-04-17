package paises.api.pais;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadoscriarPais(@NotBlank String nome, @NotBlank String capital, @NotBlank String regiao, @NotBlank String subregiao, @NotBlank String area)//uma classe imutavel , usada para enviar ou receber dados
{

}
