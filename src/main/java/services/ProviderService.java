package services;

import dao.ProviderDao;
import models.Provider;


import java.util.List;

public class ProviderService {
    private ProviderDao providerDao = new ProviderDao();

    public ProviderService() {
    }

    public Provider findProvider(int id) {
        return providerDao.findById(id);
    }

    public void saveProvider(Provider provider) {
        providerDao.save(provider);
    }

    public void deleteProvider(Provider provider) {
        providerDao.delete(provider);
    }

    public void updateProvider(Provider provider) {
        providerDao.update(provider);
    }

    public List<Provider> findAllProviders() {
        return providerDao.findAll();
    }


}
