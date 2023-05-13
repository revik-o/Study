package ua.avadamedia.dz4.ModelDAO;

import ua.avadamedia.dz4.Model.BrandModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BrandModelDAO implements IBrandModelDAO {

    private HashMap<UUID, BrandModel> brandModelHashMap = new HashMap<>();

    @Override
    public void addNewBrand(BrandModel brandModel) {
        brandModelHashMap.put(brandModel.getId(), brandModel);
    }

    @Override
    public void removeNewBrand(BrandModel brandModel) {
        brandModelHashMap.remove(brandModel.getId());
    }

    @Override
    public BrandModel getBrandModel(UUID uuid) {
        return brandModelHashMap.get(uuid);
    }

    @Override
    public List<BrandModel> getAllData() {
        List<BrandModel> brandModels = new ArrayList<>();
        brandModelHashMap.forEach((uuid, brandModel) -> brandModels.add(brandModel));
        return brandModels;
    }

    public HashMap<UUID, BrandModel> getBrandModelHashMap() {
        return brandModelHashMap;
    }
}
