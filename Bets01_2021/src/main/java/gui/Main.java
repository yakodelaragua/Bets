package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import businessLogic.BLFacade;
import configuration.ConfigXML;
import domain.Event;
import domain.ExtendedIterator;
import domain.Factory;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ConfigXML c = ConfigXML.getInstance();
		System.out.println(c.getLocale());
		Locale.setDefault(new Locale(c.getLocale()));

		BLFacade blFacade = Factory.createFacade(c);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse("17/12/2020");
			date.setHours(01);
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		
		ExtendedIterator<Event> i = blFacade.getEventsIterator(date);
		Event e;
		System.out.println("_____________________");
		System.out.println("RECORRIDO HACIA ATRÁS");
		i.goLast(); // Hacia atrás
		while (i.hasPrevious()) {
			e = i.previous();
			System.out.println(e.toString());
		}
		System.out.println();
		System.out.println("_____________________");
		System.out.println("RECORRIDO HACIA ADELANTE");
		i.goFirst(); // Hacia adelante
		while (i.hasNext()) {
			e = i.next();
			System.out.println(e.toString());
		}

	}

}
