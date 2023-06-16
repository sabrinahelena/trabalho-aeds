import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        ListaDupla lista = new ListaDupla();


        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/willi/IdeaProjects/TP/src/TP.txt"));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

                for (String palavra : palavras) {
                    palavra = palavra.toLowerCase();
                    arvore.raiz = arvore.inserir(arvore.raiz, new Palavra(palavra));
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        arvore.inOrder(arvore.raiz);

        arvore.contarPalavras(arvore.raiz, lista);
        arvore.atualizarListaDupla(arvore.raiz, lista);

        System.out.println("--- Lista Dupla Encadeada ---");
        lista.Imprimir();
    }
}