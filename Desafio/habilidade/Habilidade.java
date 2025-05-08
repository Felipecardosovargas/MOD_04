package Desafio.habilidade;

import Desafio.personagem.Personagem;

/**
 * Representa uma habilidade que um personagem pode usar.
 * Contém nome, descrição, custo de mana e o efeito da habilidade.
 * Movido para o pacote Desafio.habilidade
 */
public class Habilidade {
    private String nome;
    private String descricao;
    private int custoMana;
    private EfeitoHabilidade efeito;
    private boolean requerAlvoOponente;

    public Habilidade(String nome, String descricao, int custoMana, boolean requerAlvoOponente, EfeitoHabilidade efeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.custoMana = custoMana;
        this.efeito = efeito;
        this.requerAlvoOponente = requerAlvoOponente;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getCustoMana() { return custoMana; }
    public boolean isRequerAlvoOponente() { return requerAlvoOponente; }

    public boolean executar(Personagem caster, Personagem alvo) {
        if (caster.getMana() >= custoMana) {
            caster.consumirMana(custoMana);
            System.out.println(caster.getNome() + " usa " + nome + "!");
            return efeito.aplicar(caster, alvo);
        } else {
            System.out.println(caster.getNome() + " tentou usar " + nome + ", mas não tem mana suficiente ("+caster.getMana()+"/"+custoMana+").");
            return false;
        }
    }
}
