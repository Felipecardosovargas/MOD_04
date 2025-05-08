package lambdas_exercicios.exercicio_03_transformacao_strings;

import java.util.List;
import java.util.stream.Collectors;

public class Exercicio3 {
    public static List<String> converterParaMaiusculas(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}