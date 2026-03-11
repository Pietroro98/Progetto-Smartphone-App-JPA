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

            testInserimentoNuovaApp(appServiceInstance);

            testAggiornamentoVersioneEDataUltimoAggiornamentoApp(appServiceInstance);

            testInstallazioneAppEsistenteSuSmartphoneEsistente(appServiceInstance, smartphoneServiceInstance);

            testDisinstallazioneAppDaSmartphone(appServiceInstance, smartphoneServiceInstance);

            testRimozioneCompletaSmartphoneAssociatoADueApp(appServiceInstance, smartphoneServiceInstance);

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

    private static void testAggiornamentoVersioneEDataUltimoAggiornamentoApp(AppService appService) throws Exception {
        System.out.println(".......testAggiornamentoVersioneEDataUltimoAggiornamentoApp inizio.............");

        List<App> listaApp = appService.listAll();
        App appDaAggiornare = listaApp.stream().findFirst().orElse(null);

        if (appDaAggiornare == null || appDaAggiornare.getId() == null) {
            throw new RuntimeException("testAggiornamentoVersioneEDataUltimoAggiornamentoApp fallito: insert non eseguito");
        }

        LocalDate nuovaDataAggiornamento = LocalDate.parse("2026-03-11");
        Float nuovaVersione = 1.4F;

        appDaAggiornare.setDataUltimoAggiornamento(nuovaDataAggiornamento);
        appDaAggiornare.setVersione(nuovaVersione);
        appService.aggiorna(appDaAggiornare);

        App appAggiornata = appService.caricaSingoloElemento(appDaAggiornare.getId());
        if (appAggiornata == null
                || appAggiornata.getVersione() == null
                || appAggiornata.getDataUltimoAggiornamento() == null
                || appAggiornata.getVersione().compareTo(nuovaVersione) != 0
                || !appAggiornata.getDataUltimoAggiornamento().equals(nuovaDataAggiornamento)) {
            throw new RuntimeException("testAggiornamentoVersioneEDataUltimoAggiornamentoApp fallito");
        }
        System.out.println(".......testAggiornamentoVersioneEDataUltimoAggiornamentoApp fine: PASSED.............");
    }

    private static void testInstallazioneAppEsistenteSuSmartphoneEsistente(AppService appService, SmartphoneService smartphoneService) throws Exception {
        System.out.println(".......testInstallazioneAppEsistenteSuSmartphoneEsistente inizio.............");

        App appDaInstallare = appService.listAll().stream().findFirst().orElse(null);
        Smartphone smartphoneDestinazione = smartphoneService.listAll().stream().findFirst().orElse(null);

        if (appDaInstallare == null || appDaInstallare.getId() == null || smartphoneDestinazione == null || smartphoneDestinazione.getId() == null) {
            throw new RuntimeException("testInstallazioneAppEsistenteSuSmartphoneEsistente fallito: dati non presenti");
        }

        smartphoneService.installaApp(smartphoneDestinazione, appDaInstallare);

        Smartphone smartphoneAggiornato = smartphoneService.caricaSingoloElemento(smartphoneDestinazione.getId());
        boolean appInstallata = smartphoneAggiornato != null
                && smartphoneAggiornato.getApps().stream().anyMatch(app -> app.getId().equals(appDaInstallare.getId()));

        if (!appInstallata) {
            throw new RuntimeException("testInstallazioneAppEsistenteSuSmartphoneEsistente fallito");
        }

        System.out.println(".......testInstallazioneAppEsistenteSuSmartphoneEsistente fine: PASSED.............");
    }

    private static void testDisinstallazioneAppDaSmartphone(AppService appService, SmartphoneService smartphoneService) throws Exception {
        System.out.println(".......testDisinstallazioneAppDaSmartphone inizio.............");

        App appDaDisinstallare = appService.listAll().stream().findFirst().orElse(null);
        Smartphone smartphoneDestinazione = smartphoneService.listAll().stream().findFirst().orElse(null);

        if (appDaDisinstallare == null || appDaDisinstallare.getId() == null
                || smartphoneDestinazione == null || smartphoneDestinazione.getId() == null) {
            throw new RuntimeException("testDisinstallazioneAppDaSmartphone fallito: dati non presenti");
        }

        smartphoneService.disinstallaApp(smartphoneDestinazione, appDaDisinstallare);

        Smartphone smartphoneAggiornato = smartphoneService.caricaSingoloElemento(smartphoneDestinazione.getId());
        boolean appAncoraInstallata = smartphoneAggiornato != null
                && smartphoneAggiornato.getApps().stream().anyMatch(app -> app.getId().equals(appDaDisinstallare.getId()));

        if (appAncoraInstallata) {
            throw new RuntimeException("testDisinstallazioneAppDaSmartphone fallito");
        }

        System.out.println(".......testDisinstallazioneAppDaSmartphone fine: PASSED.............");
    }

    private static void testRimozioneCompletaSmartphoneAssociatoADueApp(AppService appService,
                                                                        SmartphoneService smartphoneService)
            throws Exception {
        System.out.println(".......testRimozioneCompletaSmartphoneAssociatoADueApp inizio.............");

        Smartphone smartphoneDaRimuovere = new Smartphone("Samsung", "Galaxy S26", 1199.99F, 18.0F);
        smartphoneService.inserisciNuovo(smartphoneDaRimuovere);

        App primaApp = new App("whatsapp", LocalDate.parse("2026-02-01"), LocalDate.parse("2026-02-15"), 2.1F);
        App secondaApp = new App("telegram", LocalDate.parse("2026-02-02"), LocalDate.parse("2026-02-20"), 3.4F);
        appService.inserisciNuovo(primaApp);
        appService.inserisciNuovo(secondaApp);

        smartphoneService.installaApp(smartphoneDaRimuovere, primaApp);
        smartphoneService.installaApp(smartphoneDaRimuovere, secondaApp);

        Smartphone smartphoneConApp = smartphoneService.caricaSingoloElemento(smartphoneDaRimuovere.getId());
        if (smartphoneConApp == null || smartphoneConApp.getApps().size() != 2) {
            throw new RuntimeException("testRimozioneCompletaSmartphoneAssociatoADueApp fallito: associazioni non create");
        }

        smartphoneService.rimuoviCompleto(smartphoneDaRimuovere.getId());

        boolean smartphoneAncoraPresente = smartphoneService.listAll().stream()
                .anyMatch(smartphone -> smartphone.getId().equals(smartphoneDaRimuovere.getId()));

        if (smartphoneAncoraPresente) {
            throw new RuntimeException("testRimozioneCompletaSmartphoneAssociatoADueApp fallito");
        }

        System.out.println(".......testRimozioneCompletaSmartphoneAssociatoADueApp fine: PASSED.............");
    }
}
