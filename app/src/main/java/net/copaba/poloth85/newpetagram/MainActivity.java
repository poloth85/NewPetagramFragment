package net.copaba.poloth85.newpetagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pet> pets;
    private RecyclerView listaPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.MiActionBar);
        setSupportActionBar(miActionBar);

        listaPets = (RecyclerView) findViewById(R.id.rvPetagram);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaPets.setLayoutManager(llm);
        inicializaListaPets();
        inicializaAdaptador();


    }
    public  PetAdaptador adaptador;
    public void inicializaAdaptador(){
        adaptador = new PetAdaptador(pets,this);
        RecyclerView rvPets = (RecyclerView) findViewById(R.id.rvPetagram);
        rvPets.setAdapter(adaptador);
    }
    public void inicializaListaPets(){
        pets = new ArrayList<Pet>();
        pets.add(new Pet("Lola", R.drawable.dog_6,6));
        pets.add(new Pet("Caris",R.drawable.dog_5,5));
        pets.add(new Pet("Yordi", R.drawable.dog_4,4));
        pets.add(new Pet("Mimi", R.drawable.dog_3,3));
        pets.add(new Pet("Mike", R.drawable.dog_2,2));
        pets.add(new Pet("Teo", R.drawable.dog_1,1));
    }
    public void irActivity2(View view) {
//Mandamos al segundo activity solo las utimas 5 mascotas que se marcarn como favoritas
        ArrayList<Pet> pet = new ArrayList<Pet>();
        if(adaptador.petsFav != null && adaptador.petsFav.size()>5){
            for(int i = adaptador.petsFav.size()-5; i < adaptador.petsFav.size(); i++){
                pet.add(adaptador.petsFav.get(i));
            }
        }else {
            pet = adaptador.petsFav;
        }

        Bundle extra = new Bundle();
        extra.putSerializable("pets",(Serializable) pet);
        Intent intent =  new Intent(this,Activity2.class);
        intent.putExtra("extra",extra);
        startActivity(intent);
    }
}
