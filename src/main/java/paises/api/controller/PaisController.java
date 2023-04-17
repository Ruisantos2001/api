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
    public void criar(@RequestBody @Valid DadoscriarPais dados) //metódo para criar um pais
    {
       repository.save(new Pais(dados));
    }

    @GetMapping() 
    public List<DadosListagemPais> listar() //metódo para listar paises
    {
       return repository.findAll().stream().map(DadosListagemPais::new).toList();
    }

    @PutMapping
    @Transactional
    public void modificar(@RequestBody@Valid DadosmodificarPais dados)
    {

    }
}
