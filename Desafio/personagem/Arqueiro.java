package Desafio.personagem;

import Desafio.habilidade.Habilidade; // Pacote ajustado
import Desafio.personagem.enums.ClassePersonagem; // Importando Enum

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        // Nome, Classe, Vida, Mana/Foco, AtaqueBase, DefesaBase
        super(nome, ClassePersonagem.ARQUEIRO, 120, 50, 16, 8);
        inicializarHabilidades();
    }

    private void inicializarHabilidades() {
        Habilidade tiroPreciso = new Habilidade(
                "Tiro Preciso",
                "Um disparo certeiro que ignora parte da defesa do alvo.",
                12,
                true,
                (caster, alvo) -> {
                    if (alvo == null || !alvo.estaVivo()) {
                        System.out.println("Alvo inválido para Tiro Preciso.");
                        return false;
                    }
                    int danoBaseHabilidade = caster.getAtaqueBase() + 8;
                    int defesaIgnorada = alvo.getDefesaAtual() / 2; // Ignora metade da defesa para este tiro
                    int danoEfetivo = Math.max(0, danoBaseHabilidade - (alvo.getDefesaAtual() - defesaIgnorada) + random.nextInt(6));
                    System.out.println(caster.getNome() + " dispara um Tiro Preciso em " + alvo.getNome() + ", causando " + danoEfetivo + " de dano perfurante!");
                    alvo.receberDano(danoEfetivo);
                    return true;
                }
        );
        aprenderHabilidade(tiroPreciso);

        Habilidade saraivadaDeFlechas = new Habilidade(
                "Saraivada de Flechas",
                "Dispara múltiplas flechas no alvo.",
                18,
                true,
                (caster, alvo) -> {
                    if (alvo == null || !alvo.estaVivo()) {
                        System.out.println("Alvo inválido para Saraivada de Flechas.");
                        return false;
                    }
                    System.out.println(caster.getNome() + " prepara uma Saraivada de Flechas!");
                    int numFlechas = 2 + random.nextInt(2); // 2 ou 3 flechas
                    int danoPorFlecha = (caster.getAtaqueBase() / 2) + random.nextInt(4);

                    for (int i = 0; i < numFlechas; i++) {
                        if (!alvo.estaVivo()) break;
                        int danoEfetivoFlecha = Math.max(0, danoPorFlecha - alvo.getDefesaAtual());
                        System.out.println("Flecha ("+(i+1)+") atinge " + alvo.getNome() + " causando " + danoEfetivoFlecha + " de dano!");
                        alvo.receberDano(danoEfetivoFlecha);
                        try { Thread.sleep(300); } catch (InterruptedException e) {} // Pequena pausa para legibilidade
                    }
                    return true;
                }
        );
        aprenderHabilidade(saraivadaDeFlechas);
    }

    @Override
    public String getMensagemAtaquePadrao() {
        return nome + " ("+this.classe.getNomeDisplay()+") dispara uma flecha com seu arco!";
    }
}
