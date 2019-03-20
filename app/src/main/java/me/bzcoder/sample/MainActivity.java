package me.bzcoder.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import me.bzcoder.easyglide.EasyGlide;
import me.bzcoder.easyglide.progress.CircleProgressView;
import me.bzcoder.easyglide.progress.OnProgressListener;
import me.bzcoder.easyglide.transformation.BlurTransformation;
import me.bzcoder.easyglide.transformation.GrayscaleTransformation;
import me.bzcoder.easyglide.transformation.RoundedCornersTransformation;
import me.bzcoder.easyglide.view.SelectImageView;
import me.bzcoder.nidegridphotoview.R;

public class MainActivity extends AppCompatActivity {


    String url1 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=579539400,2248223712&fm=26&gp=0.jpg";
    String url2 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=370513584,2329026648&fm=27&gp=0.jpg";
    String url3 = "https://img5.duitang.com/uploads/item/201411/24/20141124111858_aeWeU.thumb.700_0.gif";
    String url4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553063390335&di=65e36f298c8089d8855058921b47fc57&imgtype=0&src=http%3A%2F%2Fbannerdesign.cn%2Fwp-content%2Fuploads%2F2015%2F02%2F20150204014336322.jpg";
    private ImageView iv0;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;
    private SelectImageView iv7;
    private ImageView iv8;
    private ImageView iv9;
    private ImageView iv10;
    private CircleProgressView circleProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        circleProgressView = findViewById(R.id.progressView);
        iv0 = (ImageView) findViewById(R.id.iv_0);
        iv1 = (ImageView) findViewById(R.id.iv_1);
        iv2 = (ImageView) findViewById(R.id.iv_2);
        iv3 = (ImageView) findViewById(R.id.iv_3);
        iv4 = (ImageView) findViewById(R.id.iv_4);
        iv5 = (ImageView) findViewById(R.id.iv_5);
        iv6 = (ImageView) findViewById(R.id.iv_6);
        iv7 = (SelectImageView) findViewById(R.id.iv_7);
        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        iv8 = (ImageView) findViewById(R.id.iv_8);
        iv9 = (ImageView) findViewById(R.id.iv_9);
        iv10 = (ImageView) findViewById(R.id.iv_10);

        EasyGlide.loadImage(this, url4, iv0, (isComplete, percentage, bytesRead, totalBytes) -> {
            if (isComplete) {
                circleProgressView.setVisibility(View.GONE);
            } else {
                circleProgressView.setVisibility(View.VISIBLE);
                circleProgressView.setProgress(percentage);
            }
        });

        EasyGlide.loadImage(this, url1, iv1);

        EasyGlide.loadImage(this, url3, iv2);

        EasyGlide.loadBlurImage(this, url1, iv3);

        EasyGlide.loadCircleImage(this, url1, iv4);

        EasyGlide.loadRoundCornerImage(this, url1, iv5);

        EasyGlide.loadGrayImage(this, url1, iv6);

        EasyGlide.loadImage(this, url2, iv7);

        EasyGlide.loadImageWithTransformation(this, url2, iv8, new GrayscaleTransformation(),new RoundedCornersTransformation(50, 0));

        EasyGlide.loadImageWithTransformation(this, url2, iv9, new BlurTransformation(this, 20)
                , new GrayscaleTransformation(), new CircleCrop());
    }


}
