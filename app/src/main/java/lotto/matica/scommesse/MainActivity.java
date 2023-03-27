package lotto.matica.scommesse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.BuildConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lotto.matica.scommesse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    TextView txt;
    MusclesAdapter musclesAdapter;
    ExersiseAdapter exersiseAdapter;
    RecyclerView musclesList, exersiseList;
    ArrayList<Muscles> muscles;
    ArrayList<Exersise> exersises;
    List<Integer> id_grp= Base.getId_group();
    List<Integer> id_musle= Base.getId_musle();
    List<String> names= Base.getName_muscle();
    //List<String> name_exer= Base.getName_exer();
    //List<String> photo= Base.getPhoto();
    //List<Integer> descr= Base.getDescr();
    DBHelper dbHelper;
    private static final String FILE_NAME="MY_FILE_NAME";
    private static final String URL_STRING="URL_STRING";
    String url_FB;
    String url_SP;
    SQLiteDatabase database;
    SharedPreferences sPref;
    SharedPreferences.Editor ed;
    private FirebaseRemoteConfig mfirebaseRemoteConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //проверка сохранена ли ссылка
        DBHelper dbHelper = new DBHelper(this);
        database= dbHelper.getWritableDatabase();
        url_SP = getSharedPrefStr();
        if(url_SP=="") {
            //подключение к FireBase
            getFireBaseUrlConnection();
            getURLStr();
        }else{
            //проверка на подключение к интернету
            if(!hasConnection(this)){
                Intent intent = new Intent(MainActivity.this, InernetNone.class);
                startActivity(intent);
            }
            else{//запускаем WebView
                browse(url_SP);
            }
        }
    }

    //включение WebView
    public void browse(String url){
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
    //заполнение данных мышц
    public void getMuscleForGroup(int id_group){
        muscles.clear();
        for(int i = 0; i<12; i++){
            if(id_grp.get(i)==id_group){
                Muscles mMuscle = new Muscles(i+1,id_grp.get(i),names.get(i));
                muscles.add(mMuscle);
            }
        }
    }
    //заполнение данных упражнений
    public void getExForMuscles(int id_muscle){
        exersises.clear();
        ContentValues cv = new ContentValues();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, "muscle_id =="+id_muscle, null, null, null,null);
        if(cursor.moveToFirst()){
            int idIndex =cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex =cursor.getColumnIndex(DBHelper.KEY_NAME);
            int id_muscleIndex =cursor.getColumnIndex(DBHelper.KEY_MUSCLE_ID);
            int descIndex =cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION);
            int photoIndex =cursor.getColumnIndex(DBHelper.KEY_PHOTO);
            do {
                Exersise mExersise = new Exersise(cursor.getInt(idIndex),
                        cursor.getInt(id_muscleIndex), cursor.getString(nameIndex), cursor.getString(descIndex),
                        cursor.getString(photoIndex));
                exersises.add(mExersise);
            }while(cursor.moveToNext());
        }
        else{
            Log.d("mLog","0 rows");
        }

        cursor.close();
    }
    //проверка эмулятора
    private boolean checkIsEmu() {

        if (BuildConfig.DEBUG) return false;
        String phoneModel = Build.MODEL;
        String buildProduct = Build.PRODUCT;
        String buildHardware = Build.HARDWARE;
        String brand = Build.BRAND;
        return (Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.toLowerCase(Locale.getDefault()).contains("droid4x")
                || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || buildHardware.equals("goldfish")
                || brand.contains("google")
                || buildHardware.equals("vbox86")
                || buildProduct.equals("sdk")
                || buildProduct.equals("google_sdk")
                || buildProduct.equals("sdk_x86")
                || buildProduct.equals("vbox86p")
                || Build.BOARD.toLowerCase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.toLowerCase(Locale.getDefault()).contains("nox")
                || buildHardware.toLowerCase(Locale.getDefault()).contains("nox")
                || buildProduct.toLowerCase(Locale.getDefault()).contains("nox"))
                || (brand.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                ||"google_sdk".equals(Build.PRODUCT)
                || "sdk_gphone_x86_arm".equals(Build.PRODUCT)
                ||"sdk_google_phone_x86".equals(Build.PRODUCT);
    }

    //проверка интернет подключения
    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    //получение ссылки и обработка вызова заглушки/WebView
    public void getURLStr(){
        try {
            mfirebaseRemoteConfig.fetchAndActivate()
                    .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                        @Override
                        public void onComplete(@NonNull Task<Boolean> task) {
                            if (task.isSuccessful()) {
                                Log.i("Fire", String.valueOf(task.getResult()));
                                url_FB = mfirebaseRemoteConfig.getString("url");
                                if("google_sdk".equals(Build.PRODUCT))
                                {
                                    Log.d("Emu","true");
                                }
                                else Log.d("Emu","false");
                                if (url_FB.isEmpty()) {
                                    Log.i("Fire", "empty string");
                                    if (url_FB.isEmpty() || checkIsEmu()) plug();
                                    else browse(url_FB);
                                } else {
                                    url_FB = mfirebaseRemoteConfig.getString("url");
                                    Log.i("Fire", url_FB);
                                    saveToSP();
                                }

                            } else {
                                url_FB = "";
                                plug();
                                Log.i("Fire", "null2");
                            }
                        }
                    });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            Intent intent = new Intent(MainActivity.this, InernetNone.class);
            startActivity(intent);
        }
    }
    //получение локальной ссылки
    public String getSharedPrefStr(){
        sPref = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        String url_SP = sPref.getString(URL_STRING,"");
        return url_SP;
    }
    //подключение к Firebase
    public void getFireBaseUrlConnection(){
        //подключение к FireBase
        mfirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(10)
                .build();
        mfirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mfirebaseRemoteConfig.setDefaultsAsync(R.xml.url_values);
    }
    //вызыв зваглушки
    public void plug(){

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        txt = (TextView) findViewById(R.id.textView);
        //recycler for ex
        exersiseList = findViewById(R.id.RecyclerExersise);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        exersiseList.setLayoutManager(layoutManager2);
        //add muscles data
        exersises = new ArrayList<>();
        getExForMuscles(1);
        // recycler adapter
        exersiseAdapter = new ExersiseAdapter(exersises);
        //recycler for mus
        musclesList = findViewById(R.id.RecyclerMuscles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        musclesList.setLayoutManager(layoutManager);
        //recycler listener
        MusclesAdapter.OnMusclesClickListener onMusclesClickListener = new MusclesAdapter.OnMusclesClickListener() {
            @Override
            public void onMusclesClick( Muscles muscles) {
                getExForMuscles(muscles.getId());
                Log.d("Muscle", "put");
                exersiseList.setAdapter(exersiseAdapter);
            }
        };

        //add muscles data
        muscles = new ArrayList<>();
        getMuscleForGroup(1);
        // recycler adapter
        musclesAdapter = new MusclesAdapter(muscles,this, onMusclesClickListener);
        musclesList.setAdapter(musclesAdapter);
        //recycler for Exersise

        exersiseList.setAdapter(exersiseAdapter);
        //bottom navigation
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.chest:
                    txt.setText("Chest + triceps");
                    getMuscleForGroup(2);
                    musclesList.setAdapter(musclesAdapter);
                    getExForMuscles(6);
                    exersiseList.setAdapter(exersiseAdapter);
                    break;
                case R.id.legs:
                    txt.setText("Legs + shoulders");
                    getMuscleForGroup(1);
                    musclesList.setAdapter(musclesAdapter);
                    getExForMuscles(1);
                    exersiseList.setAdapter(exersiseAdapter);
                    break;
                case R.id.biceps:
                    txt.setText("Biceps + back");
                    getMuscleForGroup(3);
                    musclesList.setAdapter(musclesAdapter);
                    getExForMuscles(9);
                    exersiseList.setAdapter(exersiseAdapter);
                    break;
            }
            return true;
        });
    }
    //сохранение ссылки локально
    public void saveToSP(){
        ed = sPref.edit();
        ed.putString(URL_STRING, url_FB);
        ed.apply();
        browse(url_FB);
    }
}