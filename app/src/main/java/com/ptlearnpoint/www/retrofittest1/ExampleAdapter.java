package com.ptlearnpoint.www.retrofittest1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PT on 2/3/2017.
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {

    private ArrayList<Example> worldpopulationArrayList;
    private Context context;


    public ExampleAdapter(Context context , ArrayList<Example> worldpopulationArrayList){
        this.worldpopulationArrayList = worldpopulationArrayList;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_country,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.customerName.setText(worldpopulationArrayList.get(position).getTitle().toString());
        holder.customerAddress.setText(worldpopulationArrayList.get(position).getReleaseYear().toString());
        holder.phoneNumber.setText(worldpopulationArrayList.get(position).getRating().toString());

        //For Url Response

        Picasso.with(context).load(worldpopulationArrayList.get(position).getImage()).into(holder.customerImage);

       /*/ For  Byte Image Response
        try {


            byte[] decodeString = Base64.decode(worldpopulationArrayList.get(position).getFlag().getBytes(),Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
            holder.customerImage.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }/*/
    }

    @Override
    public int getItemCount() {
        return worldpopulationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView customerImage ;
        TextView customerName , phoneNumber , customerAddress;
        View cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            customerImage = (ImageView) itemView.findViewById(R.id.customer_image);
            customerName = (TextView) itemView.findViewById(R.id.customer_name);
            phoneNumber = (TextView) itemView.findViewById(R.id.phone_number);
            customerAddress = (TextView) itemView.findViewById(R.id.txt_address);
        }
    }
}
