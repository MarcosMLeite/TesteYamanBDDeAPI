package Tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import elementos.Elementos;
import pages.MetodoStock;

public class TestShoes {

	MetodoStock ma = new MetodoStock();
	Elementos elemento = new Elementos();

	@Given("^acessar site \"([^\"]*)\"$")
	public void acessar_site(String site) throws Throwable {
		ma.abrirNavegador(site, "CHROME", "acessando o site da shoestock");

	}

	@When("^pesquiso \"([^\"]*)\"$")
	public void pesquiso(String produto) throws Throwable {
		ma.esperarElemento(elemento.getPesquisar(), " esperando a página carregar o elemento para pesquisa");
		ma.preencher(elemento.getPesquisar(), produto, "  buscando" + produto + " na barra de pesquisa");
		ma.submit(elemento.getPesquisar(), "submit para pesquisar o " + produto);

	}

	@Then("^compro o sapato$")
	public void compro_o_sapato() throws Throwable {
		ma.esperar(5000, "esperado produto ");
		ma.escolherPorPosicao("3", " escolhendo o produto da primeira posição");
		ma.validarCarrinho("3", "Comprando sapato");
	}
}
