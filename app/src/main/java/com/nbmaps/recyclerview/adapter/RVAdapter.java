package com.nbmaps.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nbmaps.recyclerview.interfaces.ItemClickListener;
import com.nbmaps.recyclerview.mediator.Courier;
import com.nbmaps.recyclerview.pojo.Person;
import com.nbmaps.recyclerview.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    private Courier courier;
    List<Person> persons;
    Context context;

    public RVAdapter(Context context, List<Person> persons, Courier courier){
        this.persons = persons;
        this.context = context;
        this.courier = courier;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int position) {
        personViewHolder.personName.setText(persons.get(position).name);
        personViewHolder.personAge.setText(persons.get(position).age);
        personViewHolder.personPhoto.setImageResource(persons.get(position).photoId);

        personViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int item, boolean isLongClick) {
                int item_position = personViewHolder.getAdapterPosition();//позиция выбраного элемента в адаптере
                Person currElement = persons.get(item_position);//выбраный элемент в адаптере
                int positionOfElement = persons.indexOf(currElement);//позиция элемента в списке (на экране (ищем порядковый номер по элементу))

                if(isLongClick){
                    removedItem(positionOfElement);
                    Toast.makeText(context, "Нажат (долгое нажатие) элеметн под номером "+item_position, Toast.LENGTH_SHORT).show();
                } else {
                    courier.add(currElement);
//                    copyItem(item_position, currElement);
                    Toast.makeText(context, "Нажат (короткое нажатие) элеметн под номером "+item_position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void copyItem(int item_position, Person currElement) {
        persons.add(item_position, currElement);
        notifyItemInserted(item_position);
    }
    public void copyItem(Person currElement) {
        int lastInList = persons.size();// что бы добавить в конец списка элемент (ставим последним)
        persons.add(lastInList, currElement);
        notifyItemInserted(lastInList);
    }

    private void removedItem(int positionOfElement) {
        persons.remove(positionOfElement);
        notifyItemRemoved(positionOfElement);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }




    public static class PersonViewHolder extends RecyclerView.ViewHolder
    implements View.OnLongClickListener, View.OnClickListener{
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        ItemClickListener itemClickListener;


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }
    }
}