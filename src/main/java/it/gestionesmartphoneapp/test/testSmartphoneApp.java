package it.gestionesmartphoneapp.test;

import it.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.gestionesmartphoneapp.service.MyServiceFactory;
import it.gestionesmartphoneapp.service.app.AppService;
import it.gestionesmartphoneapp.service.smartphone.SmartphoneService;

public class testSmartphoneApp {
    public static void main(String[] args) {
        AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();
        SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();

        try
        {
            System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");
            System.out.println("In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
            System.out.println(
                    "**************************** inizio batteria di test ********************************************");
            System.out.println(
                    "*************************************************************************************************");
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        finally
        {
            EntityManagerUtil.shutdown();
        }
    }

}
