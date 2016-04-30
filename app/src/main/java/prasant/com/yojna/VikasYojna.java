package prasant.com.yojna;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;

public class VikasYojna extends AppCompatActivity {
    private ListView listView;
    private String[] stringArray;
    private ArrayAdapter adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vikas_yojna);


        listView=(ListView)findViewById(R.id.listView);

      stringArray=getResources().getStringArray(R.array.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                SQLiteDatabase db=openOrCreateDatabase("VikasDB", Context.MODE_PRIVATE, null);

                StringBuffer buffer=new StringBuffer();
                if (position==1){
                    Cursor c=db.rawQuery("SELECT * FROM detail WHERE id= 1", null);
                    if(c.moveToFirst())
                    {
                        buffer.append("Title: "+c.getString(0)+"\n");
                        buffer.append("Detail: "+c.getString(1)+"\n");
                        buffer.append("Id: "+c.getString(2)+"\n\n");
                    }

                    showMessage("Vikas yona",buffer.toString());
                }


              //  String itemValue = (String) listView.getItemAtPosition(position);
                // Show Alert
                //Toast.makeText(getApplicationContext(),
                  //      "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                   //     .show();
            }
        });

        // Binding resources Array to ListAdapter


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vikas_yojna, menu);
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
        return super.onOptionsItemSelected(item);
    }
    public void showMessage(String title,String mess ){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(mess);
        builder.show();
    }
}
