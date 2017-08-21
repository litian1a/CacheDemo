package com.cachedemo;

import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 原生模块
 */
public class ImgCacheModule extends ReactContextBaseJavaModule {

    public ImgCacheModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    /**
     *
     * @return js调用的模块名
     */
    @Override
    public String getName() {
        return "ImgCacheModule";
    }


    /*
        清除缓存
     */
    @ReactMethod
    public void clearImgCache( Callback success,Callback error) {
        try {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            imagePipeline.clearMemoryCaches();
            imagePipeline.clearDiskCaches();

            // combines above two lines
            imagePipeline.clearCaches();
            success.invoke("删除成功");
        }
        catch (Exception e){
            error.invoke("删除失败");
        }
    }

    /*
        查看缓存区大小
     */
    @ReactMethod
    public void getImgCache( Callback success,Callback error) {
        try {

         //   Fresco.getImagePipelineFactory().getMainFileCache().trimToMinimum();
            Fresco.getImagePipelineFactory().getMainFileCache().trimToMinimum();
            long size = Fresco.getImagePipelineFactory().getMainFileCache().getSize();//b

            success.invoke(size+"");
        }
        catch (Exception e){
            e.printStackTrace();
            error.invoke("获取失败");
        }
    }
}
