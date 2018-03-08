package za.ac.cut.assignment_ii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void New_Contact(View view){


        Intent intent = new Intent(this, New_Contact.class);
        startActivity(intent);
    }

    public void Edit_Contact(View view){
        Intent intent = new Intent(this, Edit_Contact.class);
        startActivity(intent);

    }

    public void Contract_info(View view){
        Intent intent = new Intent(this, Contact_Details.class);
        startActivity(intent);

    }
}
