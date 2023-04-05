package com.jvictorc.partly.demo;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Component {
    private String urlProduto;
    private String nome;
    private String avaliacoes;
    private String preco;
    private String image;
    private boolean isAvaliable;

    @Override
    public String toString() {
        return "Component{" +
                "urlProduto='" + urlProduto + '\'' +
                ", nome='" + nome + '\'' +
                ", avaliacoes='" + avaliacoes + '\'' +
                ", preco='" + preco + '\'' +
                ", image='" + image + '\'' +
                ", isAvaliable=" + isAvaliable +
                '}';
    }
}
