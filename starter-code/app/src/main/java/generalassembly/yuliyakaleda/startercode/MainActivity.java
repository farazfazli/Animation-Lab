package generalassembly.yuliyakaleda.startercode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mWishEditText;
    private TextView mWishTextView;
    private Button mButton;

    private ViewGroup mViewGroup;

    private TextView mTextView;

    private static final int MIN_CHARACTER_COUNT = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWishEditText = (EditText) findViewById(R.id.edittext);
        mWishEditText.setMaxLines(1);

        mButton = (Button) findViewById(R.id.button);
        mWishTextView = (TextView) findViewById(R.id.textview);

        mViewGroup = (ViewGroup) findViewById(R.id.frameLayout);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String wishText = mWishEditText.getText().toString();

        if (wishText.trim().length() > MIN_CHARACTER_COUNT) {

            mWishTextView.setText(wishText);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_and_fade_in);
            mWishTextView.startAnimation(animation);

            mTextView = new TextView(this);
            mTextView.setText(wishText);

            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    log("started");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    log("finished");
                    addToBottom();
                    clearEditText();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    log("repeating");
                }
            });
        }
    }

    private void log(String thing) {
        Log.i("[Animation]", "Animation " + thing + "!");
    }

    private void addToBottom() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        mTextView.startAnimation(animation);
        mViewGroup.addView(mTextView, 0); // the zero here adds to the top instead of the bottom
    }

    private void clearEditText() {
        mWishEditText.setText("");
    }
}
