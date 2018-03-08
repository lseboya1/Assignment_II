package za.ac.cut.assignment_ii;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Contact_Details extends AppCompatActivity {

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ValueEventListener childEventListener;

    EditText contact_name;
    TextView contact_name_info;
    ImageView callphone;
    ImageView location_fin;
    ImageView webSite;
    UserContact userContact;

    String call;
    String location;
    String web;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__details);

        contact_name = (EditText)findViewById(R.id.contact_name);
        contact_name_info = (TextView)findViewById(R.id.contact_name_info);
        callphone = (ImageView)findViewById(R.id.call);
        location_fin = (ImageView)findViewById(R.id.location);
        webSite = (ImageView)findViewById(R.id.webSite);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("UserContact");
    }

    public void Search(View view){


        final String name = contact_name.getText().toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Log.i("info", snapshot.toString());

                    userContact = snapshot.getValue(UserContact.class);

                    if(Objects.equals(name, userContact.getName())){

                        contact_name_info.setText("Contact detail for "+ userContact.getName());
                        call = userContact.getPhoneNumber();
                        location = userContact.getLocation();
                        web = userContact.getAddress();

                        callphone.setVisibility(View.VISIBLE);
                        location_fin.setVisibility(View.VISIBLE);
                        webSite.setVisibility(View.VISIBLE);

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void Call(View view){

        Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                Uri.fromParts("tel", call, null));
        startActivity(phoneIntent);

    }

    public void Location(View view){

    }

    public void WebSite(View view){

//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.cut.ac.za"));
//        startActivity(browserIntent);

        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
        search.putExtra(SearchManager.QUERY, web);
        startActivity(search);
    }
}
