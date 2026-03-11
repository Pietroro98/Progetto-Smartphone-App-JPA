package it.gestionesmartphoneapp.dao.smartphone;

import it.gestionesmartphoneapp.dao.IBaseDAO;
import it.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneDAO extends IBaseDAO<Smartphone> {
    public void disinstallaApp(Long idSmartphone, Long idApp) throws Exception;

    public void rimuoviCompleto(Long idSmartphone) throws Exception;
}
