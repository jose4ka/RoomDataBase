package com.example.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


//Адаптер для списка номеров

public class Adapter_Numbers extends RecyclerView.Adapter<Adapter_Numbers.ViewHolder> {

    //Локальный список для адаптера
    private List<Number> mData;
    private LayoutInflater mInflater;



    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void deleteNumber(Number number);
        void updateNumber(Number number);
        void callNumber(Number number);
    }

    //Объект интерфейса
    CallBackButtons callback;

    // Данные для конструктора
    public Adapter_Numbers(Context context, List<Number> data, CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_number, parent, false);
        return new ViewHolder(view);
    }


    //Тут мы устанавливаем значения для каждого элемента в списке при создании
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Number number = mData.get(position);
        if(number.getImage()==null){
            //Устанавливаем просто строковые значения, если не было установлено изображение
            holder.tv_surname.setText(number.getSurname());
            holder.tv_name.setText(number.getName());
            holder.tv_number.setText(number.getNumber());
        }
        else {
            //Устанавливаем строковые значения
            holder.tv_surname.setText(number.getSurname());
            holder.tv_name.setText(number.getName());
            holder.tv_number.setText(number.getNumber());
            //Получаем массив изображения, декодируем его, и устанавливаем на фото значка элемента
            byte[] image=number.getImage();
            Bitmap bmp= BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.img_ImageIcon.setImageBitmap(bmp);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Информация об элементах в разметке
    class ViewHolder extends RecyclerView.ViewHolder {
        Button btn_Delete, btn_Edit, btn_Call;
        TextView tv_name, tv_surname, tv_number;
        ImageView img_ImageIcon;
        ViewHolder(final View itemView) {
            super(itemView);

            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_Delete:

                            //Удаляем выбранный на экране элемент
                            /*
                            Обращаемся к локальному списку элементов, и из него
                            мы получаем нужный нам number с помощью индекса
                            Индекс у нас совпадает с расположением элеметов на экране
                            поэтому получаем его засчёт метода getLayoutPosition()
                             */
                            /*
                            После этого, мы с помощью интерфейса образаемся к основному классу
                            и удаляем нужный нам элемент
                             */
                            //Всё это выполняется и на следующих кликах
                            callback.deleteNumber(mData.get(getLayoutPosition()));
                            break;
                        case R.id.btn_Edit:
                                callback.updateNumber(mData.get(getLayoutPosition()));
                            break;
                        case R.id.btn_Call:
                            callback.callNumber(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };

            //Инициализируем переменную
            btn_Delete=itemView.findViewById(R.id.btn_Delete);
            btn_Edit=itemView.findViewById(R.id.btn_Edit);
            btn_Delete.setOnClickListener(oclBtn);
            btn_Edit.setOnClickListener(oclBtn);
            tv_name=itemView.findViewById(R.id.tv_Name);
            tv_number=itemView.findViewById(R.id.tv_Number);
            tv_surname=itemView.findViewById(R.id.tv_Surname);
            img_ImageIcon=itemView.findViewById(R.id.img_Icon);
            btn_Call=itemView.findViewById(R.id.btn_Call);
            btn_Call.setOnClickListener(oclBtn);


        }
    }
}
