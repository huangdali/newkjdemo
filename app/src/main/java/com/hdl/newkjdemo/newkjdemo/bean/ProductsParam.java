package com.hdl.newkjdemo.newkjdemo.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ProductsParam {

    private ProductsParamBean productsParam;

    private StoreModelBean storeModel;

    private List<SlideListBean> slideList;

    public ProductsParamBean getProductsParam() {
        return productsParam;
    }

    public void setProductsParam(ProductsParamBean productsParam) {
        this.productsParam = productsParam;
    }

    public StoreModelBean getStoreModel() {
        return storeModel;
    }

    public void setStoreModel(StoreModelBean storeModel) {
        this.storeModel = storeModel;
    }

    public List<SlideListBean> getSlideList() {
        return slideList;
    }

    public void setSlideList(List<SlideListBean> slideList) {
        this.slideList = slideList;
    }

    public static class ProductsParamBean {

        private List<ProductsListBean> productsList;

        private List<ProductsTypelistBean> productsTypelist;

        public List<ProductsListBean> getProductsList() {
            return productsList;
        }

        public void setProductsList(List<ProductsListBean> productsList) {
            this.productsList = productsList;
        }

        public List<ProductsTypelistBean> getProductsTypelist() {
            return productsTypelist;
        }

        public void setProductsTypelist(List<ProductsTypelistBean> productsTypelist) {
            this.productsTypelist = productsTypelist;
        }

        public static class ProductsListBean {
            private int buyLowNum;
            private int currPrice;
            private String isShow;
            private String isStandard;
            private int page;
            private String productEme;
            private String productOriginAddress;
            private String productsAddDate;
            private String productsDescription;
            private String productsId;
            private String productsImage;
            private String productsModifiedDate;
            private String productsName;
            private int productsPraise;
            private int productsPrice;
            private String productsSource;
            private String productsStatus;
            private String productsUnit;
            private int productsWeight;
            private int rows;
            private int saleNum;
            private String shopProRecom;
            private String sortId;
            private String specification;
            private int stockNumber;
            private String storeId;

            public int getBuyLowNum() {
                return buyLowNum;
            }

            public void setBuyLowNum(int buyLowNum) {
                this.buyLowNum = buyLowNum;
            }

            public int getCurrPrice() {
                return currPrice;
            }

            public void setCurrPrice(int currPrice) {
                this.currPrice = currPrice;
            }

            public String getIsShow() {
                return isShow;
            }

            public void setIsShow(String isShow) {
                this.isShow = isShow;
            }

            public String getIsStandard() {
                return isStandard;
            }

            public void setIsStandard(String isStandard) {
                this.isStandard = isStandard;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public String getProductEme() {
                return productEme;
            }

            public void setProductEme(String productEme) {
                this.productEme = productEme;
            }

            public String getProductOriginAddress() {
                return productOriginAddress;
            }

            public void setProductOriginAddress(String productOriginAddress) {
                this.productOriginAddress = productOriginAddress;
            }

            public String getProductsAddDate() {
                return productsAddDate;
            }

            public void setProductsAddDate(String productsAddDate) {
                this.productsAddDate = productsAddDate;
            }

            public String getProductsDescription() {
                return productsDescription;
            }

            public void setProductsDescription(String productsDescription) {
                this.productsDescription = productsDescription;
            }

            public String getProductsId() {
                return productsId;
            }

            public void setProductsId(String productsId) {
                this.productsId = productsId;
            }

            public String getProductsImage() {
                return productsImage;
            }

            public void setProductsImage(String productsImage) {
                this.productsImage = productsImage;
            }

            public String getProductsModifiedDate() {
                return productsModifiedDate;
            }

            public void setProductsModifiedDate(String productsModifiedDate) {
                this.productsModifiedDate = productsModifiedDate;
            }

            public String getProductsName() {
                return productsName;
            }

            public void setProductsName(String productsName) {
                this.productsName = productsName;
            }

            public int getProductsPraise() {
                return productsPraise;
            }

            public void setProductsPraise(int productsPraise) {
                this.productsPraise = productsPraise;
            }

            public int getProductsPrice() {
                return productsPrice;
            }

            public void setProductsPrice(int productsPrice) {
                this.productsPrice = productsPrice;
            }

            public String getProductsSource() {
                return productsSource;
            }

            public void setProductsSource(String productsSource) {
                this.productsSource = productsSource;
            }

            public String getProductsStatus() {
                return productsStatus;
            }

            public void setProductsStatus(String productsStatus) {
                this.productsStatus = productsStatus;
            }

            public String getProductsUnit() {
                return productsUnit;
            }

            public void setProductsUnit(String productsUnit) {
                this.productsUnit = productsUnit;
            }

            public int getProductsWeight() {
                return productsWeight;
            }

            public void setProductsWeight(int productsWeight) {
                this.productsWeight = productsWeight;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public int getSaleNum() {
                return saleNum;
            }

            public void setSaleNum(int saleNum) {
                this.saleNum = saleNum;
            }

            public String getShopProRecom() {
                return shopProRecom;
            }

            public void setShopProRecom(String shopProRecom) {
                this.shopProRecom = shopProRecom;
            }

            public String getSortId() {
                return sortId;
            }

            public void setSortId(String sortId) {
                this.sortId = sortId;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public int getStockNumber() {
                return stockNumber;
            }

            public void setStockNumber(int stockNumber) {
                this.stockNumber = stockNumber;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }
        }

        public static class ProductsTypelistBean {
            private int page;
            private int rows;
            private String typeAddDate;
            private String typeId;
            private String typeModifiedDate;
            private String typeName;
            private int typeSort;
            private String typeStatus;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public String getTypeAddDate() {
                return typeAddDate;
            }

            public void setTypeAddDate(String typeAddDate) {
                this.typeAddDate = typeAddDate;
            }

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getTypeModifiedDate() {
                return typeModifiedDate;
            }

            public void setTypeModifiedDate(String typeModifiedDate) {
                this.typeModifiedDate = typeModifiedDate;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public int getTypeSort() {
                return typeSort;
            }

            public void setTypeSort(int typeSort) {
                this.typeSort = typeSort;
            }

            public String getTypeStatus() {
                return typeStatus;
            }

            public void setTypeStatus(String typeStatus) {
                this.typeStatus = typeStatus;
            }
        }
    }

    public static class StoreModelBean {
        private String cdma;
        private int distributionDistance;
        private int guDingComm;
        private String keepId;
        private String lat;
        private int liuDongComm;
        private String lng;
        private int page;
        private int rows;
        private int shippingAmount;
        private String storeAddress;
        private String storeBussinessCard;
        private double storeComm;
        private String storeCreateDate;
        private String storeId;
        private String storeLogo;
        private String storeName;
        private String storeOpeningTime;
        private String storePublicity;
        private String storeStar;
        private String storeStatus;
        private String storeTelephone;
        private double storeTurnover;
        private String storeType;

        public String getCdma() {
            return cdma;
        }

        public void setCdma(String cdma) {
            this.cdma = cdma;
        }

        public int getDistributionDistance() {
            return distributionDistance;
        }

        public void setDistributionDistance(int distributionDistance) {
            this.distributionDistance = distributionDistance;
        }

        public int getGuDingComm() {
            return guDingComm;
        }

        public void setGuDingComm(int guDingComm) {
            this.guDingComm = guDingComm;
        }

        public String getKeepId() {
            return keepId;
        }

        public void setKeepId(String keepId) {
            this.keepId = keepId;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public int getLiuDongComm() {
            return liuDongComm;
        }

        public void setLiuDongComm(int liuDongComm) {
            this.liuDongComm = liuDongComm;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getShippingAmount() {
            return shippingAmount;
        }

        public void setShippingAmount(int shippingAmount) {
            this.shippingAmount = shippingAmount;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getStoreBussinessCard() {
            return storeBussinessCard;
        }

        public void setStoreBussinessCard(String storeBussinessCard) {
            this.storeBussinessCard = storeBussinessCard;
        }

        public double getStoreComm() {
            return storeComm;
        }

        public void setStoreComm(double storeComm) {
            this.storeComm = storeComm;
        }

        public String getStoreCreateDate() {
            return storeCreateDate;
        }

        public void setStoreCreateDate(String storeCreateDate) {
            this.storeCreateDate = storeCreateDate;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoreLogo() {
            return storeLogo;
        }

        public void setStoreLogo(String storeLogo) {
            this.storeLogo = storeLogo;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreOpeningTime() {
            return storeOpeningTime;
        }

        public void setStoreOpeningTime(String storeOpeningTime) {
            this.storeOpeningTime = storeOpeningTime;
        }

        public String getStorePublicity() {
            return storePublicity;
        }

        public void setStorePublicity(String storePublicity) {
            this.storePublicity = storePublicity;
        }

        public String getStoreStar() {
            return storeStar;
        }

        public void setStoreStar(String storeStar) {
            this.storeStar = storeStar;
        }

        public String getStoreStatus() {
            return storeStatus;
        }

        public void setStoreStatus(String storeStatus) {
            this.storeStatus = storeStatus;
        }

        public String getStoreTelephone() {
            return storeTelephone;
        }

        public void setStoreTelephone(String storeTelephone) {
            this.storeTelephone = storeTelephone;
        }

        public double getStoreTurnover() {
            return storeTurnover;
        }

        public void setStoreTurnover(double storeTurnover) {
            this.storeTurnover = storeTurnover;
        }

        public String getStoreType() {
            return storeType;
        }

        public void setStoreType(String storeType) {
            this.storeType = storeType;
        }
    }

    public static class SlideListBean {
        private String adddate;
        private String disable;
        private String image;
        private String name;
        private int page;
        private int rows;
        private int slideType;
        private String sortid;
        private String unid;

        public String getAdddate() {
            return adddate;
        }

        public void setAdddate(String adddate) {
            this.adddate = adddate;
        }

        public String getDisable() {
            return disable;
        }

        public void setDisable(String disable) {
            this.disable = disable;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getSlideType() {
            return slideType;
        }

        public void setSlideType(int slideType) {
            this.slideType = slideType;
        }

        public String getSortid() {
            return sortid;
        }

        public void setSortid(String sortid) {
            this.sortid = sortid;
        }

        public String getUnid() {
            return unid;
        }

        public void setUnid(String unid) {
            this.unid = unid;
        }
    }
}
