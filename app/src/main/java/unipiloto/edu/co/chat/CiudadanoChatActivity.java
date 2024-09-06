package unipiloto.edu.co.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CiudadanoChatActivity extends AppCompatActivity {

    private ArrayList<String> historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        historial = new ArrayList<>();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ciudadano_chat);
        Intent intent = getIntent();
        if (intent.getStringArrayListExtra("historial") != null) {
            historial = intent.getStringArrayListExtra("historial");
        }
        TextView messageView = (TextView)findViewById(R.id.message);
        for (String message : historial) {
            messageView.setText(messageView.getText() + "\n" + message);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onSendMessage(View view) {
        EditText messageView = (EditText)findViewById(R.id.writtenMessage);
        String messageText = "Ciudadano: "+messageView.getText().toString();
        historial.add(messageText);
        Intent intent = new Intent(this, PlaneadorChatActivity.class);
        intent.putExtra("historial",historial);
        startActivity(intent);
    }
}