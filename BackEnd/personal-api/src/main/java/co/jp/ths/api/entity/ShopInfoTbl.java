package co.jp.ths.api.entity;

import java.sql.Timestamp;

public class ShopInfoTbl {
	private String shopName;
	private String shopUuid;
	private String shopCode;
	private String address;
	private String details;
	private String certPath;
	private String certFlag;
	private String certWord;
	private Timestamp createTime;
	private Timestamp updateTime;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopUuid() {
		return shopUuid;
	}

	public void setShopUuid(String shopUuid) {
		this.shopUuid = shopUuid;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public String getCertFlag() {
		return certFlag;
	}

	public void setCertFlag(String certFlag) {
		this.certFlag = certFlag;
	}
	public String getCertWord() {
		return certWord;
	}

	public void setCertWord(String certWord) {
		this.certWord = certWord;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
