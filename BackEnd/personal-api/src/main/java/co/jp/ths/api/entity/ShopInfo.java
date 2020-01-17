package co.jp.ths.api.entity;

public class ShopInfo {
	private String shopName;
	private String shopCode;
	private String shopUuid;
	private String address;
	private String details;
	private String certPath;
	private String certFlag;
	private String createTime;
	private String updateTime;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopUuid() {
		return shopUuid;
	}

	public void setShopUuid(String shopUuid) {
		this.shopUuid = shopUuid;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
