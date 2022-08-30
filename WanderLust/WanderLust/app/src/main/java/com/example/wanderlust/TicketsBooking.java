package com.example.wanderlust;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TicketsBooking extends AppCompatActivity {
    private Button proceed;
    private EditText Name,From,To,Contact,Aadhar,datee;
    DatabaseReference appointDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_booking);

        proceed=findViewById(R.id.proceed_btn);
        Name=findViewById(R.id.name_txt_tiket);
        From=findViewById(R.id.from_txt);
        To=findViewById(R.id.to_txt);
        Contact=findViewById(R.id.contact_txt_tiket);
        Aadhar=findViewById(R.id.aadhar_txt);
        datee=findViewById(R.id.date_txt);

        appointDbRef = FirebaseDatabase.getInstance().getReference().child("Appointment_Db");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAppointment();

            }

            private void insertAppointment() {
                String name = Name.getText().toString();
                String from = From.getText().toString();
                String to = To.getText().toString();
                String contact= Contact.getText().toString();
                String aadhar = Aadhar.getText().toString();
                String date = datee.getText().toString();

                TicketBooking appointment_db=new TicketBooking(name,from,to,aadhar,contact,date);
                appointDbRef.push().setValue(appointment_db);
                Toast.makeText(TicketsBooking.this,"Ticket hase been booked Sucessfully",Toast.LENGTH_LONG)
                        .show();
                Name.setText("");
                From.setText("");
                Aadhar.setText("");
                Contact.setText("");
                datee.setText("");
                To.setText("");
            }
        });

    }
}