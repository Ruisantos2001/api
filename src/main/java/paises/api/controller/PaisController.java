package paises.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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
    public ResponseEntity criar(@RequestBody @Valid DadoscriarPais dados, UriComponentsBuilder uriBuilder) //método para criar um pais
    {
        Pais pais=new Pais(dados);
        repository.save(pais);
        var uri = uriBuilder.path("/paises/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhadosPais(pais));
        //uri representa o endereço que o spring vai usar para criar o cabecalho e  body,a informação que quero passar na resposta
    }

    @GetMapping()
    @Cacheable(value="Lista de Paises")
    public ResponseEntity<Page<DadosListagemPais>> listar(@PageableDefault(size=10,sort={"nome"}) Pageable paginacao) //método para mostrar paises, e ordenar os países por qualquer uma das suas propriedades.
    {
        Page page =repository.findAll(paginacao).map(DadosListagemPais::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity modificar(@RequestBody@Valid DadosmodificarPais dados)//método para modificar os dados de um país anteriormente criado
    {
        Pais pais=repository.getReferenceById(dados.id());
        pais.modificardados(dados);
        return ResponseEntity.ok(new DadosDetalhadosPais(pais));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id ) //método pathvariable indica que a variavel id e uma variavel do caminho.
    {
       repository.deleteById(id);
       return ResponseEntity.noContent().build();//para notificar que esta requisição não retorna um conteudo
    }
}