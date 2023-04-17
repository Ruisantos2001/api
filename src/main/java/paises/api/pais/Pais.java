package paises.api.pais;
import jakarta.persistence.*;
import lombok.*;


//JPA representação do obj Pais como tabela na bd

@Table(name="paises")
@Entity(name="Pais")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of="id")

public class Pais
{
     @Id @GeneratedValue (strategy=GenerationType.IDENTITY)
     private long id;
     private String nome,capital,regiao,subregiao,area;

     public long getId(){return id;}
     public void setId(long id){ this.id=id;}

     public Pais(DadoscriarPais dados)
     {
         this.nome=dados.nome();
         this.capital=dados.capital();
         this.regiao=dados.regiao();
         this.subregiao=dados.subregiao();
         this.area=dados.area();
     }
}