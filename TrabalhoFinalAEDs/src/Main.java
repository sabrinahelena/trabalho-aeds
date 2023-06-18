import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        ListaDupla listaDupla = new ListaDupla();

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/Sabrina/Desktop/Programação/AEDS-II/trabalho-aeds/TrabalhoFinalAEDs/src/trabalho.txt"));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

                for (String palavra : palavras) {
                    palavra = palavra.toLowerCase();
                    arvoreAVL.raiz = arvoreAVL.inserir(arvoreAVL.raiz, new Palavra(palavra));
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        arvoreAVL.ImprimirEmOrdem(arvoreAVL.raiz);
        arvoreAVL.contarPalavras(arvoreAVL.raiz, listaDupla);
        arvoreAVL.AttListaDupla(arvoreAVL.raiz, listaDupla);

        System.out.println("Lista dupla");
        listaDupla.Imprimir();
    }
}