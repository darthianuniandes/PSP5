package co.com.ism.controller;

import co.com.ism.view.MainView;

public class AppController {
	
	private static MainView mainView;
	
    public static void main( String[] args ) {
    	mainView = new MainView();
        mainView.leerArchivo(args);
        mainView.obtenerValoresArchivo();
        mainView.integrarSimpsons();
        mainView.imprimirResultados();
    }
}
