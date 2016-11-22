package listarecycler.example.net.intents;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button web,tlf,maps,fot,corr,busc,dial,streetView,comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (Button)findViewById(R.id.AbrirWeb);
        tlf = (Button)findViewById(R.id.LlamarTelefono);
        maps = (Button)findViewById(R.id.Maps);
        fot = (Button)findViewById(R.id.Foto);
        corr = (Button)findViewById(R.id.Correo);
        busc = (Button)findViewById(R.id.busca);
        dial = (Button)findViewById(R.id.dialer);
        streetView = (Button)findViewById(R.id.street);
        comp = (Button)findViewById(R.id.compartir);

        web.setOnClickListener(this);
        tlf.setOnClickListener(this);
        maps.setOnClickListener(this);
        fot.setOnClickListener(this);
        corr.setOnClickListener(this);
        busc.setOnClickListener(this);
        dial.setOnClickListener(this);
        streetView.setOnClickListener(this);
        comp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AbrirWeb:
                pgWeb();
                break;

            case R.id.LlamarTelefono:
                llamadaTelefono();
                break;

            case R.id.Maps:
                googleMaps();
                break;

            case R.id.Foto:
                hacerFoto();
                break;

            case R.id.Correo:
                mandarCorreo();
                break;

            case R.id.busca:
                busquedaWeb();
                break;

            case R.id.dialer:
                dialerTelefono();
                break;

            case R.id.street:
                streetView();
                break;

            case R.id.compartir:
                share();
                break;
        }
    }

    public void pgWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://campus.somtic.net/"));
        startActivity(intent);
    }
    public void llamadaTelefono() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(
                    Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {
                Intent intent =
                        new Intent(Intent.ACTION_CALL,Uri.parse(
                                "tel:966870700"));
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:966870700"));
            startActivity(intent);
        }
    }
    public void googleMaps() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:38.553468,-0.121579"));
        startActivity(intent);
    }
    public void hacerFoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
    public void mandarCorreo() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL,
                new String[] {"smira@iesperemaria.com" });
        startActivity(intent);
    }
    private void busquedaWeb(){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,"IES Pere Maria Orts");
        startActivity(intent);
    }

    private void dialerTelefono()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:966870700"));
        startActivity(intent);
    }

    private void streetView()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=38.553468,0.121579"));
        startActivity(intent);
    }

    private void share()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Compartido desde IES Pere Maria Orts.");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.share)));
    }

}
