package collections_exercicios.exercicio_02_hashset;

import java.util.HashSet;

public class Exercicio2 {
    public static void main(String[] args) {

        HashSet<String> cores = new HashSet<>();
        cores.add("verde");
        cores.add("amarelo");
        cores.add("roxo");
        cores.add("branco");
        cores.add("cinza");

        System.out.println(cores);
        System.out.println("Tamanho do nosso HashSet: "+cores.size());

        // adicionando cor duplicada
        cores.add("verde");
        System.out.println(cores);

        // HashSet não tem index
        cores.remove("verde");
        System.out.println(cores);

        String corEspecifica = "verde";
        if (cores.contains(corEspecifica)){
            System.out.println("A cor especifica é: "+corEspecifica);
        } else {
            System.out.println("A lista não contem, "+corEspecifica);
        }

        System.out.println("Lista iterada: ");
        for(String cor: cores){
            System.out.println(cor);
        }
    }
}
