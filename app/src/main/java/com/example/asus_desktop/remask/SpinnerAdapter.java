package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/13/2018.
 */

/*public class SpinnerAdapter extends AppCompatActivity {
    List<String> list  = new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view_spinner);


        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        getData();
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedPosition= position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayAdapter<String> getData() {

        list.add("Sparta");
        list.add("Coder");
        list.add("Healthy");
        list.add("Android");
        list.add("Developer");

        spinnerAdapter.addAll(list);
        spinnerAdapter.notifyDataSetChanged();
        return spinnerAdapter;
    }
}
*/