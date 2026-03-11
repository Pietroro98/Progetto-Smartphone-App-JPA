package it.gestionesmartphoneapp.service;

import it.gestionesmartphoneapp.dao.MyDaoFactory;
import it.gestionesmartphoneapp.service.app.AppService;
import it.gestionesmartphoneapp.service.app.AppServiceImpl;
import it.gestionesmartphoneapp.service.smartphone.SmartphoneService;
import it.gestionesmartphoneapp.service.smartphone.SmartphoneServiceImpl;

public class MyServiceFactory {

	private static AppService appServiceInstance = null;
	private static SmartphoneService smartphoneServiceInstance = null;

	public static AppService getBranoServiceInstance() {
		if (appServiceInstance == null)
			appServiceInstance = new AppServiceImpl();

		appServiceInstance.setAppDAO(MyDaoFactory.getAppDAOInstance());

		return appServiceInstance;
	}

	public static SmartphoneService getGenereServiceInstance() {
		if (smartphoneServiceInstance == null)
			smartphoneServiceInstance = new SmartphoneServiceImpl();

		smartphoneServiceInstance.setSmartphoneDAO(MyDaoFactory.getSmartphoneDAOInstance());

		return smartphoneServiceInstance;
	}

}
