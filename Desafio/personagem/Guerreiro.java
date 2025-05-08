package Desafio.personagem;

import Desafio.habilidade.Habilidade; // Pacote ajustado
import Desafio.personagem.enums.ClassePersonagem; // Importando Enum

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        // Nome, Classe, Vida, Mana, AtaqueBase, DefesaBase
        super(nome, ClassePersonagem.GUERREIRO, 150, 30, 18, 10);
        inicializarHabilidades();
    }

    private void inicializarHabilidades() {
        Habilidade golpePoderoso = new Habilidade(
                "Golpe Poderoso",
                "Um ataque concentrado que causa dano extra.",
                10,
                true,
                (caster, alvo) -> {
                    if (alvo == null || !alvo.estaVivo()) {
                        System.out.println("Alvo inválido para Golpe Poderoso.");
                        return false;
                    }
                    int danoBaseHabilidade = caster.getAtaqueBase() + 12; // Dano aumentado
                    int variacao = random.nextInt(7) - 3; // Variação de -3 a +3
                    int danoEfetivo = Math.max(0, danoBaseHabilidade + variacao - alvo.getDefesaAtual());
                    System.out.println(caster.getNome() + " desfere um Golpe Poderoso em " + alvo.getNome() + " causando " + danoEfetivo + " de dano!");
                    alvo.receberDano(danoEfetivo);
                    return true;
                }
        );
        aprenderHabilidade(golpePoderoso);

        Habilidade gritoDeGuerra = new Habilidade(
                "Grito de Guerra",
                "Aumenta o moral e recupera um pouco de mana.",
                5, // Custo baixo, pois é mais um buff/utility
                false,
                (caster, alvo) -> {
                    System.out.println(caster.getNome() + " solta um Grito de Guerra! Seu espírito de luta se intensifica!");
                    caster.restaurarMana(10); // Restaura um pouco de mana
                    // Poderia adicionar um buff temporário de ataque ou defesa aqui em um sistema mais complexo
                    return true;
                }
        );
        aprenderHabilidade(gritoDeGuerra);
    }

    @Override
    public String getMensagemAtaquePadrao() {
        return nome + " ("+this.classe.getNomeDisplay()+") ataca com sua espada!";
    }
}
