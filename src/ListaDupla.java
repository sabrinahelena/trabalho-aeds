public class ListaDupla {
    private class No {
        Palavra palavra;
        No anterior;
        No proximo;

        No(Palavra palavra) {
            this.palavra = palavra;
            this.anterior = null;
            this.proximo = null;
        }
    }

    private No inicio;
    private No fim;

    void inserir(Palavra palavra) {
        No novoNo = new No(palavra);

        if (inicio == null) {
            inicio = fim = novoNo;
        } else {
            No atual = inicio;
            while (atual != null) {
                if (atual.palavra.nome.equals(palavra.nome)) {
                    return; // Palavra já existe na lista, não é necessário inserir novamente
                }
                atual = atual.proximo;
            }

            atual = inicio;
            while (atual != null && palavra.contador < atual.palavra.contador)
                atual = atual.proximo;

            if (atual == null) {
                novoNo.anterior = fim;
                fim.proximo = novoNo;
                fim = novoNo;
            } else if (atual == inicio) {
                novoNo.proximo = inicio;
                inicio.anterior = novoNo;
                inicio = novoNo;
            } else {
                novoNo.proximo = atual;
                novoNo.anterior = atual.anterior;
                atual.anterior.proximo = novoNo;
                atual.anterior = novoNo;
            }
        }
    }

    void imprimir() {
        No atual = inicio;

        while (atual != null) {
            System.out.println(atual.palavra.nome + " (" + atual.palavra.contador + ")");
            atual = atual.proximo;
        }
    }
}
