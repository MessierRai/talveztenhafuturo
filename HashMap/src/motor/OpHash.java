package motor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OpHash {
	
	Map<String, Integer> hm = new HashMap<String, Integer>();
	
	
	public void insert(String chave, int valor) {
		hm.put(chave, valor);
	}
	
	public void remove(String chave) {
		hm.remove(chave);
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
	
	public boolean exist(String clausula) {
		return hm.containsKey(clausula);
	}

}
