package com.example.fss_info_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        Button button1 = findViewById(R.id.btnLogin);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText1.getText().toString().trim();
                String password = editText2.getText().toString();
                Log.d("MainActivity", "Username: " + username);
                Log.d("MainActivity", "Password: " + password);

                // Vérifier si l'adresse e-mail est valide
                String userNamePattern = "[a-zA-Z0-9._ -]+";
                String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
                if ((username.matches(emailPattern)) || (username.matches(userNamePattern))) {
                    // Si les identifiants sont valides, vérifiez si le mot de passe est correct
                    // Définir le motif pour le mot de passe
                    String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{10,}$";
                    if (password.matches(passwordPattern)) {
                        Log.d("MainActivity", "Password is valid");
                        // Si les identifiants sont valides, lancez DashboardActivity
                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                   }
                } else {
                    Log.d("MainActivity", "Username is invalid");
                    Toast.makeText(getApplicationContext(), "Nom d'utilisateur invalide", Toast.LENGTH_SHORT).show();
                }
        }
        });
    }
}