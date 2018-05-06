package motor;

import java.util.Scanner;

public class Tratamento {
	private boolean temp;
	private boolean temp2;
	private boolean temp3;
	private int sinal;
	private int numClausulas;
	private float fcTemp1;
	private float fcTemp2;
	private float fcTemp3;
	
	private OpHash hm;
	Scanner sc = new Scanner(System.in);
	
	public Tratamento(OpHash hm) {
		this.hm = hm;
	}
	
	public void naoExiste(String clausula)  { // se não existe no HashMap, pega o valor verdade e o FC da clausula
		System.out.println("Qual o valor verdade de " + clausula + "? (1 = vdd; 0 = falso)");
		int vv = sc.nextInt();
		System.out.println("Qual o fator de confiança de " + clausula + "? (0 -100)");
		int vb = sc.nextInt();
		hm.inserir(clausula, vv, vb);
	}
	
	
	public Integer verificaExistencia(String clausula) { //verifica se já existe no hashmap
		if(hm.existe(clausula)) {
			return hm.getValor(clausula);
		}else if(clausula.equals("e") || clausula.equals("ou")) {
			return -1;
		}else {
			naoExiste(clausula);
			return verificaExistencia(clausula);
		}
	}
	
	public boolean valorClausula(String c) { //verifica se, quando existe no hasmap, qual o valor verdade da coisas
		
			if(verificaExistencia(c) == 1) { //se o valor verdade da clausula for verdade
				if(this.numClausulas == 1) {
					this.temp = true;
					this.fcTemp1 = hm.getValueFC(c);
				}if(this.numClausulas == 2) {
					this.temp2 = true;
					this.fcTemp2 = hm.getValueFC(c);
				}
				//System.out.println(c + this.temp);
			}else {
				if(this.numClausulas == 1) {
					this.temp = false;
					this.fcTemp1 = hm.getValueFC(c);
				}if(this.numClausulas == 2) {
					this.temp2 = false;
					this.fcTemp2 = hm.getValueFC(c);
				}
				
			}
		
		return this.temp;
	}
	
	public int sinalClausula(String c) { //verifica se é "E" ou "OU"
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
	
	public boolean tratamento(String regra) {
		String[] b = regra.split(" ");
		
		for(String i : b) {
			if(verificaExistencia(i) == -1) { // se o VerifyExistence retorna -1 é pq é um conectivo lógico
				sinalClausula(i);
			}else {
				this.numClausulas++;
				valorClausula(i);
				if(this.numClausulas == 2) {
					eVerdade();
					this.temp = this.temp3;
					this.fcTemp1 = this.fcTemp3;
					this.numClausulas = 1;
				}
			}
		}
		this.numClausulas = 0; // se não zerar agora, na proxima iteração de regras vai ter um ruido aqui que vai atrapalhar o resultado final.
		return this.temp3;
	}
	
	public void eVerdade() { // faz o calculo da verdade dependendo do sinal entre as clausulas
		if(this.sinal == 1) {
			this.temp3 = this.temp && this.temp2;
			this.fcTemp3 = calculaFC(this.sinal, this.fcTemp1, this.fcTemp2);
		}if(this.sinal == 0) {
			this.temp3 = this.temp || this.temp2;
			this.fcTemp3 = calculaFC(this.sinal, this.fcTemp1, this.fcTemp2);
		}
	}
	
	public String tratamentoRegra(String regra) { // verifica o valor verdade final da regra, se é verdade retona a conclusão dela
													// se não, retorna dizendo que a regra não se encaixa
		boolean res = tratamento(regra); //pega o valor verdade final da regra
		if(res) {
			return hm.rghm.get(regra) + " - FC: " + calculaFCGeral(fcTemp3, regra) + "%" ;
		}else {
			return "(" + regra + ") - não se encaixa" + " - FC: " + calculaFCGeral(fcTemp3, regra) + "%";
		}
	}
	
	public float calculaFC(int sinal, float fc1, float fc2) { // calcula o fator de confianca baseada no sinal
		float fc = 0;
		if(sinal == 1) {
			fc = (fc1 / 100) * (fc2 / 100); //divide por 100 porque precisava de um numero do tipo "0,8" ao invés de 80%
		}else if(sinal == 0) {
			fc = ((fc1 / 100) + (fc2 / 100)) - ((fc1 / 100) * (fc2 / 100));
		}
		fc = fc * 100;
		return fc;
	}
	
	public float calculaFCGeral(float fcCondicao, String regra) {
		return (fcCondicao * hm.getFCRegra(regra)) / 100;
		
	}
	
	public void start() {
		for(String i : hm.getRegras()) { // itera sobre cada regra armazenada no HashMap
			System.out.println("###### " + tratamentoRegra(i) + " #########");
		}
	}
		
}
