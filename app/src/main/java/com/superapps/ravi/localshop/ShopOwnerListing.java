

package com.superapps.ravi.localshop;

        import android.app.ListActivity;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.SparseBooleanArray;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.ads.AdRequest;
        import com.google.android.gms.ads.AdView;
        import com.google.android.gms.ads.MobileAds;

        import java.util.ArrayList;

public class ShopOwnerListing extends ListActivity
{
    ArrayList list = new ArrayList();
    ArrayAdapter adapter;
    TextView storeKeeper;
    EditText editName,edit;
    String name;
    private class ViewHolder {
        CheckBox ck1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_owner_listing);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /** Reference to the add button of the layout main.xml */
        Button btn = (Button) findViewById(R.id.btnAdd);

        /** Reference to the delete button of the layout main.xml */
        Button btnDel = (Button) findViewById(R.id.btnDel);

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, list);

        editName = (EditText) findViewById(R.id.nameTxt);
        storeKeeper = (TextView)findViewById(R.id.name);
        name = editName.getText().toString();
        edit = (EditText) findViewById(R.id.txtItem);

        /** Setting the event listener for the add button */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editName.getText().toString().equals("")))
                {
                    storeKeeper.setText(name);
                    list.add(edit.getText().toString());
                    edit.setText("");
                    adapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(getApplicationContext(), "Enter Store name", Toast.LENGTH_LONG);
            }
        });
        /** Setting the event listener for the delete button */
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Getting the checked items from the listview */
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
            }
        });

        /** Setting the adapter to the ListView */
        setListAdapter(adapter);
    }
}
