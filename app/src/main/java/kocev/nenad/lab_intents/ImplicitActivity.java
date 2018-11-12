package kocev.nenad.lab_intents;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        TextView textView = findViewById(R.id.tvListOfApplications);

        PackageManager packageManager = getApplicationContext().getPackageManager();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MAIN);
        intentFilter.addCategory(Intent.CATEGORY_LAUNCHER);

        List<IntentFilter> outFilters = new ArrayList<IntentFilter>();
        outFilters.add(intentFilter);
        List<ComponentName> componentNames = new ArrayList<>();
        packageManager.getPreferredActivities(outFilters, componentNames, null);

        StringBuilder stringBuilder = componentNames.stream()
                .map(c ->{
                    String [] arr = c.getClassName().split("\\.");
                    return arr[arr.length - 1];
                })
                .collect(
                        StringBuilder::new,
                        (sb, name) -> sb.append(name).append(", "),
                        (sb1, sb2) -> sb1.append(sb2.toString())
                );
        textView.setText(stringBuilder.toString());
    }
}
