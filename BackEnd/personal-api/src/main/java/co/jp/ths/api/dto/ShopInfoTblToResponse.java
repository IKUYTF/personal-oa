package co.jp.ths.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.jp.ths.api.entity.ShopInfo;
import co.jp.ths.api.entity.ShopInfoTbl;

public class ShopInfoTblToResponse {
	
	public ShopInfoTblToResponse() {
		
	}
	
	public ShopInfo convert(ShopInfoTbl tbl) {
				
		ShopInfo res = new ShopInfo();
		res.setShopName(tbl.getShopName());
		res.setShopCode(tbl.getShopCode());
		res.setAddress(tbl.getAddress());
		res.setDetails(tbl.getDetails());

		if (StringUtils.isBlank(tbl.getCertPath())) {
			res.setCertPath("未認証証書");
		} else {
			res.setCertPath(tbl.getCertPath());
		}

		if(tbl.getCreateTime() != null) {
			res.setCreateTime(tbl.getCreateTime().toString().substring(0, 16));
		}
		if(tbl.getUpdateTime() != null) {
			res.setUpdateTime(tbl.getUpdateTime().toString().substring(0, 16));
		}
		
		return res;
		
	}
	

	public List<ShopInfo> convertList(List<ShopInfoTbl> tbl) {
		
		List<ShopInfo> res = new ArrayList<>(tbl.size());
		
		for(ShopInfoTbl shop: tbl) {
			ShopInfo shopInfo = new ShopInfo();
			shopInfo.setShopName(shop.getShopName());
			shopInfo.setShopUuid(shop.getShopUuid());
			shopInfo.setShopCode(shop.getShopCode());
			shopInfo.setAddress(shop.getAddress());
			shopInfo.setDetails(shop.getDetails());
			shopInfo.setCertPath(shop.getCertPath());
			shopInfo.setCertFlag(shop.getCertFlag());

			if(shop.getCreateTime() != null) {
				shopInfo.setCreateTime(shop.getCreateTime().toString().substring(0, 16));
			}
			if(shop.getUpdateTime() != null) {
				shopInfo.setUpdateTime(shop.getUpdateTime().toString().substring(0, 16));
			}
			res.add(shopInfo);
		}
		return res;
		
	}

}
