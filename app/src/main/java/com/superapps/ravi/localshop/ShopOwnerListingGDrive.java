

package com.superapps.ravi.localshop;

import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ShopOwnerListingGDrive extends ListActivity
{
    ArrayList list = new ArrayList();
    ArrayAdapter adapter;
    TextView storeKeeper;
    EditText editName,edit;
    String name;
    Button btnDel,btnAdd;


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
        btnAdd = (Button) findViewById(R.id.btnAdd);

        /** Reference to the delete button of the layout main.xml */
        btnDel = (Button) findViewById(R.id.btnDel);

        /** Reference to the save button of the layout main.xml */
        Button btnSave = (Button) findViewById(R.id.btnDel);


        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, list);

        editName = (EditText) findViewById(R.id.nameTxt);
        storeKeeper = (TextView) findViewById(R.id.name);
        edit = (EditText) findViewById(R.id.txtItem);

        /** Setting the adapter to the ListView */
        setListAdapter(adapter);
        //list.setAdapter(adapter);

        /** Setting the event listener for the add button */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editName.getText().toString().equals(""))) {
                    storeKeeper.setText(editName.getText().toString());
                    list.add(edit.getText().toString());
                    edit.setText("");
                    adapter.notifyDataSetChanged();
                } else
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

                for (int i = itemCount - 1; i >= 0; i--) {
                    if (checkedItemPositions.get(i)) {
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onListItemClick (ListView l, View v,int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        if (getListView().getCheckedItemCount()!= 0) {
            btnDel.setVisibility(View.VISIBLE);
      } else {
           btnDel.setVisibility(View.INVISIBLE);
       }
    }

    public void CreateFileOnGoogleDrive(View view) {
        Intent intent = new Intent(this, CreateFileActivity.class);
        startActivity(intent);
    }

    /*public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        // Do something in response to button
    }*/

}
