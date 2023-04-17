package paises.api.controller;

import paises.api.pais.Pais;

public record DadosListagemPais(long id,String nome, String capital, String area,String regiao)
{
    public DadosListagemPais(Pais pais )
    {
        this(pais.getId(),pais.getNome(),pais.getCapital(),pais.getArea(),pais.getRegiao());
    }
}
