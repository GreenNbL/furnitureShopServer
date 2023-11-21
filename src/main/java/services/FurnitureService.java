package services;

import dao.FurnitureDao;
import models.Furniture;

import java.util.List;

public class FurnitureService {
    private FurnitureDao furnitureDao = new FurnitureDao();

    public FurnitureService() {
    }

    public Furniture findFurniture(int id) {
        return furnitureDao.findById(id);
    }

    public void saveFurniture(Furniture furniture) {
        furnitureDao.save(furniture);
    }

    public void deleteFurniture(Furniture furniture) {
        furnitureDao.delete(furniture);
    }

    public void updateFurniture(Furniture furniture) {
        furnitureDao.update(furniture);
    }

    public List<Furniture> findAllFurnitures() {
        return furnitureDao.findAll();
    }
}
