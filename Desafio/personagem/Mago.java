package Desafio.personagem;

import Desafio.habilidade.Habilidade; // Pacote ajustado
import Desafio.personagem.enums.ClassePersonagem; // Importando Enum

public class Mago extends Personagem {
    public Mago(String nome) {
        // Nome, Classe, Vida, Mana, AtaqueBase, DefesaBase
        super(nome, ClassePersonagem.MAGO, 100, 80, 10, 6);
        inicializarHabilidades();
    }

    private void inicializarHabilidades() {
        Habilidade bolaDeFogo = new Habilidade(
                "Bola de Fogo",
                "Lança uma bola de fogo que causa dano mágico.",
                15,
                true,
                (caster, alvo) -> {
                    if (alvo == null || !alvo.estaVivo()) {
                        System.out.println("Alvo inválido para Bola de Fogo.");
                        return false;
                    }
                    int danoMagico = 25 + caster.getManaMaxima() / 8 + random.nextInt(12);
                    int defesaConsiderada = Math.max(0, alvo.getDefesaBase() / 2); // Magia ignora parte da defesa
                    int danoEfetivo = Math.max(0, danoMagico - defesaConsiderada);
                    System.out.println(caster.getNome() + " lança uma Bola de Fogo em " + alvo.getNome() + " causando " + danoEfetivo + " de dano flamejante!");
                    alvo.receberDano(danoEfetivo);
                    return true;
                }
        );
        aprenderHabilidade(bolaDeFogo);

        Habilidade curaLeve = new Habilidade(
                "Cura Leve",
                "Restaura uma pequena quantidade de vida do conjurador.",
                20,
                false,
                (caster, alvo) -> { // alvo é o próprio caster
                    int cura = 30 + random.nextInt(16); // 30-45 de cura
                    caster.curar(cura);
                    // A mensagem de cura já está no método caster.curar()
                    return true;
                }
        );
        aprenderHabilidade(curaLeve);
    }

    @Override
    public String getMensagemAtaquePadrao() {
        return nome + " ("+this.classe.getNomeDisplay()+") ataca com seu cajado!";
    }
}
