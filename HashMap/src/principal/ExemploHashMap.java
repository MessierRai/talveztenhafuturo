package principal;

import motor.OpHash;
import motor.Tratamento;

public class ExemploHashMap {

	public static void main(String[] args) {
		OpHash op = new OpHash();
		Tratamento tr = new Tratamento(op);
		
		//op.insert("Invalido ", 1, 60); // 1 - verdadeiro
		//op.insert("cansacod", 1, 60); // 0 - falso
		
		
		//System.out.println(op.getKeys(0));
		
		op.insertRule("cansaco e sono", "dormir");
		op.insertRule("sono e fome", "Deus lhe ajude nesta hora dificil");;
		
		tr.start();
		
		
		
	}

}
