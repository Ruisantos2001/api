package paises.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void criar(@RequestBody @Valid DadoscriarPais dados) //método para criar um pais
    {
        repository.save(new Pais(dados));
    }

    @GetMapping() 
    public List<DadosListagemPais> listar() //método para listar paises
    {
        return repository.findAll().stream().map(DadosListagemPais::new).toList();
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
