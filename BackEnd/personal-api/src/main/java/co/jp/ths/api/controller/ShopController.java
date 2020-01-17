package co.jp.ths.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.jp.ths.api.constant.Constant;
import co.jp.ths.api.dto.ShopInfoTblToResponse;
import co.jp.ths.api.entity.ShopInfo;
import co.jp.ths.api.entity.ShopInfoTbl;
import co.jp.ths.api.entity.response.ShopInfoRes;
import co.jp.ths.api.service.ShopInfoTblService;
import co.jp.ths.api.util.CertGenerate;

/**
 * 
 * @author iku 2019/05/01
 *
 */
@RestController
@RequestMapping("/manage")
public class ShopController {

	@Autowired
	private ShopInfoTblService shopTblService;

	ShopInfoTblToResponse conv = new ShopInfoTblToResponse();

	@GetMapping(value = "/shop-get/{keyword}")
	public List<ShopInfo> getShopByKeyword(@PathVariable String keyword) {
		String keyword1 = "%" + keyword + "%";

		List<ShopInfoTbl> shopList = shopTblService.getShopByKeyword(keyword1);
		return conv.convertList(shopList);

	}

	@GetMapping(value = "/shop-getOne/{shopCode}")
	public ShopInfoRes getOneShop(@PathVariable String shopCode){

		ShopInfoTbl tbl = shopTblService.getOneShop(shopCode);
		ShopInfoRes shopRes = new ShopInfoRes();
		if(tbl == null) {
			shopRes.setReturnCode("NG");
			shopRes.setReturnMessage("この店舗が存在しません。");
			return shopRes;
		} 

		shopRes.setReturnCode("OK");
		shopRes.setReturnMessage("");
		shopRes.setShopInfo(conv.convert(tbl));
		return shopRes;
	}

	@GetMapping(value = "/shop-getAll")
	public List<ShopInfo> getAllShops() {

		List<ShopInfoTbl> shopList = shopTblService.getAllShop();
		return conv.convertList(shopList);
	}

	@PostMapping(value = "/shop-insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ShopInfo> addShop(@RequestBody ShopInfoTbl tbl) {
		if(StringUtils.isNotBlank(tbl.getCertPath())) {
			String templatePath = null;
			if(tbl.getCertPath().contains("template1")) {
				templatePath = Constant.templateCertPath + "template1.png";
			} else if(tbl.getCertPath().contains("template2")) {
				templatePath = Constant.templateCertPath + "template2.jpg";
			} else if(tbl.getCertPath().contains("template3")) {
				templatePath = Constant.templateCertPath + "template3.jpeg";
			}

	        List<Map<String, String>> contentMap = new ArrayList<>();

	        Map<String, String> map = new HashMap<>();
	        map.put("word", StringUtils.isBlank(tbl.getCertWord()) ? tbl.getShopName() : tbl.getCertWord());
	        map.put("x", "217");
	        map.put("y", "222");
	        contentMap.add(map);

	        String savePath = Constant.targetCertPath + tbl.getShopCode() + ".png";
			int status = CertGenerate.addWaterMark(templatePath, savePath, Constant.certcolor, Constant.certFont, contentMap);
		    if(status == 0) {
		    	tbl.setCertPath("");
		    } else {
		    	tbl.setCertPath(Constant.certServerPath + tbl.getShopCode() + ".png");
		    }
		}

		tbl.setShopUuid(UUID.randomUUID().toString());

		shopTblService.addShop(tbl);
		
		return getAllShops();
	}

	@PostMapping(value = "/shop-update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ShopInfo> updateShop(@RequestBody ShopInfoTbl tbl) {
		if(StringUtils.isNotBlank(tbl.getCertPath())) {
			String templatePath = null;
			if(tbl.getCertPath().contains("template1")) {
				templatePath = Constant.templateCertPath + "template1.png";
			} else if(tbl.getCertPath().contains("template2")) {
				templatePath = Constant.templateCertPath + "template2.jpg";
			} else if(tbl.getCertPath().contains("template3")) {
				templatePath = Constant.templateCertPath + "template3.jpeg";
			}

	        List<Map<String, String>> contentMap = new ArrayList<>();

	        Map<String, String> map = new HashMap<>();
	        map.put("word", StringUtils.isBlank(tbl.getCertWord()) ? tbl.getShopName() : tbl.getCertWord());
	        map.put("x", "217");
	        map.put("y", "300");
	        contentMap.add(map);

	        String savePath = Constant.targetCertPath + tbl.getShopCode() + ".png";
			int status = CertGenerate.addWaterMark(templatePath, savePath, Constant.certcolor, Constant.certFont, contentMap);
		    if(status == 0) {
		    	tbl.setCertPath("");
		    } else {
		    	tbl.setCertPath(Constant.certServerPath + tbl.getShopCode() + ".png");
		    }
		}
		
		shopTblService.editShop(tbl);
		
		return getAllShops();
	}

	@GetMapping(value = "/shop-delete/{uuid}")
	public List<ShopInfo> deleteShop(@PathVariable String uuid) {
		shopTblService.deleteShop(uuid);
		return getAllShops();
	}

}
