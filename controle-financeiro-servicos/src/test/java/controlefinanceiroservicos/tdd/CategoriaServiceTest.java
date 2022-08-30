package controlefinanceiroservicos.tdd;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controlefinanceiroservicos.model.Categoria;
import controlefinanceiroservicos.service.CategoriaService;

public class CategoriaServiceTest {

    private CategoriaService categoriaService= new CategoriaService(); 	
    
    @Test
	public void cadastroCategoriaSemParametroNome()  {
		try {
			Categoria categoriaNome= new Categoria();
			categoriaService.inserir(categoriaNome);
			fail("Nome da categoria é de preenchimento obrigatório. ");
		} catch (Exception e) {
			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
		}
	}
    
    @Test
    public void cadastroCategoriaParametroNomeVazio()  {
    	try {
    		Categoria categoriaNome= new Categoria();
    		categoriaNome.setNome("");
    		categoriaService.inserir(categoriaNome);
    		fail("Nome da categoria é de preenchimento obrigatório. ");
    	} catch (Exception e) {
    		assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
    	}
    }
    
    @Test
    public void cadastroCategoriaValidaTamanhoMinimoNome() {
    	try {
			Categoria categoriaNome= new Categoria();
			categoriaNome.setNome("Te");
			categoriaService.inserir(categoriaNome);
			fail("O Nome da categoria deve ter no minimo 3 caracteres!");
		} catch (Exception e) {
			assertEquals("O nome não pode ter menos que 3 caracteres!", e.getMessage());
		}
    }
    
   @Test
   public void cadastroCategoriaValidaTamanhoMaximoNome() {
	   try {
		   Categoria categoriaNome = new Categoria();
		   categoriaNome.setNome("0123456789012345678912");
		   categoriaService.inserir(categoriaNome);
		   fail("O Nome da categoria não pode ter mais de 20 caractres!");
	   } catch (Exception e) {
		   assertEquals("O nome não pode ter mais que 20 caracteres!", e.getMessage()); 
	   }
   }
   
   @Test
   public void cadastroCategoriaSemParametroTipo() {
	   try {
		   Categoria categoriaTipo = new Categoria();
		   categoriaTipo.setNome("Faculdade");
		   categoriaService.inserir(categoriaTipo);
		   fail("O Tipo do cadastro de categoria é de preenchimento obrigatório!");

	   } catch (Exception e) {
		   assertEquals("O campo tipo é de preenchimento obrigatório!", e.getMessage());
	   }
   }
   
   @Test
   public void cadastroCategoriaParametroTipoVazio() {
	   try {
		   Categoria categoriaTipo = new Categoria();
		   categoriaTipo.setNome("Faculdade");
		   categoriaTipo.setTipo("");
		   categoriaService.inserir(categoriaTipo);
		   fail("O Tipo do cadastro de categoria é de preenchimento obrigatório!");
		   
	   } catch (Exception e) {
		   assertEquals("O campo tipo é de preenchimento obrigatório!", e.getMessage());
	   }
   }
   
   @Test
   public void cadastroCategoriaInformandoTipoComMaisCaracteres() {
	   try {
		   Categoria categoriaTipo = new Categoria();
		   categoriaTipo.setNome("Faculdade");
		   categoriaTipo.setTipo("Dia");
		   categoriaService.inserir(categoriaTipo);
		   fail("Informar um caracter no campo Tipo!");
	   } catch (Exception e) {
		   assertEquals("O tipo tem que ter um caracter. Informe G para ganho ou D para despesa!", e.getMessage());
	   }
   }
   
   @Test
   public void cadastroCategoriaInformandoTipoInvalido() {
	   try {
		   Categoria categoriaTipo = new Categoria();
		   categoriaTipo.setNome("Faculdade");
		   categoriaTipo.setTipo("F");
		   categoriaService.inserir(categoriaTipo);
		   fail("Informar D para despesa ou G para ganho!");
	   } catch (Exception e) {
		   assertEquals("Informe G para ganho ou D para despesa!", e.getMessage());
	   }
   }
}
