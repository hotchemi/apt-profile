package apt.addiction.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import apt.addiction.R;
import apt.addiction.db.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private LayoutInflater inflater;

    private int textViewResourceId;

    public ItemAdapter(Context context, int textViewResourceId, List<Item> items) {
        super(context, textViewResourceId, items);
        this.textViewResourceId = textViewResourceId;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = inflater.inflate(textViewResourceId, null);
        }
        Item item = getItem(position);
        ((TextView) view.findViewById(R.id.title)).setText(item.name);
        return view;
    }

}