
public class ArvoreAVL {
    //Um objeto do tipo No
    No raiz;

    //responsável por verificar se o no passado como parâmetro é nulo
    int Altura(No no) {
        //Se for null não há mais nós na árvore, sendo a altura 0
        if (no == null)
            return 0;
        //niveis
        return no.altura;
    }
    /*É responsável por calcular o fator balanceamento (definido como a diferença
     * entre a altura da subárvore esquerda e a altura da subárvore direita de nó)
     * de um nó na árvore, esse metodo verifica se a ávore está balanceada
     */
    int FatorB(No no) {
        //se for nulo significa que não há sub-árvore nesse lado
        if (no == null)
            return 0;
	        /*Calcula a diferença entre a altura da subárvore esquerda e a altura da subárvore direita do nó,
	        chamando o método altura (verificar se a árvore esta balanceada)*/
        return Altura(no.esquerda) - Altura(no.direita);
    }

    /*Realiza uma rotação para a direita em torno de um nó específico na árvore AVL
    balancear quando a direita não estiver balanceada*/
    No RotacaoDir(No y) {
        //Cria uma refer// ncia para o filho esquerdo do Nó y, que recebe x
        No x = y.esquerda;
        //cria o filho direito de x
        No z = x.direita;
        //Define y como filho direito de x, tornando o x o novo nó raiz, no lugar de y
        x.direita = y;
        //Define T2 como filho esquerdo de y, que era o antigo filho de x
        y.esquerda = z;

        y.altura = Math.max(Altura(y.esquerda), Altura(y.direita)) + 1;
        x.altura = Math.max(Altura(x.esquerda), Altura(x.direita)) + 1;

        return x;
    }

    No RotacaoEsq(No x) {
        No y = x.direita;
        No T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(Altura(x.esquerda), Altura(x.direita)) + 1;
        y.altura = Math.max(Altura(y.esquerda), Altura(y.direita)) + 1;

        return y;
    }

    No inserir(No no, Palavra palavra) {
        if (no == null)
            return (new No(palavra));

        if (palavra.palavra.compareToIgnoreCase(no.palavra.palavra) < 0)
            no.esquerda = inserir(no.esquerda, palavra);
        else if (palavra.palavra.compareToIgnoreCase(no.palavra.palavra) > 0)
            no.direita = inserir(no.direita, palavra);
        else {
            no.palavra.contador++;
            return no;
        }

        no.altura = 1 + Math.max(Altura(no.esquerda), Altura(no.direita));

        int fator = FatorB(no);

        if (fator > 1 && palavra.palavra.compareToIgnoreCase(no.esquerda.palavra.palavra) < 0)
            return RotacaoDir(no);

        if (fator < -1 && palavra.palavra.compareToIgnoreCase(no.direita.palavra.palavra) > 0)
            return RotacaoEsq(no);

        if (fator > 1 && palavra.palavra.compareToIgnoreCase(no.esquerda.palavra.palavra) > 0) {
            no.esquerda = RotacaoEsq(no.esquerda);
            return RotacaoDir(no);
        }

        if (fator < -1 && palavra.palavra.compareToIgnoreCase(no.direita.palavra.palavra) < 0) {
            no.direita = RotacaoDir(no.direita);
            return RotacaoEsq(no);
        }

        return no;
    }
    private int contarPalavrasRepetidas(No no, String palavra) {
        if (no == null) {
            return 0;
        }

        int contador = 0;

        if (no.palavra.palavra.equalsIgnoreCase(palavra)) {
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
                lista.AdicionarPalavra(no.palavra);
            } else {
                no.palavra.contador = contarPalavrasRepetidas(no, no.palavra.palavra);
                if (no.palavra.contador > 1) {
                    lista.AdicionarPalavra(no.palavra);
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

            if (no.palavra.contador >= 1) {
                lista.AdicionarPalavra(no.palavra);
            }

            atualizarListaDupla(no.direita, lista);
        }
    }

    void inOrder(No no) {
        if (no != null) {
            inOrder(no.esquerda);
            System.out.println(no.palavra.palavra + " (" + no.palavra.contador + ")");
            inOrder(no.direita);
        }
    }
}