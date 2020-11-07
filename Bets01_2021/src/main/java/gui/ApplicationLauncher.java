package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Factory;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher { 
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
		System.out.println(c.getLocale());
		Locale.setDefault(new Locale(c.getLocale()));
		System.out.println("Locale: "+Locale.getDefault());
		
		MainGUI a=new MainGUI();
		a.setVisible(true);

		try {
			
			BLFacade appFacadeInterface = Factory.createFacade(c);

			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			MainGUI.setBussinessLogic(appFacadeInterface);

		

			
		}catch (Exception e) {
			a.jLabelSelectOption.setText("Error: "+e.toString());
			a.jLabelSelectOption.setForeground(Color.RED);	
			
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();


	}

}
