package orientacao_objeto_exercicios.exercicio_03;

public class TestForma01 {
    public static void main(String[] args) {
        Circulo circulo = new Circulo(3);
        System.out.println("A área é: " + circulo.calcularArea());

        Retangulo retangulo = new Retangulo(7, 2);
        System.out.println("A área é: " + retangulo.calcularArea());
    }
}
