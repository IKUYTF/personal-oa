package co.jp.ths.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.jp.ths.api.entity.ShopInfoTbl;

@Mapper
public interface ShopInfoTblDao {
    
    List<ShopInfoTbl> selectAll();
    
    List<ShopInfoTbl> selectByKeyword(String keyword);
    
    ShopInfoTbl selectOne(String shopCode);
    
    int countAll();
    
    void insert(ShopInfoTbl tbl);
    
    void update(ShopInfoTbl tbl);
    
    void delete(String uuid);
}
