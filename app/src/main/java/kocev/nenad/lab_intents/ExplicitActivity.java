package kocev.nenad.lab_intents;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExplicitActivity extends AppCompatActivity {

    public static final String RESULT_CONTENT_NAME = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
    }

    public void btnOKClicked(View view){
        EditText editText = findViewById(R.id.etContent);
        String content = editText.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(RESULT_CONTENT_NAME, content);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void btnCancelClicked(View view){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
}
