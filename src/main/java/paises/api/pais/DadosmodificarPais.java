package paises.api.pais;
import jakarta.validation.constraints.NotNull;

public record DadosmodificarPais(@NotNull Long id, String capital, String area)
{

}
