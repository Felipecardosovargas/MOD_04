package lambdas_exercicios.exercicio_04_soma_numeros;

import java.util.List;

public class Exercicio4 {
    public static int somarNumeros(List<Integer> numeros) {
        return numeros.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}