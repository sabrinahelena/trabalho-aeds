public class Palavra {
    //Declaração do atributo "nome" que armazena uma palavra encontrada no arquivo.
    String nome;
    //Contador utilizando o Integer (transforma string em numero inteiro) para armazenar a quantidade de vezes que a
    //palavra guardada no atributo "nome" aparece no arquivo txt.
    Integer contador;

    Palavra(String nome) {
        //Instanciamento de como o atributo "nome" sera iniciado.
        this.nome = nome;
        //Instanciamento de como o atributo "contador" sera iniciado.
        this.contador = 1;
    }
}
