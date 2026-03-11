package it.gestionesmartphoneapp.test;

import it.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.gestionesmartphoneapp.model.App;
import it.gestionesmartphoneapp.model.Smartphone;
import it.gestionesmartphoneapp.service.MyServiceFactory;
import it.gestionesmartphoneapp.service.app.AppService;
import it.gestionesmartphoneapp.service.smartphone.SmartphoneService;

import java.time.LocalDate;
import java.util.List;

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


            testInserimentoNuovoSmartphone(smartphoneServiceInstance);

            testAggiornamentoVersioneOSDiUnoSmartphoneEsistente(smartphoneServiceInstance);


            System.out.println(
                    "****************************** fine batteria di test ********************************************");
            System.out.println(
                    "*************************************************************************************************");
            System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");
            System.out.println("In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");

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

    private static void testInserimentoNuovoSmartphone(SmartphoneService smartphoneService) throws Exception {
        System.out.println(".......testInserimentoNuovoSmartphone inizio.............");

        Smartphone smartphoneInstance = new Smartphone("Apple","Iphone 16 Pro", 1449.99F, 26.7F);
        smartphoneService.inserisciNuovo(smartphoneInstance);
        if (smartphoneInstance.getId() == null)
            throw new RuntimeException("testInserimentoNuovoSmartphone fallito ");

        System.out.println(".......testInserimentoNuovoSmartphone fine: PASSED.............");
    }


    private static void testAggiornamentoVersioneOSDiUnoSmartphoneEsistente(SmartphoneService smartphoneService) throws Exception {
        System.out.println(".......testAggiornamentoVersioneOSDiUnoSmartphoneEsistente inizio.............");

        List<Smartphone> listaSmartphone = smartphoneService.listAll();
        Smartphone telefonoDaAggiornare = listaSmartphone.stream().findFirst().orElse(null);

        if (telefonoDaAggiornare.getId() == null) {
            throw new RuntimeException("testAggiornamentoVersioneOSDiUnoSmartphoneEsistente fallito: insert non eseguito");
        }

        telefonoDaAggiornare.setVersioneOS(20.1F);
        smartphoneService.aggiorna(telefonoDaAggiornare);

        Smartphone smartphoneAggiornato = smartphoneService.caricaSingoloElemento(telefonoDaAggiornare.getId());
        if (smartphoneAggiornato == null || smartphoneAggiornato.getVersioneOS() == null
                || smartphoneAggiornato.getVersioneOS().compareTo(20.1F) != 0) {
            throw new RuntimeException("testAggiornamentoVersioneOSDiUnoSmartphoneEsistente fallito");
        }

        System.out.println(".......testAggiornamentoVersioneOSDiUnoSmartphoneEsistente fine: PASSED.............");
    }

    private static void testInserimentoNuovaApp(AppService appService) throws Exception {
        System.out.println(".......testInserimentoNuovoSmartphone inizio.............");

        App appInstance = new App("instagram", LocalDate.parse("2026-01-01"), LocalDate.parse("2026-01-01"),1.3F);
        appService.inserisciNuovo(appInstance);
        if (appInstance.getId() == null)
            throw new RuntimeException("testInserimentoNuovoSmartphone fallito ");

        System.out.println(".......testInserimentoNuovoSmartphone fine: PASSED.............");
    }

}
