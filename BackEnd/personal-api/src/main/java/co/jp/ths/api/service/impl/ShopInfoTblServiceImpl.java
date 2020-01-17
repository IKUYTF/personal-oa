package co.jp.ths.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.ths.api.dao.ShopInfoTblDao;
import co.jp.ths.api.entity.ShopInfoTbl;
import co.jp.ths.api.service.ShopInfoTblService;

@Service
public class ShopInfoTblServiceImpl implements ShopInfoTblService {
    
    @Autowired
    private ShopInfoTblDao dao;
    
    @Override
    public List<ShopInfoTbl> getShopByKeyword(String keyword) {
        return dao.selectByKeyword(keyword);
    }
    
    @Override
    public List<ShopInfoTbl> getAllShop() {
        return dao.selectAll();
    }

    @Override
    public ShopInfoTbl getOneShop(String shopCode)
    {
        return dao.selectOne(shopCode);
    }
    
    @Override
    public int getTotalNum() {
        return dao.countAll();
    }
    
    @Override
    public void addShop(ShopInfoTbl tbl) {
        dao.insert(tbl);
    }
    
    @Override
    public void editShop(ShopInfoTbl tbl) {
        dao.update(tbl);
    }
    
    @Override
    public void deleteShop(String uuid) {
    	dao.delete(uuid);
    }

}
