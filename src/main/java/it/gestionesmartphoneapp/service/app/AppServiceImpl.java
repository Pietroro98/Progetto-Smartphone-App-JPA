package it.gestionesmartphoneapp.service.app;

import it.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.gestionesmartphoneapp.dao.app.AppDAO;
import it.gestionesmartphoneapp.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public class AppServiceImpl implements AppService
{
    private AppDAO appDAO;

    @Override
    public void setAppDAO(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @Override
    public List<App> listAll() throws Exception
    {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            appDAO.setEntityManager(entityManager);
            return appDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public App caricaSingoloElemento(Long id) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            appDAO.setEntityManager(entityManager);
            return appDAO.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void aggiorna(App appInstance) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            entityManager.getTransaction().begin();

            appDAO.setEntityManager(entityManager);
            appDAO.update(appInstance);

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
    public void inserisciNuovo(App appInstance) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try
        {
            entityManager.getTransaction().begin();

            appDAO.setEntityManager(entityManager);
            appDAO.insert(appInstance);

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
    public void rimuovi(Long idApp) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try
        {
            entityManager.getTransaction().begin();

            appDAO.setEntityManager(entityManager);
            appDAO.delete(idApp);

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
