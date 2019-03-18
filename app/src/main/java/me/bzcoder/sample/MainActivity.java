package me.bzcoder.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import me.bzcoder.easyglide.EasyGlide;
import me.bzcoder.nidegridphotoview.R;

public class MainActivity extends AppCompatActivity {

    private ImageView tv1;


    String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497688355699&di=ea69a930b82ce88561c635089995e124&imgtype=0&src=http%3A%2F%2Fcms-bucket.nosdn.127.net%2Ff84e566bcf654b3698363409fbd676ef20161119091503.jpg";
    String url2 = "http://img1.imgtn.bdimg.com/it/u=4027212837,1228313366&fm=23&gp=0.jpg";
    String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529402445474&di=b5da3b2f6a466e618e1e32d4dd2bda4d&imgtype=0&src=http%3A%2F%2F2b.zol-img.com.cn%2Fproduct%2F133_500x2000%2F801%2Fce21ke76FRh4A.jpg";


    String url4 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=579539400,2248223712&fm=26&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        tv1 =  findViewById(R.id.tv_1);
        EasyGlide.loadImage(this,url4,tv1);
    }


}
