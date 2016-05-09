package mind.com.animations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View clouds = findViewById(R.id.clouds1);
        final View loading = findViewById(R.id.text_loading);
        final View airplane = findViewById(R.id.airplane);
        final View clouds2 = findViewById(R.id.clouds2);

        ObjectAnimator backgroundAnimator = ObjectAnimator.ofFloat(clouds, "x", 1200, -1200);
        backgroundAnimator.setDuration(3000);
        backgroundAnimator.setRepeatMode(ValueAnimator.RESTART);
        backgroundAnimator.setRepeatCount(ValueAnimator.INFINITE);
        backgroundAnimator.setInterpolator(new LinearInterpolator());
        backgroundAnimator.start();

        //Start airplane
        final Animator animText = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.move_y);
        animText.setTarget(airplane);
        animText.start();

        //Fade loading text
        final Animator loadingText = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.fade);
        loadingText.setTarget(loading);
        loadingText.start();

        //Keep ariplane on top
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animator animText = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.move_y2);
                animText.setTarget(airplane);
                animText.start();
            }
        },1500);

        //Start second fragment of clouds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clouds2.setVisibility(View.VISIBLE);

                ObjectAnimator backgroundAnimator = ObjectAnimator.ofFloat(clouds2, "x", 1200, -1200);
                backgroundAnimator.setDuration(3000);
                backgroundAnimator.setRepeatMode(ValueAnimator.RESTART);
                backgroundAnimator.setRepeatCount(ValueAnimator.INFINITE);
                backgroundAnimator.setInterpolator(new LinearInterpolator());
                backgroundAnimator.start();
            }
        },1500);

        //Test only
        loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
