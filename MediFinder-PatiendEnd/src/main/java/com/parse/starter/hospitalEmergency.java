package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class hospitalEmergency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_hospital_emergency);
        final String activeUser= ParseUser.getCurrentUser().getUsername();


        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Emergency");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    ArrayList<patientDetails> patients = new ArrayList<patientDetails>();
                    final ListView patientView=(ListView)findViewById(R.id.emergencyHospital);
                    for(ParseObject object:objects){
                        patientDetails patient=new patientDetails();
                        String status=object.getString("status");
                        String hosName=object.getString("hospitalName");
                        String name=object.getString("patientName");
                        String lat=object.getString("patientLat");
                        String lon=object.getString("patientLon");

                        String location=lat+" "+lon;
                        if((String.valueOf(hosName).equals(activeUser.toString())) && (status.equals("TRUE"))) {
                            Log.i("pagal hai saala",hosName);
                            patient.setName(name);
                            patient.setLocation(location);
                            patients.add(patient);
                        }
                    }
                    patientView.setAdapter(new MyAdapter2(hospitalEmergency.this, patients));

                }
            }
        });

    }

}
