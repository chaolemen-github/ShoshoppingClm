package com.chaolemen.shoppingclm.cart.bean;

import java.io.Serializable;

public class CartGoods implements Serializable {
    /**
     * id : 411
     * goodsId : 1
     * goodsDesc : Apple MacBook Air 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存 MMGF2CH/A)
     * goodsIcon : https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg
     * goodsPrice : 100
     * goodsCount : 0
     * userId : 206
     */

    private int id;
    private int goodsId;
    private String goodsDesc;
    private String goodsIcon;
    private String goodsPrice;
    private int goodsCount;
    private int userId;

    private boolean isCheck;

    @Override
    public String toString() {
        return "CartGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", goodsIcon='" + goodsIcon + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsCount=" + goodsCount +
                ", userId=" + userId +
                ", isCheck=" + isCheck +
                ", goodsSku='" + goodsSku + '\'' +
                '}';
    }

    /**
     * goodsSku : 2017年i5处理器升级版,8GB内存/256GB SSD
     */


    private String goodsSku;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsIcon() {
        return goodsIcon;
    }

    public void setGoodsIcon(String goodsIcon) {
        this.goodsIcon = goodsIcon;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(String goodsSku) {
        this.goodsSku = goodsSku;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
