package lecturaFicheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyJsonParser {

	public static String leerURL(String path, int tipo) {
		System.out.println("Leyendo de " + path);
		String[] commands = { "curl", "-X", "GET", path };
		Process process = null;
		String ret = "";
		try {
			process = Runtime.getRuntime().exec(commands);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				ret += line;
				
			}
			switch (tipo) {
	            case 1:  
	            	ret = ret.replaceAll("(jsonCallback\\(|\\);)", "");
	            	break;
	            case 2:  
	            	ret = ret.substring(ret.indexOf("["));
	            	ret = ret.substring(0, ret.indexOf("]")+1);
	            	break;

	        }

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
