package it.gestionesmartphoneapp.dao.smartphone;

import it.gestionesmartphoneapp.model.Smartphone;

import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneDAOImpl implements SmartphoneDAO {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Smartphone> findAll() throws Exception {
        return entityManager.createQuery("from Smartphone", Smartphone.class).getResultList();
    }

    @Override
    public Smartphone findById(Long id) throws Exception {
        // join fetch carica subito la collection apps per evitare LazyInitializationException
        // quando viene usata fuori dalla sessione di hibernate
        return entityManager.createQuery(
                "select distinct s from Smartphone s left join fetch s.apps where s.id = ?1",
                Smartphone.class
        ).setParameter(1, id).getSingleResult();
    }

    @Override
    public void update(Smartphone smartphoneInstance) throws Exception {
        if(smartphoneInstance == null) {
            throw new Exception("Smartphone instance is null");
        }
        smartphoneInstance = entityManager.merge(smartphoneInstance);
    }

    @Override
    public void insert(Smartphone smartphoneInstance) throws Exception {
        if(smartphoneInstance == null) {
            throw new Exception("Smartphone instance is null");
        }
        entityManager.persist(smartphoneInstance);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.createQuery("delete from Smartphone where id=?1").setParameter(1, id).executeUpdate();
    }

    @Override
    public void disinstallaApp(Long idSmartphone, Long idApp) throws Exception {
        if (idSmartphone == null || idApp == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.createNativeQuery(
                "delete from smartphone_app where smartphone_id = ?1 and app_id = ?2"
        ).setParameter(1, idSmartphone).setParameter(2, idApp).executeUpdate();
    }

    @Override
    public void rimuoviCompleto(Long idSmartphone) throws Exception {
        if (idSmartphone == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.createNativeQuery(
                "delete from smartphone_app where smartphone_id = ?1"
        ).setParameter(1, idSmartphone).executeUpdate();
        entityManager.createNativeQuery(
                "delete from smartphone where id = ?1"
        ).setParameter(1, idSmartphone).executeUpdate();
    }
}
