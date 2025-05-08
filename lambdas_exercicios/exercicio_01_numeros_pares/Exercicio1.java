package lambdas_exercicios.exercicio_01_numeros_pares;

import java.util.List;
import java.util.stream.Collectors;

public class Exercicio1 {
    public static List<Integer> filtrarNumerosPares(List<Integer> numeros) {
        return numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }
}