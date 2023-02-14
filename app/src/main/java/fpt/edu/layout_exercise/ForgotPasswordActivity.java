package fpt.edu.layout_exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView tvHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tvHere = findViewById(R.id.tvHere);

        tvHere.setOnClickListener(view -> this.switchToSignUpActivity(view));
    }

    private void switchToSignUpActivity(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        startActivity(intent);
    }
}
