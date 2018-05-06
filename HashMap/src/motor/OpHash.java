package motor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OpHash {
	
	Map<String, Integer> hm = new HashMap<String, Integer>();  // armazena variavel e valor verdade //hm = hasmap
	Map<String, Integer> fchm = new HashMap<String, Integer>(); // armazena variavel e fator de confianca // fchm = fator confianca hasmap
	Map<String, String> rghm = new HashMap<String, String>(); // armazena regra e sua conclusao // rghm = regras hashmap
	
	
	public void inserirRegra(String chave, String conclusao) {
		rghm.put(chave, conclusao);
	}
	
	public void inserir(String chave, int valor, int fatorConfianca) {
		hm.put(chave, valor);
		fchm.put(chave, fatorConfianca);
	}
	
	public void remover(String chave) {
		hm.remove(chave);
		fchm.remove(chave);
	}
	
	public boolean estaVazio() {
		return hm.isEmpty();
		
	}
	
	public Integer getValor(String chave) {
		return hm.get(chave);
	}
	
	public Integer getValueFC(String chave) {
		return fchm.get(chave);
	}
	
	public ArrayList<String> getChave(Integer valor) { // baseado em um valor retorna um array com as chaves que contem aquele valor
		ArrayList<String> ah = new ArrayList<String>();
		Collection<String> am = hm.keySet();
		for (String i : am) {
			if(hm.get(i).equals(valor)) {
				ah.add(i);
			}
			
		}if(ah.isEmpty()) {
			ah.add("Não há chaves para este valor");
			return ah;
		}else {
			return ah;
		}
		
	}
	
	public ArrayList<String> getRegras() {
		ArrayList<String> rules = new ArrayList<String>();
		Collection<String> aRules = rghm.keySet();
		for(String i : aRules) {
			rules.add(i);
		}
		return rules;
	}

	public boolean existe(String clausula) {
		return hm.containsKey(clausula);
	}

}
