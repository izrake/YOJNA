package prasant.com.yojna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        Intent i=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.about_us) {
            displayInfo();
            return true;
        }
        if (id == R.id.contact_info) {
            contactInfo();
            return true;
        }
        if(id==R.id.createDb){
            startActivityForResult(new Intent(this,CreateDbDemo.class),0);
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayInfo() {
        startActivityForResult(new Intent(this, AboutUs.class), 0);
    }

    public void contactInfo() {
        startActivityForResult(new Intent(this, InfoUs.class), 0);

    }

    public void ActivityOpen(View v){
        switch (v.getId()){
            case R.id.button:
                i=new Intent(this,VikasYojna.class);
                startActivityForResult(i, 500);

                break;
            case R.id.button2:



        }

    }
}



