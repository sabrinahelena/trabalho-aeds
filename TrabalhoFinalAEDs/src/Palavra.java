public class Palavra {
    String termo;
    int contador; //armazena a quant. de vezes que a palavra repete
    Palavra(String termo) {
        //Instanciamento de como o atributo "nome" sera iniciado.
        this.termo = termo;
        //Instanciamento de como o atributo "contador" sera iniciado.
        this.contador = 1;
    }
}