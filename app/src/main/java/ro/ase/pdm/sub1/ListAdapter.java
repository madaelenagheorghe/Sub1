package ro.ase.pdm.sub1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context context;
    List<HomeExchange> list;
    int resource;

    public ListAdapter(Context context,  int resource,List<HomeExchange> list) {
        this.context = context;
        this.list = list;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            holder = new ViewHolder();
            holder.liAdresa = convertView.findViewById(R.id.liAdresa);
            holder.liPerioada = convertView.findViewById(R.id.liPerioada);
            holder.liTip = convertView.findViewById(R.id.liTip);
            convertView.setTag(holder);
        }

        HomeExchange homeExchange = list.get(position);
        holder = (ViewHolder)convertView.getTag();
        holder.liAdresa.setText(homeExchange.getAdresa());
        holder.liPerioada.setText(homeExchange.getPerioada());
        holder.liTip.setText(homeExchange.getTipLocuinta().toString());

        return convertView;

    }

    static class ViewHolder{
        TextView liAdresa;
        TextView liPerioada;
        TextView liTip;
    }
}
