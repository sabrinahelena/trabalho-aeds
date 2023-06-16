public class NoListaDupla {
    Palavra palavra;
    NoListaDupla anterior;
    NoListaDupla proximo;
    NoListaDupla(Palavra palavra) {
        this.palavra = palavra;
        this.anterior = null;
        this.proximo = null;
    }
}
