package com.example.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor Name : Dr. Ahsan Habib", "Hospital Address : Dhanmondi, Dhaka", "Exp : 10yrs", "Mobile No:0174658666", "500"},
            {"Doctor Name : Dr. Rubaiya Sultana", "Hospital Address : Banani, Dhaka", "Exp : 12yrs", "Mobile No:01898765432", "1500"},
            {"Doctor Name : Dr. Md. Tanvir Hossain", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "1000"},
            {"Doctor Name : Dr. Naznin Akter", "Hospital Address : Uttara, Dhaka", "Exp : 7yrs", "Mobile No:01699887766", "1100"},
            {"Doctor Name : Dr. Mahbub Alam", "Hospital Address : Gulshan, Dhaka", "Exp : 15yrs", "Mobile No:01566778899", "1800"}
    };

    private String[][] doctor_details2 = {
            {"Doctor Name : Dr. Nusrat Jahan", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", " 1200"},
            {"Doctor Name : Dr. Mahmudul Hasan", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "1500"},
            {"Doctor Name : Dr. Farhana Haque", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "1000"},
            {"Doctor Name : Dr. Saiful Islam", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "1100"},
            {"Doctor Name : Dr. Tasnim Ahmed", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "1300"}
    };

    private String[][] doctor_details3 = {
            {"Doctor Name : Dr. Ayesha Siddique", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", "1000"},
            {"Doctor Name : Dr. Kamrul Hasan", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "2000"},
            {"Doctor Name : Dr. Shirin Akter", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "1200"},
            {"Doctor Name : Dr. Zakir Hossain", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "1300"},
            {"Doctor Name : Dr. Tahmina Rahman", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "1500"}
    };

    private String[][] doctor_details4 = {
            {"Doctor Name : Dr. Mahfuz Rahman", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", "2500"},
            {"Doctor Name : Dr. Farzana Karim", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "5000"},
            {"Doctor Name : Dr. Imran Hossain", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "3500"},
            {"Doctor Name : Dr. Sultana Akter", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "3000"},
            {"Doctor Name : Dr. Tahmid Ahmed", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "4000"}
    };

    private String[][] doctor_details5 = {
            {"Doctor Name : Dr. Aminul Haque", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", "3000"},
            {"Doctor Name : Dr. Farhana Yasmin", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "6000"},
            {"Doctor Name : Dr. Mohammad Karim", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "4000"},
            {"Doctor Name : Dr. Sarwat Hossain", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "3500"},
            {"Doctor Name : Dr. Nasrin Akhter", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "4500"}
    };

    public String[][] doctors = {
            // doctor_details1
            {"Doctor Name : Dr. Ahsan Habib", "Hospital Address : Dhanmondi, Dhaka", "Exp : 10yrs", "Mobile No:0174658666", "500"},
            {"Doctor Name : Dr. Rubaiya Sultana", "Hospital Address : Banani, Dhaka", "Exp : 12yrs", "Mobile No:01898765432", "1500"},
            {"Doctor Name : Dr. Md. Tanvir Hossain", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "1000"},
            {"Doctor Name : Dr. Naznin Akter", "Hospital Address : Uttara, Dhaka", "Exp : 7yrs", "Mobile No:01699887766", "1100"},
            {"Doctor Name : Dr. Mahbub Alam", "Hospital Address : Gulshan, Dhaka", "Exp : 15yrs", "Mobile No:01566778899", "1800"},

            // doctor_details2
            {"Doctor Name : Dr. Nusrat Jahan", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", "1200"},
            {"Doctor Name : Dr. Mahmudul Hasan", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "1500"},
            {"Doctor Name : Dr. Farhana Haque", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "1000"},
            {"Doctor Name : Dr. Saiful Islam", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "1100"},
            {"Doctor Name : Dr. Tasnim Ahmed", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "1300"},

            // doctor_details3
            {"Doctor Name : Dr. Ayesha Siddique", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", "1000"},
            {"Doctor Name : Dr. Kamrul Hasan", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "2000"},
            {"Doctor Name : Dr. Shirin Akter", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "1200"},
            {"Doctor Name : Dr. Zakir Hossain", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "1300"},
            {"Doctor Name : Dr. Tahmina Rahman", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "1500"},

            // doctor_details4
            {"Doctor Name : Dr. Mahfuz Rahman", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", "2500"},
            {"Doctor Name : Dr. Farzana Karim", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "5000"},
            {"Doctor Name : Dr. Imran Hossain", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "3500"},
            {"Doctor Name : Dr. Sultana Akter", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "3000"},
            {"Doctor Name : Dr. Tahmid Ahmed", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "4000"},

            // doctor_details5
            {"Doctor Name : Dr. Aminul Haque", "Hospital Address : Dhanmondi, Dhaka", "Exp : 5yrs", "Mobile No:01712345678", "3000"},
            {"Doctor Name : Dr. Farhana Yasmin", "Hospital Address : Banani, Dhaka", "Exp : 15yrs", "Mobile No:01898765432", "6000"},
            {"Doctor Name : Dr. Mohammad Karim", "Hospital Address : Mirpur, Dhaka", "Exp : 8yrs", "Mobile No:01911223344", "4000"},
            {"Doctor Name : Dr. Sarwat Hossain", "Hospital Address : Uttara, Dhaka", "Exp : 6yrs", "Mobile No:01699887766", "3500"},
            {"Doctor Name : Dr. Nasrin Akhter", "Hospital Address : Gulshan, Dhaka", "Exp : 7yrs", "Mobile No:01566778899", "4500"}
    };




    TextView tv;
    Button btn;
    String[][] doctor_details ={};
    HashMap<String,String> item;
    ArrayList list ;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv =findViewById(R.id.textViewODTitle);
        btn=findViewById(R.id.buttonBMBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physician")==0) {
            doctor_details = doctor_details1;
        } else
         if (title.compareTo("Dietician")==0) {
             doctor_details = doctor_details2;
         } else
         if (title.compareTo("Dentist")==0) {
             doctor_details = doctor_details3;
         } else
         if (title.compareTo("Surgeon")==0) {
             doctor_details = doctor_details4;
         } else
             doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for (int i =0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees : "+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5",},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,}
        );
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

//        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
//                it.putExtra("Text1", title);
//                it.putExtra("Text2", doctor_details[i][0]);
//                it.putExtra("Text3", doctor_details[i][1]);
//                it.putExtra("Text4", doctor_details[i][3]);
//                it.putExtra("Text5", doctor_details[i][4]);
//                startActivity(it);
//            }
//        });


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it= new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });


    }
}