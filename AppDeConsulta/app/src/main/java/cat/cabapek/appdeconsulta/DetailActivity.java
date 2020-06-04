package cat.cabapek.appdeconsulta;

import androidx.appcompat.app.AppCompatActivity;
import cat.cabapek.InfoRuta;
import cat.cabapek.Punt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    LinearLayout llista_punts;
    String url_apache = HomeActivity.url_apache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Integer idInfoRuta = getIntent().getIntExtra("idInfoRuta",0);
        Log.d("app", "SOC A ACTIVITY DETAIL AMB ID = "+idInfoRuta);
        setContentView(R.layout.activity_detail);

        //agafo la infoRuta

        DataManager data = new DataManager();

        try {
            data.execute("2", idInfoRuta.toString()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        omplir_info_ruta(data.infoRuta);

        llista_punts = findViewById(R.id.llista_punts);
        omplir_llista_punts(data.punts);

    }

    private void omplir_info_ruta(InfoRuta ir){
        System.out.println("Has cridat a omplir_info_ruta");

        ((TextView)findViewById(R.id.tv_titol)).setText(ir.getTitol());
        try {
            ((WebView)findViewById(R.id.tv_descripcio)).loadData( new Markdown4jProcessor().process(ir.getDescMarkDown()),"text/html","UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView ivImatgePunt = (ImageView)findViewById(R.id.iv_photo_url);
        Picasso.get().load(url_apache + ir.getPhotoURL()).into(ivImatgePunt);

        ((TextView)findViewById(R.id.tv_photo_titol)).setText(ir.getPhotoTitol());

        ((TextView)findViewById(R.id.tv_desnivell)).setText(((Integer)ir.getDesnivell()).toString() + "m");
        ((TextView)findViewById(R.id.tv_alcada_max)).setText(((Integer)ir.getAlçadaMax()).toString() + "m");
        ((TextView)findViewById(R.id.tv_alcada_min)).setText(((Integer)ir.getAlcadaMin()).toString() + "m");
        ((TextView)findViewById(R.id.tv_distancia)).setText(((Float)ir.getDistanciaKm()).toString() + "Km");

        ((TextView)findViewById(R.id.tv_temps_aprox)).setText(convertirMinutsAText(ir.getTempsAprox()));
        ((TextView)findViewById(R.id.tv_circular)).setText(ir.isCircular()?"Si":"No");
        ((TextView)findViewById(R.id.tv_categoria)).setText(ir.getCategoria().getNom());
        ((TextView)findViewById(R.id.tv_dificultat)).setText(((Integer)ir.getDificultat()).toString());

    }



    private void omplir_llista_punts(ArrayList<Punt> punts) {

        System.out.println("Has cridat a omplir_llista_punts");

        for(Punt p : punts){

            LinearLayout itemPunt = (LinearLayout) getLayoutInflater().inflate(R.layout.plantilla_punt, null);

            ((TextView)itemPunt.findViewById(R.id.tv_numero_nom)).setText(p.getNumero() + ". " + p.getNom());

            ((TextView)itemPunt.findViewById(R.id.tv_descripcio)).setText(p.getDescripcio());


            ((TextView)itemPunt.findViewById(R.id.tv_hora)).setText(convertirMinutsAText(p.getHora()));


            ((TextView)itemPunt.findViewById(R.id.tv_latitud)).setText(((Double) p.getLatitud()).toString());
            ((TextView)itemPunt.findViewById(R.id.tv_longitud)).setText(((Double) p.getLongitud()).toString());
            ((TextView)itemPunt.findViewById(R.id.tv_elevacio)).setText(((Integer)p.getElevació()).toString() + "m");

            ImageView ivImatgePunt = (ImageView) itemPunt.findViewById(R.id.iv_photo_url);
            Picasso.get().load(url_apache + p.getPhotoURL()).into(ivImatgePunt);

            ((TextView)itemPunt.findViewById(R.id.tv_photo_titol)).setText(p.getPhotoTitol());

            llista_punts.addView(itemPunt);
        }
    }

    /*
    COnverteixo tempsAprox (Int) minuts a 2h 22m
     */
    private String convertirMinutsAText(int tempsAprox) {
        Integer h = tempsAprox / 60;
        Integer m = tempsAprox % 60;
        return h + "h " + m + "m";
    }
}
