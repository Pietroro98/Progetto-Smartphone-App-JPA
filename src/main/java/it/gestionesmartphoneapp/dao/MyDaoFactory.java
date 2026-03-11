package it.gestionesmartphoneapp.dao;
import it.gestionesmartphoneapp.dao.app.AppDAO;
import it.gestionesmartphoneapp.dao.app.AppDAOImpl;
import it.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.gestionesmartphoneapp.dao.smartphone.SmartphoneDAOImpl;

public class MyDaoFactory {

	private static AppDAO appDaoInstance = null;
	private static SmartphoneDAO smartphoneDaoInstance = null;

	public static AppDAO getAppDAOInstance() {
		if (appDaoInstance == null)
			appDaoInstance = new AppDAOImpl();

		return appDaoInstance;
	}

	public static SmartphoneDAO getSmartphoneDAOInstance() {
		if (smartphoneDaoInstance == null)
			smartphoneDaoInstance = new SmartphoneDAOImpl();

		return smartphoneDaoInstance;
	}

}
