package space.works.crackscreen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class CrackScreen extends Service {
    Button mButton;
    ImageView mImage;
    InterstitialAd mInterstitialAd;

    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2059382170925909/6634720855");
        AdRequest interstitialRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(interstitialRequest);

        this.mButton = new Button(this);
        this.mImage = new ImageView(this);
        this.mButton.setText(".");
        this.mButton.setTextColor(-12303292);
        this.mButton.setVisibility(0);
        this.mButton.setBackgroundColor(0);
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                CrackScreen.this.stopSelf();
                mInterstitialAd.show();
            }
        });
        this.mImage.setImageResource(R.drawable.liquid_crystal_spill);
        this.mImage.setScaleType(ImageView.ScaleType.FIT_XY);
        WindowManager.LayoutParams localLayoutParams1 = new WindowManager.LayoutParams(2006);
        localLayoutParams1.format = 1;
        WindowManager.LayoutParams localLayoutParams2 =
                new WindowManager.LayoutParams(-2, -2, 2003, 40, -3);
        localLayoutParams2.gravity = 51;
        localLayoutParams2.width = 50;
        localLayoutParams2.height = 50;
        localLayoutParams2.setTitle("Load Average");
        WindowManager localWindowManager = (WindowManager)getSystemService("window");
        localWindowManager.addView(this.mButton, localLayoutParams2);
        localWindowManager.addView(this.mImage, localLayoutParams1);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mButton != null) {
            ((WindowManager)getSystemService("window")).removeView(this.mButton);
            this.mButton = null;
            ((WindowManager)getSystemService("window")).removeView(this.mImage);
            this.mImage = null;
        }
    }
}