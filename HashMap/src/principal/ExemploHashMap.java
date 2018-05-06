package principal;

import motor.OpHash;
import motor.Tratamento;

public class ExemploHashMap {

	public static void main(String[] args) {
		OpHash op = new OpHash();
		Tratamento tr = new Tratamento(op);
		
		//op.insert("Invalido ", 1, 60); // 1 - verdadeiro
		//op.insert("cansacod", 1, 60); // 0 - falso
		
		op.inserirRegra("cansaco e sono", "dormir", 100);
		op.inserirRegra("sono e fome", "Deus lhe ajude nesta hora dificil", 90);
		
		tr.start();
		
		
		
	}

}
