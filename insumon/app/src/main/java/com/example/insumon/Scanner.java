package com.example.insumon;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.bytedeco.javacpp.opencv_core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Scanner extends AppCompatActivity {

    private Activity activity;
    public static final int PermissionCode = 1000;
    public static final int GetPhotoCode = 1001;

    private Button mBtnPic;
    private ImageView mShowImage;
    String imageFilePath;

    private boolean isCameraPermission = false;

    public opencv_core.Mat roi;                                                  //++                                                                                //++
        //public Mat roi;                                                        //++                                                                                //++
    public ImageView imageView;                                              //++                                                                                //++
    public TextView result;                                                  //++
    public Process poss;                                                         //++
    public ImgConvertor conver = new ImgConvertor();                         //++
    public TesseractDetect Tess;                                             //++
    private AssetManager assetManager;
    private File file;
    private Button manual;
    private Button enter;

    TextView presult;
    TextView presultbf;                                                       //檢查bf
///
    TextView mTextMessage;
    private static final String TAG = "Scanner";


    private String datapath = "";                                             //csv路徑
    public WriteData2CSVThread writeData2CSVThread;                           //csv畫筆
    public static Transfer transfer = null;                                   //csv傳輸器
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_dashboard:
                        Intent a = new Intent(Scanner.this, History.class);
                        startActivity(a);
                        //Scanner.this.finish();
                        break;
                    case R.id.navigation_notifications:
                        Intent b = new Intent(Scanner.this, Chart.class);
                        startActivity(b);
                        //Scanner.this.finish();
                        break;
                }
                return false;
            }
        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        presult = (TextView) findViewById(R.id.res);
//        presultbf = (TextView) findViewById(R.id.resbefore);                //檢查bf
        assetManager = getAssets();
        file = getFilesDir();
        activity = this;
        initView();
        initListener();

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        manual = (Button) findViewById(R.id.manualinput);
        manual.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                manualData();
            }
        });

        enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmData();
            }
        });
        Log.d("dit3", String.valueOf(getFilesDir()));


//生成檔案
        AssetManager assetManager = getAssets();
        File file = getFilesDir();
        InternalStorage internalStorage = new InternalStorage(assetManager, file);
        datapath = getFilesDir() + "/raw";
        internalStorage.checkFile(new File(datapath));

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data.csv", MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writeData2CSVThread = new WriteData2CSVThread(fos);


        Search search = new Search();
        Delete delete = new Delete(this);
        Revise revise = new Revise(this);
        Clear clear = new Clear();
        ReadCSVThread readCSVThread = new ReadCSVThread(this);

        transfer = new Transfer();
        transfer.setWriteData2CSVThread(writeData2CSVThread);
        transfer.setSearch(search);
//        Log.d("hey3", String.valueOf(readCSVThread.getTest()));
        transfer.setReader(readCSVThread);

        String[] filelist = fileList();
        for (int i = 0; i < filelist.length; i++) {
            Log.d("list", filelist[i]);
        }
    }



    protected void manualData() {
        Intent intent = new Intent(this, ScannerManualInput.class);
        startActivity(intent);
        //        transfer();
    }

    protected void confirmData() {
        Intent intent = new Intent(this, ScannerFinalResult.class);
        startActivity(intent);
    }


    private void initView() {
        mBtnPic = (Button) findViewById(R.id.btn_take_pic);
        mShowImage = (ImageView) findViewById(R.id.show_image);
    }

    private void initListener() {
        mBtnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionCode) {
            //假如允許了
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isCameraPermission = true;
                //do something
                Toast.makeText(this, "感謝賜予權限！", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(Scanner.this, TakePicActivity.class), GetPhotoCode);
            }
            //假如拒絕了
            else {
                isCameraPermission = false;
                //do something
                Toast.makeText(this, "CAMERA權限FAIL，請給權限", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //創造檔案名稱、和存擋路徑
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void openCamera() {
        //已獲得權限
        if (isCameraPermission) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.d("checkpoint", "error for createImageFile 創建路徑失敗");
            }
            //成功創建路徑的話
            if (photoFile != null) {
                Intent intent = new Intent(Scanner.this, TakePicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", photoFile.getAbsolutePath());
                intent.putExtras(bundle);
                startActivityForResult(intent, GetPhotoCode);
            }
        }
        //沒有獲得權限
        else {
            getPermission();
        }
    }

    private void getPermission() {
        //檢查是否取得權限
        final int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        //沒有權限時
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            isCameraPermission = false;
            ActivityCompat.requestPermissions(Scanner.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PermissionCode);
        } else { //已獲得權限
            isCameraPermission = true;
            openCamera();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GetPhotoCode) {
            setPic(imageFilePath);
      roi = conver.imagev2Mat(mShowImage);                                 //++
      poss = new Process(roi);                                             //++
      poss.compress(roi.cols()/2,roi.rows()/2);                     //++
      imageView.setImageBitmap(poss.getBitmap());                        //++
      Tess = new TesseractDetect(assetManager,file);                       //++
      String output = Tess.detectFromBitmap(poss.getBitmap());             //++
      result.setText(output);                                              //++
        }
    }

    private void setPic(String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = mShowImage.getWidth();
        int targetH = mShowImage.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        mShowImage.setImageBitmap(bitmap);

        opencv_core.Mat roi = conver.bitmap2Mat(bitmap);

        poss = new Process(roi);
        Log.d("width", String.valueOf(roi.cols()));
        Log.d("height", String.valueOf(roi.rows()));

        poss.process(221, 19);
        mShowImage.setImageBitmap(poss.getBitmap());
        Log.d("after", "ok");

        //++
        Tess = new TesseractDetect(assetManager, file);
        String output = Tess.detectFromBitmap(poss.getBitmap());
        String outputbf = Tess.detectFromBitmap_before(poss.getBitmap());               //檢查bf
        presult.setText(output);
        presultbf.setText(outputbf);
        //檢查bf


        }

}







