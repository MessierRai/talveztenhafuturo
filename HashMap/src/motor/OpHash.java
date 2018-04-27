package motor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OpHash {
	
	Map<String, Integer> hm = new HashMap<String, Integer>();  // armazena variavel e valor verdade
	Map<String, Integer> fchm = new HashMap<String, Integer>(); // armazena variavel e fator de confianca
	Map<String, String> rghm = new HashMap<String, String>(); // armazena regra e sua conclusao
	
	
	public void insertRule(String chave, String conclusao) {
		rghm.put(chave, conclusao);
	}
	
	public void insert(String chave, int valor, int fatorConfianca) {
		hm.put(chave, valor);
		fchm.put(chave, fatorConfianca);
	}
	
	public void remove(String chave) {
		hm.remove(chave);
		fchm.remove(chave);
	}
	
	public boolean isEmpty() {
		return hm.isEmpty();
		
	}
	
	public Integer getValue(String chave) {
		return hm.get(chave);
	}
	
	public ArrayList<String> getKeys(Integer valor) { // baseado em um valor retorna um array com as chaves que contem aquele valor
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
	
	public ArrayList<String> getRules() {
		ArrayList<String> rules = new ArrayList<String>();
		Collection<String> aRules = rghm.keySet();
		for(String i : aRules) {
			rules.add(i);
		}
		return rules;
	}

	public boolean exist(String clausula) {
		return hm.containsKey(clausula);
	}

}
