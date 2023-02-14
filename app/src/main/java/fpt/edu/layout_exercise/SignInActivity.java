package fpt.edu.layout_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    TextView tvHere;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tvHere = findViewById(R.id.tvHere);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        tvHere.setOnClickListener(view -> this.switchToSignUpActivity(view));
        tvForgotPassword.setOnClickListener(view -> this.switchToForgotPasswordActivity(view));
    }

    private void switchToSignUpActivity(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        startActivity(intent);
    }

    private void switchToForgotPasswordActivity(View view) {
        Intent intent = new Intent(view.getContext(), ForgotPasswordActivity.class);
        startActivity(intent);
    }
}