package motor;

import java.util.Scanner;

public class Tratamento {
	private boolean temp;
	private boolean temp2;
	private boolean temp3;
	private int sinal;
	private int numClausulas;
	
	private OpHash hm;
	Scanner sc = new Scanner(System.in);
	
	public Tratamento(OpHash hm) {
		this.hm = hm;
	}
	
	public void noExist(String clausula)  {
		System.out.println("Qual o valor verdade de " + clausula + "? (1 = vdd; 0 = falso)");
		int vv = sc.nextInt();
		System.out.println("Qual o fator de confiança de " + clausula + "? (0 -100)");
		int vb = sc.nextInt();
		hm.insert(clausula, vv, vb);
	}
	
	
	public Integer verifyExistence(String clausula) { //verifica se já existe no hashmap
		if(hm.exist(clausula)) {
			return hm.getValue(clausula);
		}else if(clausula.equals("e") || clausula.equals("ou")) {
			return -1;
		}else {
			noExist(clausula);
			return verifyExistence(clausula);
		}
	}
	
	public boolean valueCl(String c) { //verifica se, quando existe no hasmap, qual o valor verdade da coisas
		
			if(verifyExistence(c) == 1) {
				if(this.numClausulas == 1) {
					this.temp = true;
				}if(this.numClausulas == 2) {
					this.temp2 = true;
				}
				//System.out.println(c + this.temp);
			}else {
				if(this.numClausulas == 1) {
					this.temp = false;
				}if(this.numClausulas == 2) {
					this.temp2 = false;
				}
				
			}
		
		return this.temp;
	}
	
	public int signalCl(String c) { //verifica se é "E" ou "OU"
		if(c.equals("e")) {
			this.sinal = 1;
			//System.out.println(c + this.sinal);
		}
		if(c.equals("ou")) {
			this.sinal = 0;
			//System.out.println(c + this.sinal);
		}
		return this.sinal;
	}
	
	public boolean treatment(String regra) {
		String[] b = regra.split(" ");
		
		for(String i : b) {
			if(verifyExistence(i) == -1) {
				signalCl(i);
			}else {
				this.numClausulas++;
				valueCl(i);
				if(this.numClausulas == 2) {
					isTrue();
					this.temp = this.temp3;
					this.numClausulas = 1;
				}
			}
		}
		this.numClausulas = 0; // se não zerar agora, na proxima iteração de regras vai ter um ruido aqui que vai atrapalhar o resultado final.
		return this.temp3;
	}
	
	public void isTrue() { // faz o calculo da verdade dependendo do sinal entre as clausulas
		if(this.sinal == 1) {
			this.temp3 = this.temp && this.temp2;
		}if(this.sinal == 0) {
			this.temp3 = this.temp || this.temp2;
		}
	}
	
	public String treatmentRule(String regra) {
		boolean res = treatment(regra);
		if(res) {
			return hm.rghm.get(regra);
		}else {
			return "(" + regra + ") - não se encaixa";
		}
	}
	
	public void start() {
		for(String i : hm.getRules()) {
			System.out.println("###### " + treatmentRule(i) + " #########");
		}
	}
		
}
