package me.bzcoder.sample;

import android.Manifest;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import me.bzcoder.easyglide.EasyGlide;
import me.bzcoder.easyglide.progress.CircleProgressView;
import me.bzcoder.easyglide.sample.R;
import me.bzcoder.easyglide.transformation.BlurTransformation;
import me.bzcoder.easyglide.transformation.GrayscaleTransformation;
import me.bzcoder.easyglide.transformation.RoundedCornersTransformation;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {


    String url1 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=579539400,2248223712&fm=26&gp=0.jpg";
    String url2 = "http://s3.sinaimg.cn/mw690/002c2mEVzy7nKnBsVCqc2&690";
    String url3 = "https://img5.duitang.com/uploads/item/201411/24/20141124111858_aeWeU.thumb.700_0.gif";
    String url4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553095565699&di=219f0c4a8ac0d1074bc401f52abc7114&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201701%2F01%2F20170101234706_KGtEv.png";
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
    private ImageView iv11;
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
        iv11 = (ImageView) findViewById(R.id.iv_11);


        EasyGlide.loadImage(this, url4, iv0, (isComplete, percentage, bytesRead, totalBytes) -> {
//            if (isComplete) {
//                circleProgressView.setVisibility(View.GONE);
//
//                circleProgressView.setVisibility(View.VISIBLE);
//                circleProgressView.setProgress(percentage);
//           }
        });
        circleProgressView.setVisibility(View.VISIBLE);

        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> circleProgressView.setProgress((Integer) animator.getAnimatedValue()));
        animator.start();

        EasyGlide.placeHolderImageView = R.color.red;

        EasyGlide.circlePlaceholderImageView = R.color.red;

        iv1.setOnClickListener(v ->


                downloadImage()

        );

        EasyGlide.loadImage(this, url3, iv1);

        EasyGlide.loadImage(this, url4, iv2);

        EasyGlide.loadBlurImage(this, url4, iv3);

        EasyGlide.loadCircleImage(this, url4, iv4);

        EasyGlide.loadRoundCornerImage(this, url4, iv5);

        EasyGlide.loadGrayImage(this, url4, iv6);

        EasyGlide.loadResizeXYImage(this, url2, 800, 200, iv7);

        EasyGlide.loadImageWithTransformation(this, url2, iv8, new GrayscaleTransformation(), new RoundedCornersTransformation(50, 0));

        EasyGlide.loadCircleWithBorderImage(this, url2, iv9);

        EasyGlide.loadImageWithTransformation(this, url2, iv10, new BlurTransformation(this, 20)
                , new GrayscaleTransformation(), new CircleCrop());

        EasyGlide.loadImage(this, R.drawable.test, iv11);
    }

    private boolean hasStoragePermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private static final int WRITE_EXTERNAL_PERM = 123;


    @AfterPermissionGranted(WRITE_EXTERNAL_PERM)
    private void downloadImage() {
        if (hasStoragePermission()) {
            EasyGlide.downloadImageToGallery(iv1.getContext(), url3);
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.need_write_external),
                    WRITE_EXTERNAL_PERM,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

    }


}
