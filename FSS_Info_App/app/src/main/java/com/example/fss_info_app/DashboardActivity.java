package com.example.fss_info_app;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import androidx.appcompat.app.AppCompatActivity;


@SuppressWarnings("deprecation")
public class DashboardActivity extends AppCompatActivity {

    TextView textView1;
    private static String serverIP="192.168.1.16";
    private static String txtPort="1000";
    private EditText txtMsg;
    FrameLayout timeTable;
    FrameLayout grades;
    Button button1;
    Button send;
    Button info;
    int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String username = getIntent().getStringExtra("username");
        Toast.makeText(DashboardActivity.this, "Welcome " + username + "!",Toast.LENGTH_SHORT).show();
        textView1 = (TextView) findViewById(R.id.textView1);
        button1 = (Button) findViewById(R.id.button1);
        send=(Button) findViewById(R.id.btnSend);
        txtMsg = (EditText) findViewById(R.id.txtMsg);
        textView1.setText("Welcome " + username + "!");
        info=(Button)findViewById(R.id.btninfo);
        timeTable=(FrameLayout)findViewById(R.id.icon_emploi);
        grades=(FrameLayout)findViewById(R.id.icon_grades);
        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the user clicks the "Logout" button, go back to the MainActivity
                Intent intent = new Intent(DashboardActivity.this, TimeTable.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the user clicks the "Logout" button, go back to the MainActivity
                Intent intent = new Intent(DashboardActivity.this, Grades.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the user clicks the "Logout" button, go back to the MainActivity
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                Thread thread = new Thread(() -> {

                    try {

                        System.out.println("running");
                        String str="";
                        Socket socket = new Socket(serverIP.toString(), Integer.parseInt(txtPort.toString()));
                        System.out.println("socket creation");
                        DataInputStream din=new DataInputStream(socket.getInputStream());
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        System.out.println("dataOutputStream");
                        dataOutputStream.writeUTF(txtMsg.getText().toString());
                        System.out.println("writeUTF");
                        dataOutputStream.flush();
                        str=din.readUTF();
                        System.out.println("Server says: "+str);
                        dataOutputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ouvrir le site de la faculté dans le navigateur
                String url = "https://fss.rnu.tn/fra/home"; // l'URL du site de la faculté
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }

}


