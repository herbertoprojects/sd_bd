package bd_java_code;

import java.util.Scanner;



public abstract class textEditor {

	public static String limitaString(String texto,int maxCaracteres){
		String vazio = " ";
		for (int i = 0;i<maxCaracteres;i++){
			vazio += " ";
		}
		return (texto+vazio).substring(0, maxCaracteres);
	}
	
	public static int pedeNumero(String texto,int minNum,int maxNum) {
		Scanner sc = new Scanner(System.in);
		String s;
		int num = 0;
		boolean teste = true;
		
		do {
			System.out.print(texto);
			s = sc.nextLine();
			try {
				num = Integer.parseInt(s);
				teste = false;
				if(num<minNum) {teste = true;System.out.println("O numero tera de ser maior que "+minNum);}
				if(num>maxNum) {teste = true;System.out.println("O numero tera de ser menor que "+maxNum);}
			}catch(NumberFormatException e) {
				teste = true;
				System.out.println("Formato invalido. Por favor coloque um numero.");
			}
			
		}while(teste);
		return num;
	}
	
	public static String leLinha(String texto) {
		Scanner sc = new Scanner(System.in);
		System.out.print(texto);
		String textoR = sc.nextLine();
		return textoR;
	}
	
	public static String formatNum (int num,int casas) {
		String texto = ""+num;
		while(texto.length()<casas) {
			texto = "0"+texto;
		}
		return texto;
	}
	
	
	
}
