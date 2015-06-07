package com.sce3.thirdyear.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sce3.thirdyear.androidmaps.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyListAdapter extends ArrayAdapter<Object> {

    public MyListAdapter(Context context, List<Object> list) {
        super(context, R.layout.historyitem, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View theView = inflater.inflate(R.layout.historyitem, parent, false);
        Map<String, Object> map = (Map) getItem(position);

        final int id = (int) map.get("id");
        String city = (String) map.get("city");
        String address = (String) map.get("address");
        String price = String.valueOf((int) map.get("price"));
        String filename = (String) map.get("filename");


        TextView cityText = (TextView) theView.findViewById(R.id.textView6);
        TextView addressText = (TextView) theView.findViewById(R.id.textView7);
        TextView priceText = (TextView) theView.findViewById(R.id.textView8);

        cityText.setText("City: " + city);
        addressText.setText("Address: " + address);
        priceText.setText("Price: " + price);


        ImageView theImageView = (ImageView) theView.findViewById(R.id.imageView2);
        new DownloadImageTask(theImageView).execute(String.format("http://%s/%s/%s", JSONRequest.SERVER, JSONRequest.IMAGE_DIR, filename));

        //ImageView delete = (ImageView) theView.findViewById(R.id.deletebtn);
        /*
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDB db = new SQLiteDB(v.getContext().getApplicationContext());
                String session = db.getSavedSession();
                String address = String.format("http://%s/JavaWeb/api?action=removeHistory&apartment_id=%d&session=%s", JSONRequest.SERVER, id, session);
                System.out.println(address);
                JSONRequest json = new JSONRequest(address);
                JSONObject jobj;
                try {
                    jobj = new JSONObject(json.getJSON());
                    if (jobj.getString("result").equals("success")) {
                        //MyListAdapter.this.remove(MyListAdapter.this.getPosition());
                        MyListAdapter.this.notifyDataSetChanged();
                        MyListAdapter.this.notifyDataSetInvalidated();
                    } else {
                        Toast.makeText(v.getContext(), "Error deleting item.", Toast.LENGTH_SHORT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
        return theView;
    }
}
