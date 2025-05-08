package Desafio.habilidade;

import Desafio.personagem.Personagem;

/**
 * Interface funcional para definir o efeito de uma habilidade.
 * Uma expressão lambda pode ser usada para implementar o método aplicar.
 * Movido para o pacote Desafio.habilidade
 */
@FunctionalInterface
public interface EfeitoHabilidade {
    /**
     * Aplica o efeito da habilidade.
     * @param caster O personagem que está lançando a habilidade.
     * @param alvo O personagem que é o alvo da habilidade (pode ser o próprio caster).
     * @return true se a habilidade foi aplicada com sucesso, false caso contrário (ex: sem mana).
     */
    boolean aplicar(Personagem caster, Personagem alvo);
}
