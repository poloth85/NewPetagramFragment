package net.copaba.poloth85.newpetagram;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Polo on 17/08/16.
 */
public class PetAdaptador extends RecyclerView.Adapter<PetAdaptador.PetViewHolder> {

    ArrayList<Pet> pets;
    Activity activity;
    // Array donde almacenamos los perros a los que le demos me  gusta
    ArrayList<Pet> petsFav = new ArrayList<Pet>();
    int inicio = 0;

    public PetAdaptador(ArrayList<Pet> pets, Activity activity){
        this.pets = pets;
        this.activity = activity;
    }


    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new PetViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final PetViewHolder petVewHolder, final int position) {

        final Pet pet = pets.get(position);
        petVewHolder.imgFoto.setImageResource(pet.getFoto());
        petVewHolder.tvNombre.setText(pet.getNombre());
        petVewHolder.tvRaite.setText(String.valueOf(pet.getRate()));



        petVewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, pet.getNombre(),Toast.LENGTH_SHORT).show();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!//////");
            }
        });

        petVewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si nos gusta un perro y no le hemos dado me husta a ninguno nos va a almacenar el perro que nos gusto
                if(petsFav.size() == 0) {
                    petsFav.add(pet);
                }
                else{// Si nos gusto un perro pero ya tenemos algun perro en el array, verificamos que no sea el mismo para no repetirlos
                    boolean flag = false;
                    for (Pet pet1 : petsFav){
                        if(pet.getNombre() == pet1.getNombre()){
                            flag = true;
                        }
                    }
                    if(!flag)
                        petsFav.add(pet);
                }
                Toast.makeText(activity, "Diste like a " + pet.getNombre(), Toast.LENGTH_SHORT).show();
                int rate = pet.getRate();
                rate = rate +1;
                pet.setRate(rate);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static  class PetViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvRaite;
        private ImageButton btnLike;
        private ImageButton btnFav;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvRaite = (TextView) itemView.findViewById(R.id.tvRaite);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
            btnFav = (ImageButton) itemView.findViewById(R.id.btnFav);

        }
    }
}