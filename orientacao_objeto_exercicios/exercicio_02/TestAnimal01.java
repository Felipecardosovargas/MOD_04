package orientacao_objeto_exercicios.exercicio_02;

public class TestAnimal01 {
    public static void main(String[] args) {
        Cachorro snoopy = new Cachorro("Snoopy");
        snoopy.fazerSom();
        Gato garfield = new Gato("Garfield");
        garfield.fazerSom();
    }
}
