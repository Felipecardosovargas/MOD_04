package orientacao_objeto_exercicios.exercicio_01;

public class Carro {
    private String marca;
    private String modelo;
    private int ano;

    // Construtor
    public Carro(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    // Método que retorna a descrição
    public String getDescricao() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Ano: " + ano;
    }
}