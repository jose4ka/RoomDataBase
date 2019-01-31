package com.example.roomdatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Adapter_Numbers extends RecyclerView.Adapter<Adapter_Numbers.ViewHolder> {
    private List<Number> mData;
    private LayoutInflater mInflater;



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


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button delete, edit;
        TextView tv_name, tv_surname, tv_number;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
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
