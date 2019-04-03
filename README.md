# EasyGlide
[![](https://www.jitpack.io/v/BzCoder/EasyGlide.svg)](https://www.jitpack.io/#BzCoder/EasyGlide)

EasyGlide是一款基于Glide4.9.0的工具封装类，功能不复杂，主要是为了方便使用以及防止重复劳动，一行代码就可以搞定一切。工程中对于图片加载进度的监听参考了[GlideImageView](https://github.com/sunfusheng/GlideImageView)，但是由于GlideImageView对于代码的侵入性太高，因此选择重新封装。欢迎大家使用并提出新的需求，我会及时更新。

PS：最近发现Glide一个问题，单页上GIF数目过多时，GIF会疯狂掉帧。

# 演示

![](https://github.com/BzCoder/EasyGlide/blob/master/image/demo.gif)



# 使用方法
## 引入

```

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
```

	dependencies {
	        implementation 'com.github.BzCoder:EasyGlide:1.0.2'
	}
```

## 主要包含以下三个小模块：
- EasyGlide
- CircleProgressView
- SelectImageView

## EasyGlide 图片加载工具类
工具类都在EasyGlide当中，其中封装了常用的图片加载方法。包含基本常用功能（圆形，黑白，圆角矩形，高斯模糊，变换大小，监听下载进度，清除缓存）。
```java

EasyGlide.loadImage(this, url4, iv2);

EasyGlide.loadBlurImage(this, url4, iv3);

EasyGlide.loadCircleImage(this, url4, iv4);

EasyGlide.loadRoundCornerImage(this, url4, iv5);

EasyGlide.loadGrayImage(this, url4, iv6);

EasyGlide.loadResizeXYImage(this, url2, 800, 200, iv7);

EasyGlide.loadImageWithTransformation(this, url2, iv8, new GrayscaleTransformation(), new RoundedCornersTransformation(50, 0));
    
EasyGlide.clearDiskCache(this)；

EasyGlide.clearMemory(this)；

EasyGlide.clearImage(this,imageView)；

```

这些函数可能不能满足需求多种多样的你，所以你也可以通过实现```loadImage(Context context, GlideConfigImpl config)```模仿EasyGlide来对EasyGlide进行扩充。也欢迎直接替issue给我，我来帮您扩充。

你可以尽早的设置全局placeholder，当然也可以单独设置placeholder。
```java
 EasyGlide.placeHolderImageView = R.color.red;

 EasyGlide.circlePlaceholderImageView = R.color.red;
 
```

## 图片下载
```java
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

```
## CircleProgressView 仿微博图片加载盖层
就是原封不动来自[GlideImageView](https://github.com/sunfusheng/GlideImageView) ，在布局中加入即可，有三种样式可供选择。
```xml
 <me.bzcoder.easyglide.progress.CircleProgressView
                android:id="@+id/progressView"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_centerInParent="true"
                android:layout_margin="10dp"
                android:progress="0"
                android:visibility="gone"
                app:cpv_progressNormalColor="@color/transparent10"
                app:cpv_progressReachColor="@color/transparent90_white"
                app:cpv_progressStyle="FillInnerArc"
                app:cpv_progressTextColor="@color/red"
                app:cpv_progressTextSize="13sp"
                app:cpv_progressTextVisible="false" />
             
  
```
## SelectImageView 仿微信图片点击响应
一个点击可以变为半透明的View，算是一个Bonus，所以放在了Sample里。逻辑十分简单，看代码即可。




