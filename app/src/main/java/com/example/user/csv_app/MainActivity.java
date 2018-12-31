package com.example.user.csv_app;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    List<EmployeeData> employeeDetails , employeeDetailsFilter;
    EmployeeData wellData ;
    ListView employee_list;
    ListAdapter arrayAdapter;

    public class ListAdapter extends ArrayAdapter<EmployeeData> {

        private int resourceLayout;
        private Context mContext;

        public ListAdapter(Context context, int resource, List<EmployeeData> items) {
            super(context, resource, items);
            this.resourceLayout = resource;
            this.mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(mContext);
                v = vi.inflate(resourceLayout, null);
            }

            EmployeeData p = getItem(position);

            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.textView);
                TextView tt2 = (TextView) v.findViewById(R.id.textView1);
                TextView tt3 = (TextView) v.findViewById(R.id.textView2);

                if (tt1 != null) {
                    tt1.setText("Employee ID "+p.getId());
                }

                if (tt2 != null) {
                    tt2.setText("Employee Name "+p.getName());
                }

                if (tt3 != null) {
                    tt3.setText("Skill Set "+p.getSkill()+", "+p.getSkill_1()+", "+p.getSkill_2());
                }
            }

            return v;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



       employeeDetails = new ArrayList<>();
       employee_list = (ListView)findViewById(R.id.employee_list);
        employeeDetailsFilter = new ArrayList<EmployeeData>();
       arrayAdapter = new ListAdapter(this,R.layout.textview, employeeDetailsFilter);
        employee_list.setAdapter(arrayAdapter);



        readData();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filterMenu:
                filterData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id==R.id.filterMenu)
//        {
//         filterData();
//        }
//        return super.onContextItemSelected(item);
//    }

    private void readData() {

        InputStream is = getResources().openRawResource(R.raw.employees);// read the file using input stream
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))); // convert the file data into char
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split(",");

                // Read the data and store it in the WellData POJO.
                 wellData = new EmployeeData();
                wellData.setId(tokens[0]);
                wellData.setName(tokens[1]);
                wellData.setSkill(tokens[2]);
                wellData.setSkill_1(tokens[3]);
                wellData.setSkill_2(tokens[4]);
//                if(!tokens[5].isEmpty())
//                {
//                wellData.setSkill_3(tokens[5]);
//               }
//               else
//                {
//                  //  wellData.setSkill_3(" ");
//                }
// wellData.setWellName(tokens[5]);
               // String temp =null;

               employeeDetails.add(wellData);
                 //check();
                Log.d("MainActivity" ,"Just Created " + wellData.toString());
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }

    }

    public void filterData()
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        // TextView textView = (TextView) findViewById(R.id.view) ;
        employeeDetailsFilter.clear();
        String a = editText.getText().toString();;// user defined keyword to filter

        // employee_list.setAdapter(arrayAdapter);
        for (EmployeeData temp : employeeDetails) {

            if(temp.getId().contains(a)||temp.getName().contains(a)||temp.getSkill()
                    .contains(a)||temp.getSkill_1().contains(a)||temp.getSkill_2().contains(a))
            {


//                   wellData1 = new EmployeeData();
//                   wellData1.setId(temp.getId());
//                   wellData1.setName(temp.getName());
//                   wellData1.setSkill(temp.getSkill());
//                   wellData1.setSkill_1(temp.getSkill_1());
//                   wellData1.setSkill_2(temp.getSkill_2());
                employeeDetailsFilter.add(temp);
                //   arrayAdapter.add(temp);

            }
        }

        arrayAdapter.notifyDataSetChanged();
        //textView.setText(employeeDetailsFilter.toString());
        Log.i("MainActivity",employeeDetailsFilter.toString());
    }





}
