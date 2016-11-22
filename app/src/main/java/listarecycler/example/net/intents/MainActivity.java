package listarecycler.example.net.intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button web,tlf,maps,fot,corr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (Button)findViewById(R.id.AbrirWeb);
        tlf = (Button)findViewById(R.id.LlamarTelefono);
        maps = (Button)findViewById(R.id.Maps);
        fot = (Button)findViewById(R.id.Foto);
        corr = (Button)findViewById(R.id.Correo);

        web.setOnClickListener(this);
        tlf.setOnClickListener(this);
        maps.setOnClickListener(this);
        fot.setOnClickListener(this);
        corr.setOnClickListener(this);
    }

    public void pgWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://campus.somtic.net/"));
        startActivity(intent);
    }
    public void llamadaTelefono(View view) {
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
    public void googleMaps(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:38.553468,-0.121579"));
        startActivity(intent);
    }
    public void hacerFoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
    public void mandarCorreo(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL,
                new String[] {"smira@iesperemaria.com" });
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AbrirWeb:
                pgWeb(v);
            break;

            case R.id.LlamarTelefono:
                llamadaTelefono(v);
            break;

            case R.id.Maps:
                googleMaps(v);
            break;

            case R.id.Foto:
                hacerFoto(v);
            break;

            case R.id.Correo:
                mandarCorreo(v);
            break;
        }
    }
}
