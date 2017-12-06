package bd_java_code;

public abstract class textEditor {

	public static String limitaString(String texto,int maxCaracteres){
		String vazio = " ";
		for (int i = 0;i<maxCaracteres;i++){
			vazio += " ";
		}
		return (texto+vazio).substring(0, maxCaracteres);
	}
}
