package Desafio.personagem;

import Desafio.item.Item; // Pacote ajustado
import Desafio.item.PocaoDeCura; // Pacote ajustado
import Desafio.habilidade.Habilidade; // Pacote ajustado
import Desafio.personagem.enums.ClassePersonagem; // Importando Enum

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Personagem {
    protected String nome;
    protected ClassePersonagem classe; // Novo atributo para a classe do personagem
    protected int vida;
    protected int vidaMaxima;
    protected int mana;
    protected int manaMaxima;
    protected int ataqueBase;
    protected int defesaBase;
    protected boolean defendendo;
    protected List<Item> inventario;
    protected List<Habilidade> habilidades;

    protected static final Random random = new Random();

    /**
     * Construtor da classe Personagem.
     * @param nome Nome do personagem.
     * @param classe Classe do personagem (usando Enum).
     * @param vida Vida máxima e inicial.
     * @param mana Mana máxima e inicial.
     * @param ataqueBase Valor de ataque base.
     * @param defesa Valor de defesa base.
     */
    public Personagem(String nome, ClassePersonagem classe, int vida, int mana, int ataqueBase, int defesa) {
        this.nome = nome;
        this.classe = classe; // Define a classe
        this.vidaMaxima = vida;
        this.vida = vida;
        this.manaMaxima = mana;
        this.mana = mana;
        this.ataqueBase = ataqueBase;
        this.defesaBase = defesa;
        this.defendendo = false;
        this.inventario = new ArrayList<>();
        this.habilidades = new ArrayList<>();
        inicializarItensPadrao();
    }

    // Getters
    public String getNome() { return nome; }
    public ClassePersonagem getClasse() { return classe; } // Getter para a classe
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getMana() { return mana; }
    public int getManaMaxima() { return manaMaxima; }
    public int getAtaqueBase() { return ataqueBase; }
    public int getDefesaBase() { return defesaBase; }
    public boolean estaVivo() { return vida > 0; }
    public List<Item> getInventario() { return inventario; }
    public List<Habilidade> getHabilidades() { return habilidades; }
    public boolean isDefendendo() { return defendendo; }


    protected void inicializarItensPadrao() {
        if (!(this instanceof Inimigo)) {
            adicionarItensAoInventario(new PocaoDeCura(), new PocaoDeCura()); // Ex: 2 poções iniciais
        }
    }

    public void adicionarItensAoInventario(Item... itensParaAdicionar) {
        for (Item item : itensParaAdicionar) {
            this.inventario.add(item);
            System.out.println(item.getNome() + " ("+ item.getTipo().getNomeDisplay() +") adicionado ao inventário de " + this.nome + ".");
        }
    }

    public void adicionarItemAoInventario(Item item) {
        this.inventario.add(item);
        System.out.println(item.getNome() + " ("+ item.getTipo().getNomeDisplay() +") adicionado ao inventário de " + this.nome + ".");
    }

    public int getDefesaAtual() {
        return defendendo ? defesaBase * 2 : defesaBase;
    }

    public void atacar(Personagem alvo) {
        this.defendendo = false;
        System.out.println(getMensagemAtaquePadrao());

        int variacaoAtaque = this.ataqueBase > 0 ? random.nextInt(Math.max(1, this.ataqueBase / 4) + 1) - (this.ataqueBase / 8) : 0;
        int ataqueEfetivo = this.ataqueBase + variacaoAtaque;
        int danoCausado = Math.max(0, ataqueEfetivo - alvo.getDefesaAtual());

        String classeDisplay = this.classe != null ? " (" + this.classe.getNomeDisplay() + ")" : "";
        System.out.println(this.nome + classeDisplay + " atacou " + alvo.getNome() + " e causou " + danoCausado + " de dano!");
        alvo.receberDano(danoCausado);
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        System.out.println(this.nome + " recebeu " + dano + " de dano. Vida restante: " + Math.max(0, this.vida));
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(this.nome + " foi derrotado!");
        }
    }

    public void curar(int quantidade) {
        int vidaAntes = this.vida;
        this.vida += quantidade;
        if (this.vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        }
        System.out.println(this.nome + " recuperou " + (this.vida - vidaAntes) + " de vida. Vida atual: " + this.vida);
    }

    public void consumirMana(int quantidade) {
        this.mana -= quantidade;
        if (this.mana < 0) this.mana = 0;
    }

    public void restaurarMana(int quantidade) {
        int manaAntes = this.mana;
        this.mana += quantidade;
        if (this.mana > this.manaMaxima) {
            this.mana = this.manaMaxima;
        }
        System.out.println(this.nome + " recuperou " + (this.mana - manaAntes) + " de mana. Mana atual: " + this.mana);
    }

    public void defender() {
        this.defendendo = true;
        System.out.println(this.nome + " está defendendo! Defesa aumentada para a próxima rodada.");
    }

    public void resetarEstadoDefesa() {
        this.defendendo = false;
    }

    public void usarItemDoInventario(int indiceItem) {
        this.defendendo = false;
        if (indiceItem >= 0 && indiceItem < inventario.size()) {
            Item itemEscolhido = inventario.get(indiceItem);
            System.out.println(this.nome + " tenta usar " + itemEscolhido.getNome() + ".");
            itemEscolhido.usar(this);
            if (itemEscolhido.isConsumivel()) {
                inventario.remove(indiceItem);
                System.out.println(itemEscolhido.getNome() + " foi consumido.");
            }
        } else {
            System.out.println("Índice de item inválido!");
        }
    }

    public void listarItensParaUso() {
        System.out.println("--- Seu Inventário ("+this.nome+") ---");
        if (inventario.isEmpty()) {
            System.out.println("Nenhum item disponível.");
            return;
        }
        for (int i = 0; i < inventario.size(); i++) {
            Item item = inventario.get(i);
            System.out.println((i + 1) + ". " + item.getNome() + " [" + item.getTipo().getNomeDisplay() + "] (" + item.getDescricao() + ")");
        }
        System.out.println("----------------------");
    }

    public void exibirInventarioCompleto() {
        System.out.println("\n--- Inventário Detalhado de " + this.nome + " ---");
        if (inventario.isEmpty()) {
            System.out.println("Nenhum item.");
        } else {
            for (Item item : inventario) {
                System.out.println("- " + item.getNome() + " [" + item.getTipo().getNomeDisplay() + "] (" + item.getDescricao() + ")");
            }
        }
        System.out.println("------------------------------------");
    }

    public void aprenderHabilidade(Habilidade habilidade) {
        this.habilidades.add(habilidade);
        System.out.println(this.nome + " aprendeu a habilidade: " + habilidade.getNome() + "!");
    }

    public boolean lancarHabilidade(int indiceHabilidade, Personagem alvo) {
        this.defendendo = false;
        if (indiceHabilidade >= 0 && indiceHabilidade < habilidades.size()) {
            Habilidade habilidadeEscolhida = habilidades.get(indiceHabilidade);
            if (habilidadeEscolhida.isRequerAlvoOponente() && (alvo == null || alvo == this)) {
                System.out.println(habilidadeEscolhida.getNome() + " requer um oponente como alvo.");
                return false;
            }
            return habilidadeEscolhida.executar(this, alvo);
        } else {
            System.out.println("Índice de habilidade inválido!");
            return false;
        }
    }

    public void listarHabilidadesParaUso() {
        System.out.println("--- Habilidades de " + this.nome + " ("+this.classe.getNomeDisplay()+") ---");
        if (habilidades.isEmpty()) {
            System.out.println("Nenhuma habilidade aprendida.");
            return;
        }
        for (int i = 0; i < habilidades.size(); i++) {
            Habilidade hab = habilidades.get(i);
            System.out.println((i + 1) + ". " + hab.getNome() + " (Custo: " + hab.getCustoMana() + " Mana) - " + hab.getDescricao());
        }
        System.out.println("------------------------------------");
    }

    public abstract String getMensagemAtaquePadrao();
}