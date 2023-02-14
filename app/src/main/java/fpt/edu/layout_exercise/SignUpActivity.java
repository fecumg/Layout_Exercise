package fpt.edu.layout_exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    TextView tvSignIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvSignIn = findViewById(R.id.tvSignIn);

        tvSignIn.setOnClickListener(view -> this.switchToSignInActivity(view));
    }

    private void switchToSignInActivity(View view) {
        Intent intent = new Intent(view.getContext(), SignInActivity.class);
        startActivity(intent);
    }
}
