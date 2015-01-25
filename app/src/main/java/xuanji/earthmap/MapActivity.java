package xuanji.earthmap;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MapActivity extends ActionBarActivity {

    OrientationEventListener myOrientationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        SensorManager sMgr;
        sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);

        Sensor rotation;
        rotation = sMgr.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);



        SensorEventListener rotation_listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                TextView t_i = (TextView) findViewById(R.id.text_rotation_i);
                TextView t_j = (TextView) findViewById(R.id.text_rotation_j);
                TextView t_k = (TextView) findViewById(R.id.text_rotation_k);

                float i = event.values[0];
                float j = event.values[1];
                float k = event.values[2];

                t_i.setText(String.valueOf(i));
                t_j.setText(String.valueOf(j));
                t_k.setText(String.valueOf(k));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.w("rotation", "accuracy changed");
            }
        };
        sMgr.registerListener(rotation_listener, rotation, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
