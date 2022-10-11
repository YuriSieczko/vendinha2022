package ifpr.pgua.eic.vendinha2022.controllers.ViewModels;

import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProdutoRow {

    private Produto produto;

    public ProdutoRow(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public StringProperty idProperty() {
        return new SimpleStringProperty(String.valueOf(produto.getId()));
    }

    public StringProperty nomeProperty() {
        return new SimpleStringProperty(produto.getNome());
    }

    public StringProperty descricaoProperty() {
        return new SimpleStringProperty(produto.getDescricao());
    }

    public DoubleProperty valorProperty() {
        return new SimpleDoubleProperty(produto.getValor());
    }

    public DoubleProperty quantidadeEstoqueProperty() {
        return new SimpleDoubleProperty(produto.getQuantidadeEstoque());
    }

}
