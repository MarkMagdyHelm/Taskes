package mark.com.login;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mark on 5/21/2017.
 */
class RowHolder extends RecyclerView.ViewHolder {
    TextView label = null;
    ImageView icon = null;
    String template = null;

    RowHolder(View row) {
        super(row);
        label = (TextView) row.findViewById(R.id.label);
        icon = (ImageView) row.findViewById(R.id.icon);

    }

    void bindModel(String item) {
        label.setText(item);

        if (item.equals("Facebook Login")) {
            icon.setImageResource(R.drawable.facebookhome);
        } else { if (item.equals("Load Contacts")) {
            icon.setImageResource(R.drawable.contacts);}

        else {
            if (item.equals("Search Counters")) {
                icon.setImageResource(R.drawable.countries);
            } else {
                if (item.equals("Map")) {
                    icon.setImageResource(R.drawable.map);
                }
            }
        }}}}