package it.gestionesmartphoneapp.service.smartphone;
import it.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.gestionesmartphoneapp.model.Smartphone;

import java.util.List;

public interface SmartphoneService {

    public List<Smartphone> listAll() throws Exception;

    public Smartphone caricaSingoloElemento(Long id) throws Exception;

    public void aggiorna(Smartphone smartphoneInstance) throws Exception;

    public void inserisciNuovo(Smartphone smartphoneInstance) throws Exception;

    public void rimuovi(Long idSmartphone) throws Exception;

    public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);
}
