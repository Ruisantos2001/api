package paises.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import paises.api.pais.*;

import java.util.List;

@RestController //para identificar  que esta classe e um controller
@RequestMapping("paises") //sempre que chegar uma requesição do tipo paises ele vai chamar esse PaisController
public class PaisController
{
    @Autowired //para instanciar o repositorio sozinho
    private PaisRepository repository;
    @PostMapping()
    @Transactional
    @CacheEvict(value="Lista de Paises",allEntries = true)//limpar a cache,todas entradas.
    public void criar(@RequestBody @Valid DadoscriarPais dados) //método para criar um pais
    {
        repository.save(new Pais(dados));
    }

    @GetMapping()
    @Cacheable(value="Lista de Paises")
    public Page<DadosListagemPais> listar(@PageableDefault(size=10,sort={"nome"}) Pageable paginacao) //método para mostrar paises, e ordenar os países por qualquer uma das suas propriedades.
    {
        return repository.findAll(paginacao).map(DadosListagemPais::new);
    }

    @PutMapping
    @Transactional
    public void modificar(@RequestBody@Valid DadosmodificarPais dados)//método para modificar os dados de um país anteriormente criado
    {
        Pais pais=repository.getReferenceById(dados.id());
        pais.modificardados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id ) //método pathvariable indica que a variavel id e uma variavel do caminho.
    {
       repository.deleteById(id);
    }
}