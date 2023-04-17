package paises.api.pais;

public record DadosDetalhadosPais(Long id,String nome,String capital,String regiao,String subregiao,String area)
{
    public DadosDetalhadosPais(Pais pais)
    {
        this(pais.getId(),pais.getNome(),pais.getCapital(),pais.getRegiao(),pais.getSubregiao(),pais.getArea());
    }
}