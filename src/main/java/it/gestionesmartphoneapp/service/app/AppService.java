package it.gestionesmartphoneapp.service.app;

import it.gestionesmartphoneapp.dao.app.AppDAO;
import it.gestionesmartphoneapp.model.App;

import java.util.List;

public interface AppService
{
    public List<App> listAll() throws Exception;

    public App caricaSingoloElemento(Long id) throws Exception;

    public void aggiorna(App appInstance) throws Exception;

    public void inserisciNuovo(App appInstance) throws Exception;

    public void rimuovi(Long idApp) throws Exception;

    public void setAppDAO(AppDAO appDAO);
}
