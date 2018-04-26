package principal;

import motor.OpHash;
import motor.Tratamento;

public class ExemploHashMap {

	public static void main(String[] args) {
		OpHash op = new OpHash();
		Tratamento tr = new Tratamento(op);
		
		op.insert("Invalido ", 1); // 1 - verdadeiro
		op.insert("B", 0); // 0 - falso
		op.insert("C", 0);
		op.insert("V", 1);
		op.insert("R", 0);
		op.insert("M", 1);
		op.insert("R", 1);
		op.insert("Z", 1);
		op.insert("A", 1);
		op.insert("O", 0);
		op.insert("E", 1);
		
		//System.out.println(op.getKeys(0));
		
		String a = "M e O ou F e G";
		
		System.out.println(tr.treatment(a));
		
	}

}
