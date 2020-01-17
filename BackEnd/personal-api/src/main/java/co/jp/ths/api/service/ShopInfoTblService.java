package co.jp.ths.api.service;

import java.util.List;

import co.jp.ths.api.entity.ShopInfoTbl;

public interface ShopInfoTblService {
    
    List<ShopInfoTbl> getShopByKeyword(String keyword);
    
    List<ShopInfoTbl> getAllShop();
    
    ShopInfoTbl getOneShop(String shopCode);
    
    int getTotalNum();
    
    void addShop(ShopInfoTbl tbl);
    
    void editShop(ShopInfoTbl tbl);
    
    void deleteShop(String uuid);

}
