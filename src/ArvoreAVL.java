public class ArvoreAVL {  No raiz;

    int altura(No no) {
        if (no == null)
            return 0;
        return no.altura;
    }

    int fatorBalanceamento(No no) {
        if (no == null)
            return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    No inserir(No no, Palavra palavra) {
        if (no == null)
            return (new No(palavra));

        if (palavra.nome.compareToIgnoreCase(no.palavra.nome) < 0)
            no.esquerda = inserir(no.esquerda, palavra);
        else if (palavra.nome.compareToIgnoreCase(no.palavra.nome) > 0)
            no.direita = inserir(no.direita, palavra);
        else {
            no.palavra.contador++;
            return no;
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        int fator = fatorBalanceamento(no);

        if (fator > 1 && palavra.nome.compareToIgnoreCase(no.esquerda.palavra.nome) < 0)
            return rotacaoDireita(no);

        if (fator < -1 && palavra.nome.compareToIgnoreCase(no.direita.palavra.nome) > 0)
            return rotacaoEsquerda(no);

        if (fator > 1 && palavra.nome.compareToIgnoreCase(no.esquerda.palavra.nome) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (fator < -1 && palavra.nome.compareToIgnoreCase(no.direita.palavra.nome) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }
    private int contarPalavrasRepetidas(No no, String palavra) {
        if (no == null) {
            return 0;
        }

        int contador = 0;

        if (no.palavra.nome.equalsIgnoreCase(palavra)) {
            contador++;
        }

        contador += contarPalavrasRepetidas(no.esquerda, palavra);
        contador += contarPalavrasRepetidas(no.direita, palavra);

        return contador;
    }
    void contarPalavras(No no, ListaDupla lista) {
        if (no != null) {
            contarPalavras(no.esquerda, lista);

            if (no.palavra.contador > 1) {
                lista.inserir(no.palavra);
            } else {
                no.palavra.contador = contarPalavrasRepetidas(no, no.palavra.nome);
                if (no.palavra.contador > 1) {
                    lista.inserir(no.palavra);
                }
            }

            contarPalavras(no.direita, lista);
        }
    }
    void contarPalavras(No no) {
        contarPalavras(no, null);
    }




    void atualizarListaDupla(No no, ListaDupla lista) {
        if (no != null) {
            atualizarListaDupla(no.esquerda, lista);

            if (no.palavra.contador > 1) {
                lista.inserir(no.palavra);
            }

            atualizarListaDupla(no.direita, lista);
        }
    }

    void inOrder(No no) {
        if (no != null) {
            inOrder(no.esquerda);
            System.out.println(no.palavra.nome + " (" + no.palavra.contador + ")");
            inOrder(no.direita);
        }
    }
}
