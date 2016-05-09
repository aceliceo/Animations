package mind.com.animations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity2 extends AppCompatActivity {

    private int currentCity = 0;
    View city1;
    View city2;
    View city3;
    View city4;
    ObjectAnimator backgroundAnimator;
    ObjectAnimator mainBackgroundAnimator;
    boolean isFirstLoop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final View loading = findViewById(R.id.text_loading);
        final View airplane = findViewById(R.id.airplane);
        city1 = findViewById(R.id.city1);
        city2 = findViewById(R.id.city2);
        city3 = findViewById(R.id.city3);
        city4 = findViewById(R.id.city4);

//        mainBackgroundAnimator = ObjectAnimator.ofFloat(city1, "x", 0, -1200);
//        mainBackgroundAnimator.setDuration(3000);
////        mainBackgroundAnimator.setRepeatMode(ValueAnimator.INFINITE);
//        mainBackgroundAnimator.setRepeatCount(0);
//        mainBackgroundAnimator.setInterpolator(new LinearInterpolator());
//        mainBackgroundAnimator.start();

        loadCity();

        //Start airplane
        final Animator animText = AnimatorInflater.loadAnimator(MainActivity2.this, R.animator.move_y);
        animText.setTarget(airplane);
        animText.start();

        //Fade loading text
        final Animator loadingText = AnimatorInflater.loadAnimator(MainActivity2.this, R.animator.fade);
        loadingText.setTarget(loading);
        loadingText.start();

        //Keep ariplane on top
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animator animText = AnimatorInflater.loadAnimator(MainActivity2.this, R.animator.move_y2);
                animText.setTarget(airplane);
                animText.start();
            }
        },1500);

        //Test only
        loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadCity(){
        View previousView = city4;
        View nextView = city1;

        switch (currentCity){
            case 0:
                nextView = city2;
                previousView = city1;
                break;
            case 1:
                nextView = city3;
                previousView = city2;
                break;
            case 2:
                nextView = city4;
                previousView = city2;
                break;
            case 3:
                nextView = city1;
                previousView = city4;
                break;
        }

        final View finalPreviousView = previousView;
        final View finalNextView = nextView;
        int delay = 0;

        if(isFirstLoop){
            isFirstLoop = false;
        }else{
            delay = 2300;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentCity == 3){
                    currentCity = 0;
                }else{
                    currentCity++;
                }

//                mainBackgroundAnimator.cancel();

                backgroundAnimator = ObjectAnimator.ofFloat(finalNextView, "x", 1200, -1200);
                backgroundAnimator.setDuration(4000);
                backgroundAnimator.setRepeatMode(ValueAnimator.RESTART);
                backgroundAnimator.setRepeatCount(ValueAnimator.INFINITE);
                backgroundAnimator.setInterpolator(new LinearInterpolator());
                backgroundAnimator.start();

                finalNextView.setVisibility(View.VISIBLE);
//                finalPreviousView.setVisibility(View.INVISIBLE);
                hideView(finalPreviousView);

                loadCity();
            }
        },delay);
    }

    private void hideView(final View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.INVISIBLE);
            }
        },1500);
    }
}
