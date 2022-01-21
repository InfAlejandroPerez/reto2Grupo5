package main;

import controlador.C1_Login;
import vista.V1_Login;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			V1_Login frame = new V1_Login();
			frame.setVisible(true);

			C1_Login login = new C1_Login(frame);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
