package principal;

import motor.OpHash;
import motor.Tratamento;

public class ExemploHashMap {

	public static void main(String[] args) {
		OpHash op = new OpHash();
		Tratamento tr = new Tratamento(op);
		
		//op.insert("Invalido ", 1, 60); // 1 - verdadeiro
		//op.insert("cansacod", 1, 60); // 0 - falso
		System.out.println("Ser� que voc� pode doar sangue?");
		System.out.println(" ");
		op.inserirRegra("gravidez ou amamenta ", "Voc� n�o pode doar sangue", 100);
		op.inserirRegra("bebeu ou gravidez ou tem_alguma_DST", "Continua sem poder doar", 90);
		op.inserirRegra("fez_tatuagem_6meses ou gravidez ou tem_alguma_DST ou amamenta", " Espere algum tempo para poder doar", 90);
		op.inserirRegra("maior_de10Anos e tem_Hepatite ou fez_tatuagem_6meses ou gravidez ou tem_alguma_DST ou amamenta", " Voc� continua impedido de doar", 90);
		op.inserirRegra("possui_malaria ou maior_de10Anos e tem_Hepatite ou fez_tatuagem_6meses ou gravidez ou tem_alguma_DST ou amamenta", " Espere algum tempo para poder doar", 90);
		op.inserirRegra("maior_de_idade e peso_maior_50", " Doe Sangue, salve vidas", 90);
		op.inserirRegra("maior_de_idade e peso_maior_50 e esta_alimentado", " Procure o posto de coleta mais pr�ximo", 90);
		
		
		tr.start();
		
		
		
	}

}
