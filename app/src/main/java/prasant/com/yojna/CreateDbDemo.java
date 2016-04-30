package prasant.com.yojna;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateDbDemo extends AppCompatActivity {
EditText editTitle;
    EditText editDetail,editId;
    Button btnCreate,btnModify,btnDelete,btnView,btndrop;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_db_demo);
        editDetail=(EditText)findViewById(R.id.editDetail);
        editTitle=(EditText)findViewById(R.id.editTitle);
        editId=(EditText)findViewById(R.id.editId);

        btndrop=(Button)findViewById(R.id.drop_table);
        btnCreate=(Button)findViewById(R.id.btnCreate);
        btnDelete=(Button)findViewById((R.id.btnDelete));
        btnModify=(Button)findViewById(R.id.btnModify);
        btnView=(Button)findViewById((R.id.btnView));
        db=openOrCreateDatabase("VikasDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS detail(title VARCHAR,detail VARCHAR,id VARCHAR);");
//performin events on Click of button
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editId.getText().toString().trim().length() == 0 || editDetail.getText().toString().trim().length() == 0
                        || editTitle.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please Enter All Values");
                    return;
                }

                db.execSQL("INSERT INTO detail VALUES('" + editTitle.getText() + "','" + editDetail.getText() +
                        "','" + editId.getText() + "');");
                showMessage("Success", "Record added");
                clearText();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM detail",null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Title: "+c.getString(0)+"\n");
                    buffer.append("Detail: "+c.getString(1)+"\n");
                    buffer.append("Id: "+c.getString(2)+"\n\n");
                }
                showMessage("Yojnas Detail", buffer.toString());
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editId.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Id");
                    return;
                }
                // Searching roll number
                Cursor c=db.rawQuery("SELECT * FROM detail WHERE id='"+editId.getText()+"'", null);
                if(c.moveToFirst())
                {
                    // Modifying record if found
                    db.execSQL("UPDATE detail SET title='"+editTitle.getText()+"',detail='"+editDetail.getText()+
                            "' WHERE id='"+editId.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editId.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                // Searching roll number
                Cursor c=db.rawQuery("SELECT * FROM detail WHERE id='"+editId.getText()+"'", null);
                if(c.moveToFirst())
                {
                    // Deleting record if found
                    db.execSQL("DELETE FROM detail WHERE id='"+editId.getText()+"'");
                    showMessage("Success", "Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }
        });
        btndrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DROP TABLE details");
                showMessage("Table Dropped","Deleted");
            }
        });

    }
           public void showMessage(String title,String mess ){
               AlertDialog.Builder builder=new AlertDialog.Builder(this);
               builder.setCancelable(true);
               builder.setTitle(title);
               builder.setMessage(mess);
               builder.show();
           }

    public void clearText()
    {
        editTitle.setText("");
        editDetail.setText("");
        editId.setText("");
        editTitle.requestFocus();
    }

}
