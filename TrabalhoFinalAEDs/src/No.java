public class No {
    //Declaração do objeto "palavra" presente em cada Nó da árvore AVL.
    Palavra palavra;
    //Declaração do atributo Nó/filho que ficará a esquerda do pai/raiz na árvore.
    No esquerda;
    //Declaração do atributo Nó/filho que ficará a direita do pai/raiz na árvore.
    No direita;
    //Declaração do atributo altura que servirá como um contador que irá retornar a altura da árvore.
    int altura;

    No(Palavra palavra) {
        //Instanciamento de como o objeto "palavra" sera iniciado de acordo como foi definido na sua classe.
        this.palavra = palavra;
        //Instanciamento de como o atributo "esquerdo" sera iniciado.
        this.esquerda = null;
        //Instanciamento de como o atributo "direita" sera iniciado.
        this.direita = null;
        //Instanciamento de como o atributo "altura" sera iniciado.
        this.altura = 0;
    }
}

