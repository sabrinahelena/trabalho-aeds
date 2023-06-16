public class ListaDupla {
    private NoListaDupla inicio;
    private NoListaDupla fim;

    void AdicionarPalavra(Palavra palavra) {
        NoListaDupla noNovo = new NoListaDupla(palavra); //Cria um novo nó que contém a palavra passada no parâmetro
        if (inicio == null) {
            inicio = fim = noNovo; //Se início for nulo, está vazio então só inserir.
        } else {
            NoListaDupla atual = inicio; //Se não, pegamos o inicio
            while (atual != null) {
                if (atual.palavra.palavra.equals(palavra.palavra)) {//compara a palavra passada com a que está no nó atual
                    return;
                }
                atual = atual.proximo; //vai passando pra frente
            }

            atual = inicio;

            /*
            Nesse loop, o código verifica duas condições: se o nó atual não é nulo
            e se o contador da palavra a ser adicionada (palavra.contador)
            é menor que o contador da palavra no nó atual.
            Durante cada iteração desse segundo loop, o nó atual é atualizado para apontar para o próximo nó da lista.
            Seguindo a atividade, inserimos o menor no fim, e o maior no início, então
            o contador do atual (inicio e .proximo) tem que ser menor que o que eu estou passando
            para inserir no inicio.
             */
            while (atual != null && palavra.contador < atual.palavra.contador)
                atual = atual.proximo;

            if (atual == null) { //significa que o que vamos adicionar tem uma ocorrencia menor
                noNovo.anterior = fim; //coloca o que era o ultimo agora antes do noNovo
                fim.proximo = noNovo; //insere depois do que era o ultimo
                fim = noNovo;//insere no fim
            } else if (atual == inicio) {
                noNovo.proximo = inicio;
                inicio.anterior = noNovo; //insere no início se o contador for o maior, tem mais ocorrencias
                inicio = noNovo;
            } else {
                noNovo.proximo = atual;
                noNovo.anterior = atual.anterior;
                atual.anterior.proximo = noNovo;
                atual.anterior = noNovo;
            }
        }
    }
    void Imprimir() {
        NoListaDupla atual = inicio;
        while (atual != null) {
            System.out.println("Palavra:" + " " + atual.palavra.palavra + "Repete: " + atual.palavra.contador + "vezes");
            atual = atual.proximo;
        }
    }
}
