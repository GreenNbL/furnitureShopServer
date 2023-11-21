package services;

import dao.DeliveryDao;
import dao.FurnitureDao;
import models.Delivery;
import models.Furniture;

import java.util.List;

public class DeliveryService {
    private DeliveryDao deliveryDao = new DeliveryDao();

    public DeliveryService() {
    }

    public Delivery findDelivery(int id) {
        return deliveryDao.findById(id);
    }

    public void saveDelivery(Delivery delivery) {
        deliveryDao.save(delivery);
    }

    public void deleteDelivery(Delivery delivery) {
        deliveryDao.delete(delivery);
    }

    public void updateDelivery(Delivery delivery) {
        deliveryDao.update(delivery);
    }

    public List<Delivery> findAllDeliveries() {
        return deliveryDao.findAll();
    }
}
