package cat.cabapek.appdeconsulta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import cat.cabapek.Categoria;
import cat.cabapek.InfoRuta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    public static String url_apache = "http://192.168.1.217:80/projecte_final_dam/";
    Spinner spin;
    LinearLayout llista_categories;
    HashMap<Categoria, ArrayList<InfoRuta>> hmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataManager data = new DataManager();
        try {
            data.execute("1").get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<InfoRuta> arr = data.infoRutas;

        hmap = new HashMap<>();
        for(InfoRuta ir : arr){
            Categoria c = ir.getCategoria();
            if(hmap.containsKey(c)) {
                //la categoria ja esta al hashMap
                hmap.get(c).add(ir);
            }else{
                //primera vegada que vec aqueta categoria
                ArrayList<InfoRuta> arr_ir = new ArrayList<InfoRuta>();
                arr_ir.add(ir);
                hmap.put(c,arr_ir);
            }
        }

        final ArrayList<Categoria> categories = new ArrayList<Categoria>(hmap.keySet());
        final ArrayList<String> str_categories = new ArrayList<String>();
        for(Categoria c : categories){
            str_categories.add(c.getNom());
        }
        //giga cutrada, pero són les dues de la nit i tinc son
        final ArrayList<String> str_categories_spinner = new ArrayList<String>();
        str_categories_spinner.add("");
        str_categories_spinner.addAll(str_categories);

        //SPINNER STUFF
        spin = (Spinner) findViewById(R.id.spinner_categories);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {

                if(pos == 0){
                    //blank
                    for (int i = 0; i < llista_categories.getChildCount(); i++) {
                        View v = llista_categories.getChildAt(i);
                        v.setVisibility(View.VISIBLE);
                    }
                }else{
                    String nomCat = arg0.getItemAtPosition(pos).toString();
                    Categoria cat = categories.get(str_categories.indexOf(nomCat));

                    //faig un loop per llista categories, amagant els que no son la categoria seleccionada
                    for (int i = 0; i < llista_categories.getChildCount(); i++){
                        View v = llista_categories.getChildAt(i);
                        if(cat.getId() != v.getId()){
                            v.setVisibility(View.GONE);
                        }else v.setVisibility(View.VISIBLE);
                    }

                    //omplir_llista_categories(cat);
                    Log.d("app", "sóc al spinner onItemSelected amb pos="+pos + " i cat="+nomCat);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, str_categories_spinner);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);


        llista_categories = findViewById(R.id.llista_categories);

        omplir_llista_categories(null);

    }

    private void omplir_llista_categories(Categoria filtre) {

        System.out.println("Has cridat a omplir_llista_categories amb filtre="+filtre);

        View v = null;
        Iterator it = hmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            if(filtre != null){
                //filtro
                if(pair.getKey().equals(filtre)){
                    v = getViewCategoria(pair);
                    llista_categories.addView(v);
                }

            }else {
                v = getViewCategoria(pair);
                llista_categories.addView(v);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    private View getViewCategoria(Map.Entry<Categoria, ArrayList<InfoRuta>> parella) {

        LinearLayout itemCategoria = (LinearLayout) getLayoutInflater().inflate(R.layout.plantilla_item_llista_categories, null);

        TextView nomCategoria = (TextView)itemCategoria.findViewById(R.id.nomCategoria);
        nomCategoria.setText(parella.getKey().getNom());

        LinearLayout llRutesCategoria = (LinearLayout) itemCategoria.findViewById(R.id.llista_rutes_de_categoria);
        View cardRuta = null;
        for(InfoRuta row : parella.getValue()){
            cardRuta = getViewCardRuta(row);
            llRutesCategoria.addView(cardRuta);
        }

        itemCategoria.setId(parella.getKey().getId()); //TEST id=idCat

        return itemCategoria;
    }

    private View getViewCardRuta(final InfoRuta infoRuta) {

        final Context context = this;
        final Activity activity = this;

        LinearLayout cardRuta = (LinearLayout) getLayoutInflater().inflate(R.layout.plantilla_card_ruta, null);

        TextView tv = (TextView)cardRuta.findViewById(R.id.nomRuta);
        tv.setText(infoRuta.getTitol());

        ImageView ivImatgeRuta = (ImageView) cardRuta.findViewById(R.id.imatge_ruta);

        Picasso.get().load(url_apache + infoRuta.getPhotoURL()).into(ivImatgeRuta);
        ivImatgeRuta.setClickable(true);
        ivImatgeRuta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("idInfoRuta", infoRuta.getId());

                activity.startActivity(i);
            }
        });

        return cardRuta;
    }

    public void netejaFiltre(View view) {
        Log.d("app", "sóc a neteja filtre");
        for (int i = 0; i < llista_categories.getChildCount(); i++) {
            View v = llista_categories.getChildAt(i);
            v.setVisibility(View.VISIBLE);
        }
    }
}
