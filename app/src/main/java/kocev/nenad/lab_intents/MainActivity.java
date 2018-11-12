package kocev.nenad.lab_intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_EXPLICIT_ACTIVITY = 2;
    public static final int REQUEST_CODE_SELECTED_IMAGE = 3;

    private String resultFromExplicitIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_EXPLICIT_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                Bundle extras = data.getExtras();
                if(extras != null){
                    resultFromExplicitIntent = extras.getString(ExplicitActivity.RESULT_CONTENT_NAME);
                    TextView textView = findViewById(R.id.tvContent);
                    textView.setText(resultFromExplicitIntent);
                }
            }
        }
        else if(requestCode == REQUEST_CODE_SELECTED_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                if(data.getData() != null){
                    Uri image = data.getData();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(image, "image/*");
                    String title = getResources().getString(R.string.share_image);
                    Intent chooser = Intent.createChooser(intent, title);
                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(chooser);
                    }
                }
            }
        }
    }

    public void onExplicitButtonClick(View view){
        Intent intent = new Intent(getApplicationContext(), ExplicitActivity.class);
        startActivityForResult(intent, REQUEST_CODE_EXPLICIT_ACTIVITY);
    }

    public void onImplicitButtonClick(View view){
        Intent intent = new Intent();
        intent.setAction("kocev.nenad.lab_intents.IMPLICIT_ACTION");
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void onSendButtonClick(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        String subject = "MPiP Send Title";
        String body = "Content send from MainActivity";
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        String title = getResources().getString(R.string.send_action);
        Intent chooser = Intent.createChooser(intent, title);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(chooser);
        }
    }

    public void onSelectPictureClick(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CODE_SELECTED_IMAGE);
        }
    }
}
