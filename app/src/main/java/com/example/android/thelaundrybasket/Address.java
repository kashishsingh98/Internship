package com.example.android.thelaundrybasket;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Address extends FragmentActivity implements LocationListener {

    EditText edthome,edthouse,edtaddress,edtlandmark;
    TextView city,areaid,subarea;
    Button btnmylocation,btnaddaddress;
    String[] addresslist;
    ArrayList<SaveAddress> saveAddressList;
    String area ,areadiv;
    String address;
    private DatabaseReference databaseReference;
    static String SUMIT_CODE="1047";
    String[]playes ={"Choose From The City","Burdwan","Kolkata","Bangalore","Hyderabad"};
    String [] burdwan ={"Select an Area","Golapbag","Khosbagan"};
    String[] hyderabad={"Select an Area","Jalavihar","Charminar"};
    String[] kolkata={"Select an  Area","Dumdum","Howrah" };
    String[] bangalore = {"Select an Area","LalBagh","Wonderla"};
    String[] Golapbag ={"Select a SubArea", "Saraitikar","SNBose Boys Hostel" };
    String[] Khosbagan = {"Select a SubArea","Raniganj","Hospital Road"};
    String[] Jalavihar = {"Select a SubArea","MathurGanj","Sector 26"};
    String[] Charminar = {"Select a SubArea","Reniassance","Umang Colony"};
    String[] Dumdum = {"Select a SubArea","Akashwani Road","256Block Society"};
    String[] Howrah ={"Select a SubArea","Vidyasagar setu","Fort WIlliam"};
    String[] Lalbagh = {"Select a SubArea","MG Park","Paradise Society"};
    String[] Wonderla ={"Select a SubArea","Kempegowda","Moodalappa Road"};
    databaseReference= FirebaseDatabase.getInstance().getReference("address");

    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        edthome=findViewById(R.id.AddressLabel);
        edthouse=findViewById(R.id.AddressLabel2);
        edtaddress=findViewById(R.id.AddressLabel3);
        edtlandmark=findViewById(R.id.AddressLabel4);
        city =findViewById(R.id.textplace);
        areaid=findViewById(R.id.textArea);
        subarea =findViewById(R.id.textsubarea);
        btnmylocation=findViewById(R.id.myloc);
        btnaddaddress=findViewById(R.id.addaddress);
        saveAddressList = new ArrayList<>();
        edthome.getText().toString();
        edthouse.getText().toString();






        btnmylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();

            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog(playes,city);


            }
        });
        areaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area = city.getText().toString();
                if(city.getText().toString().equalsIgnoreCase("City")){
                    Toast.makeText(Address.this,"Please Choose a city First",Toast.LENGTH_SHORT).show();
                }
                else if (area.equalsIgnoreCase("burdwan"))
                {
                    getDialog(burdwan,areaid);
                }
                else if(area.equalsIgnoreCase("kolkata"))
                {
                    getDialog(kolkata,areaid);
                }
                else if(area.equalsIgnoreCase("hyderabad"))
                {
                    getDialog(hyderabad,areaid);
                }
                else if(area.equalsIgnoreCase("bangalore"))
                {
                    getDialog(bangalore,areaid);
                }

            }
        });
        subarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                areadiv=areaid.getText().toString();
                if(area.equalsIgnoreCase("City"))
                {
                    Toast.makeText(Address.this,"Please Choose a City First",Toast.LENGTH_SHORT).show();
                }
                else if(areadiv.equalsIgnoreCase("Area")){
                    Toast.makeText(Address.this,"Please Choose a Area First",Toast.LENGTH_SHORT).show();
                }
                else if (areadiv.equalsIgnoreCase("golapbag"))
                {
                    getDialog(Golapbag,subarea);
                }
                else if(areadiv.equalsIgnoreCase("khosbagan"))
                {
                    getDialog(Khosbagan,subarea);
                }
                else if(areadiv.equalsIgnoreCase("howrah"))
                {
                    getDialog(Howrah,subarea);
                }
                else if(areadiv.equalsIgnoreCase("dumdum"))
                {
                    getDialog(Dumdum,subarea);
                }
                else if(areadiv.equalsIgnoreCase("jalavihar"))
                {
                    getDialog(Jalavihar,subarea);
                }
                else if(areadiv.equalsIgnoreCase("charminar"))
                {
                    getDialog(Charminar,subarea);
                }
                else if(areadiv.equalsIgnoreCase("lalbagh"))
                {
                    getDialog(Lalbagh,subarea);
                }
                else  if (areadiv.equalsIgnoreCase("wonderla"))
                {
                    getDialog(Wonderla,subarea);
                }
            }
        });
        btnaddaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edthouse.getText().toString().isEmpty()|| edthome.getText().toString().isEmpty() || edtlandmark.getText().toString().isEmpty()|| edtaddress.getText().toString().isEmpty()|| city.getText().toString().equalsIgnoreCase("City")||areaid.getText().toString().equalsIgnoreCase("Area")||subarea.getText().toString().equalsIgnoreCase("Sub Area"))
                {
                    Toast.makeText(Address.this,"Please Fill Up All the Details",Toast.LENGTH_SHORT).show();
                }
                else {
                    SaveAddress saveAddress = new SaveAddress(edthome.getText().toString(), edthouse.getText().toString(), edtlandmark.getText().toString(), edtaddress.getText().toString(), city.getText().toString(), areaid.getText().toString(), subarea.getText().toString());
                    saveAddressList.add(saveAddress);
                    //Add to firebase the list saveAddres
                    Toast.makeText(Address.this, "Address Saved",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Address.this,NewOrder1Activity.class);
                    intent.putExtra(SUMIT_CODE,saveAddressList);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1010 && resultCode==RESULT_OK)
        {

        }
    }

    public void getDialog(String[] locate, TextView place)
    {
        Dialog dialog = new Dialog(Address.this);
        dialog.setContentView(R.layout.customlayout);
        RecyclerView recyclerView=dialog.findViewById(R.id.reViewSumit);
        recyclerView.setLayoutManager(new LinearLayoutManager(Address.this));
        RecyclerAdapter adapter=new RecyclerAdapter(locate,Address.this,place,dialog);
        recyclerView.setAdapter(adapter);
        dialog.show();

    }


    public  void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);

        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<android.location.Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            address= addresses.get(0).getAddressLine(0);
            addresslist= address.trim().split("\\s*,\\s*");
            edtaddress.setText(addresslist[0]);
            edtlandmark.setText(addresslist[1]);

        } catch (Exception e) {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}




