package exception_exercicios.exercicio_02_multiplas_excecoes;

public class Exercicio2 {
    public static void main(String[] args) {
        String numeroStr = "teste"; // poderia tambem testar com null

        try {
            int numero = Integer.parseInt(numeroStr);
            System.out.println("Número convertido: " + numero);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Formato inválido. Não é possível converter a string para número.");
        } catch (NullPointerException e) {
            System.out.println("Erro: A string está nula.");
        }
    }
}