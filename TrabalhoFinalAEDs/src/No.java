public class No {
    Palavra palavra;
    No esquerda;
    No direita;
    int altura;

    No(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
        this.altura = 0; //Nó sem filhos, a altura é = 0
    }
}

