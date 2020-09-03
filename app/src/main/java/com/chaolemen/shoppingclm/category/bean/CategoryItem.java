package com.chaolemen.shoppingclm.category.bean;

public class CategoryItem {
    /**
     * id : 9
     * categoryId : 24
     * goodsDesc : Apple iPhone 6s Plus (A1699) 32G 金色 移动联通电信4G手机
     * goodsDefaultIcon : https://img14.360buyimg.com/n1/s450x450_jfs/t3268/124/2646283367/114153/f5704b88/57e4a358N9ccc6645.jpg
     * goodsDefaultPrice : 100
     * goodsDetailOne : https://img30.360buyimg.com/jgsq-productsoa/jfs/t6337/310/2148869366/61744/dca36a9c/595dda76N64984138.jpg
     * goodsDetailTwo : https://img30.360buyimg.com/jgsq-productsoa/jfs/t6175/244/140312464/88326/30e3b943/593a4888N3187bea7.jpg
     * goodsSalesCount : 9890
     * goodsStockCount : 1200
     * goodsCode : 10000000010
     * goodsDefaultSku : 金色,32GB,1件
     * maxPage : 1
     */

    private int id;
    private int categoryId;
    private String goodsDesc;
    private String goodsDefaultIcon;
    private String goodsDefaultPrice;
    private String goodsDetailOne;
    private String goodsDetailTwo;
    private int goodsSalesCount;
    private int goodsStockCount;
    private String goodsCode;
    private String goodsDefaultSku;
    private int maxPage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDefaultIcon() {
        return goodsDefaultIcon;
    }

    public void setGoodsDefaultIcon(String goodsDefaultIcon) {
        this.goodsDefaultIcon = goodsDefaultIcon;
    }

    public String getGoodsDefaultPrice() {
        return goodsDefaultPrice;
    }

    public void setGoodsDefaultPrice(String goodsDefaultPrice) {
        this.goodsDefaultPrice = goodsDefaultPrice;
    }

    public String getGoodsDetailOne() {
        return goodsDetailOne;
    }

    public void setGoodsDetailOne(String goodsDetailOne) {
        this.goodsDetailOne = goodsDetailOne;
    }

    public String getGoodsDetailTwo() {
        return goodsDetailTwo;
    }

    public void setGoodsDetailTwo(String goodsDetailTwo) {
        this.goodsDetailTwo = goodsDetailTwo;
    }

    public int getGoodsSalesCount() {
        return goodsSalesCount;
    }

    public void setGoodsSalesCount(int goodsSalesCount) {
        this.goodsSalesCount = goodsSalesCount;
    }

    public int getGoodsStockCount() {
        return goodsStockCount;
    }

    public void setGoodsStockCount(int goodsStockCount) {
        this.goodsStockCount = goodsStockCount;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsDefaultSku() {
        return goodsDefaultSku;
    }

    public void setGoodsDefaultSku(String goodsDefaultSku) {
        this.goodsDefaultSku = goodsDefaultSku;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
