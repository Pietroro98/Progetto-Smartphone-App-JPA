package it.gestionesmartphoneapp.service.smartphone;
import it.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.gestionesmartphoneapp.model.Smartphone;

import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneServiceImpl implements SmartphoneService {

    private SmartphoneDAO smartphoneDAO;
    @Override
    public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO) {
        this.smartphoneDAO = smartphoneDAO;
    }
    @Override
    public List<Smartphone> listAll() throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            smartphoneDAO.setEntityManager(entityManager);
            return smartphoneDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public Smartphone caricaSingoloElemento(Long id) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            smartphoneDAO.setEntityManager(entityManager);
            return smartphoneDAO.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void aggiorna(Smartphone smartphoneInstance) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
           entityManager.getTransaction().begin();
           smartphoneDAO.setEntityManager(entityManager);
           smartphoneDAO.update(smartphoneInstance);
           entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void inserisciNuovo(Smartphone smartphoneInstance) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            entityManager.getTransaction().begin();
            smartphoneDAO.setEntityManager(entityManager);
            smartphoneDAO.insert(smartphoneInstance);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void rimuovi(Long idSmartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            entityManager.getTransaction().begin();
            smartphoneDAO.setEntityManager(entityManager);
            smartphoneDAO.delete(idSmartphone);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }
}
