package com.chaolemen.mvplibrary.model;

import java.util.HashMap;

public class ModelFractory {
    //存储model的集合
    private static HashMap<String, BaseModel> mMap = new HashMap<>();

    //创建model
    public static <M extends BaseModel> M createModel(Class<M> mClass) {
        if (mMap.get(mClass) != null) {
            return (M) mMap.get(mClass);
        }
        M mModel = null;
        try {
            //通过反射创建对象,你的子类的modle必须要有无参构造方法。
            mModel = mClass.newInstance();
            if (mModel!=null){
                mMap.put(mClass.getName(),mModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mModel;
    }
}
