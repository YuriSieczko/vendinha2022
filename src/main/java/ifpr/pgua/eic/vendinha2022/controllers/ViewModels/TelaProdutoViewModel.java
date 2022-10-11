package ifpr.pgua.eic.vendinha2022.controllers.ViewModels;

import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import ifpr.pgua.eic.vendinha2022.model.repositories.GerenciadorLoja;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Classe para representar os dados da tela de clientes, bem
 * como controlar o que irá ocorrer. 
 */


public class TelaProdutoViewModel {
    
    /*Aqui são definidas a propriedades que serão ligadas com os
     * textfield da tela.
     */

    private StringProperty spNome = new SimpleStringProperty();
    private StringProperty spDescricao = new SimpleStringProperty();
    private DoubleProperty spValor = new SimpleDoubleProperty();
    private DoubleProperty spQuantidadeEstoque = new SimpleDoubleProperty();

    /*Aqui são definidas duas propriedades para controlar o texto
     * de um dos botões da tela e também se os textfields tfNome e tfCpf podem
     * ser editados.
     */
    private StringProperty operacao = new SimpleStringProperty("Cadastrar");
    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private boolean atualizar = false;

    /*Lista que será utilizada para povar a TableView */
    private ObservableList<ProdutoRow> obsProduto = FXCollections.observableArrayList();
    
    /*Objeto que serve para indicar qual linha da tabela está selecionada. */
    private ObjectProperty<ProdutoRow> selecionado = new SimpleObjectProperty<>();

    private GerenciadorLoja gerenciador;

    public TelaProdutoViewModel(GerenciadorLoja gerenciador){
        
        this.gerenciador = gerenciador;

        updateList();

    }

    /*Atualiza a lista observável de clientes, que por consequência irá
     * atualizar o conteúdo mostrado pela TableView.
     */
    private void updateList(){
        obsProduto.clear();
        for(Produto p:gerenciador.getProdutos()){
            obsProduto.add(new ProdutoRow(p));
        }
    }


    public ObservableList<ProdutoRow> getProduto(){
        return this.obsProduto;
    }

    /*Métodos para acesso as propriedades. */

    public StringProperty operacaoProperty(){
        return operacao;
    }

    public BooleanProperty podeEditarProperty(){
        return podeEditar;
    }

    public StringProperty nomeProperty(){
        return this.spNome;
    }

    public StringProperty descricaoProperty(){
        return this.spDescricao;
    }

    public DoubleProperty valorProperty(){
        return this.spValor;
    }

    public DoubleProperty quantideEstoqueProperty(){
        return this.spQuantidadeEstoque;
    }

    public ObjectProperty<ProdutoRow> selecionadoProperty(){
        return selecionado;
    }


    /*Método que será invocado quando
     * o botão de cadastrar for clicado na tela.
     */

    public void cadastrar(){

        //acessa os valores das propriedades, que por consequência
        //contém os valores digitados nos textfields.
       
        String nome = spNome.getValue();
        String descricao = spDescricao.getValue();
        Double valor = spValor.getValue();
        Double quantidadeEstoque = spQuantidadeEstoque.getValue();


        if(atualizar){
            gerenciador.atualizarProduto(nome, valor, quantidadeEstoque);
        }else{
            gerenciador.adicionarProduto(nome, descricao, valor, quantidadeEstoque);
        }
        
        updateList();
        limpar();
    }

    public void atualizar(){
        operacao.setValue("Atualizar");
        podeEditar.setValue(false);
        atualizar = true;
        Produto produto = selecionado.get().getProduto();
        spNome.setValue(produto.getNome());
        spDescricao.setValue(produto.getDescricao());
        spValor.setValue(produto.getValor());
        spQuantidadeEstoque.setValue(produto.getQuantidadeEstoque());

    }



    public void limpar(){
        spNome.setValue("");
        spDescricao.setValue("");
        spValor.setValue(null);
        spQuantidadeEstoque.setValue(null);
        podeEditar.setValue(true);
        atualizar = false;
        operacao.setValue("Cadastrar");
    }





}
