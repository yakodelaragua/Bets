package domain;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class Factory {
	public static BLFacade createFacade(ConfigXML config) {
		BLFacade appFacadeInterface;
		
		if (config.isBusinessLogicLocal()) {
			DataAccess da = new DataAccess(config.getDataBaseOpenMode().equals("initialize"));
			appFacadeInterface = new BLFacadeImplementation(da);

		} else {
			String serviceName = "http://" + config.getBusinessLogicNode() + ":" + config.getBusinessLogicPort()
					+ "/ws/" + config.getBusinessLogicName() + "?wsdl";
			URL url = null;
			try {
				url = new URL(serviceName);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");

			Service service = Service.create(url, qname);

			appFacadeInterface = service.getPort(BLFacade.class);
		}

		return appFacadeInterface;
	}
}
