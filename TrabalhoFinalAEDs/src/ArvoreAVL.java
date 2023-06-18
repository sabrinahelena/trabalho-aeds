
public class ArvoreAVL {
    //Um objeto do tipo No
    No raiz;

    //responsável por verificar se o no passado como parâmetro é nulo
    int Altura(No no) {
        //Se for null não há mais nós na árvore, sendo a altura 0
        if (no == null)
            return -1;
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

        return x; //se torna a nova raiz
    }

    No RotacaoEsq(No x) {
        No y = x.direita;
        No z = y.esquerda;
        y.esquerda = x;
        x.direita = z;

        x.altura = Math.max(Altura(x.esquerda), Altura(x.direita)) + 1;
        y.altura = Math.max(Altura(y.esquerda), Altura(y.direita)) + 1;

        return y; //se torna a nova raiz
    }

    No inserir(No no, Palavra palavra) {
        if (no == null)
            return (new No(palavra));
        /*
        Compara o termo passado na palavra do parâmetro com o termo que já está dentro da palavra do nó
        Se for menor, insere à esquerda do nó, e se for maior à direita, seguindo a regra de árvore.
         */

        int comparacao = palavra.termo.compareToIgnoreCase(no.palavra.termo);
        if (comparacao < 0)
            no.esquerda = inserir(no.esquerda, palavra);
        else if (comparacao > 0)
            no.direita = inserir(no.direita, palavra);
        else {
            no.palavra.contador++; // Se não, aumenta o contador para mostrar que é um termo repetido
            return no;
        }

        no.altura = 1 + Math.max(Altura(no.esquerda), Altura(no.direita));

        int fator = FatorB(no);

        if (fator > 1 && palavra.termo.compareToIgnoreCase(no.esquerda.palavra.termo) < 0)
            return RotacaoDir(no);

        if (fator < -1 && palavra.termo.compareToIgnoreCase(no.direita.palavra.termo) > 0)
            return RotacaoEsq(no);

        if (fator > 1 && palavra.termo.compareToIgnoreCase(no.esquerda.palavra.termo) > 0) {
            no.esquerda = RotacaoEsq(no.esquerda);
            return RotacaoDir(no);
        }

        if (fator < -1 && palavra.termo.compareToIgnoreCase(no.direita.palavra.termo) < 0) {
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

        if (no.palavra.termo.equalsIgnoreCase(palavra)) {
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
                no.palavra.contador = contarPalavrasRepetidas(no, no.palavra.termo);
                if (no.palavra.contador > 1) {
                    lista.AdicionarPalavra(no.palavra);
                }
            }

            contarPalavras(no.direita, lista);
        }
    }
    void AttListaDupla(No no, ListaDupla lista) {
        if (no != null) {
            AttListaDupla(no.esquerda, lista);

            if (no.palavra.contador >= 1) {
                lista.AdicionarPalavra(no.palavra);
            }

            AttListaDupla(no.direita, lista);
        }
    }

    void ImprimirEmOrdem(No no) {
        if (no != null) {
            ImprimirEmOrdem(no.esquerda);
            System.out.println("{ " + no.palavra.contador + " } - " + no.palavra.termo);
            ImprimirEmOrdem(no.direita);
        }
    }
}