package com.chaolemen.shoppingclm.category.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DBBean {

    @Id
    private long id;
    private int goodsId;
    private String goodsSkuTitle;
    private String goodsSkuContent;
    private String skuTitle;
    @Generated(hash = 1072901091)
    public DBBean(long id, int goodsId, String goodsSkuTitle,
            String goodsSkuContent, String skuTitle) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsSkuTitle = goodsSkuTitle;
        this.goodsSkuContent = goodsSkuContent;
        this.skuTitle = skuTitle;
    }
    @Generated(hash = 1770179993)
    public DBBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodsSkuTitle() {
        return this.goodsSkuTitle;
    }
    public void setGoodsSkuTitle(String goodsSkuTitle) {
        this.goodsSkuTitle = goodsSkuTitle;
    }
    public String getGoodsSkuContent() {
        return this.goodsSkuContent;
    }
    public void setGoodsSkuContent(String goodsSkuContent) {
        this.goodsSkuContent = goodsSkuContent;
    }
    public String getSkuTitle() {
        return this.skuTitle;
    }
    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

}
