package mark.com.login;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import mark.com.login.Contact.View.AllContacts;
import mark.com.login.Country.View.Countries_search;
import mark.com.login.FacebookLogin.View.FaceLogin;
import mark.com.login.Map.MapDemoActivity;


public class RecyclerActivity extends Activity {

        @Override
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.recycler_activit);
            setLayoutManager(new LinearLayoutManager(this));
            setAdapter(new IconicAdapter());
        }
        private RecyclerView rv=null;
        public void setAdapter(RecyclerView.Adapter adapter) {
            getRecyclerView().setAdapter(adapter);
        }
        public RecyclerView.Adapter getAdapter() {
            return(getRecyclerView().getAdapter());
        }
        public void setLayoutManager(RecyclerView.LayoutManager mgr) {
            getRecyclerView().setLayoutManager(mgr);
        }
        public RecyclerView getRecyclerView() {
            if (rv==null) {
                rv=new RecyclerView(this);
                rv.setHasFixedSize(true);
                setContentView(rv);
            }
            return(rv);
        }
    class IconicAdapter extends RecyclerView.Adapter<RowHolder> {

        private  final String[] items={"Facebook Login","Load Contacts","Search Counters","Map"};
        @Override
        public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return(new RowHolder( getLayoutInflater().inflate(R.layout.cell, parent, false)));
        }

        @Override
        public void onBindViewHolder(final RowHolder holder, int position) {
            holder.bindModel(items[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   if(items[rv.getChildLayoutPosition(v)].equals("Facebook Login") ) {
                       Intent in = new Intent(RecyclerActivity.this, FaceLogin.class);
                       startActivity(in);
                   }else {
                       if (items[rv.getChildLayoutPosition(v)].equals("Map")) {
                           Intent in = new Intent(RecyclerActivity.this, MapDemoActivity.class);
                           startActivity(in);
                       } else {
                           if (items[rv.getChildLayoutPosition(v)].equals("Load Contacts")) {
                               Intent in = new Intent(RecyclerActivity.this, AllContacts.class);
                               startActivity(in);
                           }else{ if (items[rv.getChildLayoutPosition(v)].equals("Search Counters")) {
                               Intent in = new Intent(RecyclerActivity.this, Countries_search.class);
                               startActivity(in);
                           }}
                       }
                   }

                }
            });
        }

        @Override
        public int getItemCount() {
            return(items.length);
        }


    }}