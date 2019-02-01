package com.example.roomdatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


//Адаптер для списка номеров

public class Adapter_Numbers extends RecyclerView.Adapter<Adapter_Numbers.ViewHolder> {
    private List<Number> mData;
    private LayoutInflater mInflater;

    //Объект класса тел. номера
    static Number number;

    // Данные для конструктора
    public Adapter_Numbers(Context context, List<Number> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_number, parent, false);
        return new ViewHolder(view);
    }


    //Тыт мы устанавливаем значения для каждого элемента в списке
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        number = mData.get(position);
        holder.tv_surname.setText(number.getSurname());
        holder.tv_name.setText(number.getName());
        holder.tv_number.setText(number.getNumber());

    }

    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Информация об элементах в разметке
    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button delete, edit;
        TextView tv_name, tv_surname, tv_number;
        ViewHolder(final View itemView) {
            super(itemView);

            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_Delete:
                            break;
                    }
                }
            };
            delete=itemView.findViewById(R.id.btn_Delete);
            edit=itemView.findViewById(R.id.btn_Edit);
            delete.setOnClickListener(oclBtn);
            edit.setOnClickListener(oclBtn);
            tv_name=itemView.findViewById(R.id.tv_Name);
            tv_number=itemView.findViewById(R.id.tv_Number);
            tv_surname=itemView.findViewById(R.id.tv_Surname);

        }
    }
}
