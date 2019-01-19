package fr.antithseradio.antithseradio;

import android.app.ProgressDialog;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.exoplayer2.Player;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

public class radioFragment extends Fragment {

    private Button btn;
    private boolean PlayPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;
    public FloatingActionButton play;
    TextView Artiste;
    TextView Titre;
    Gson JSONArray;
    FloatingActionButton b;
    View v;


    JSONObject json;
    TextView tvName,tvNumber,tvEmail,tvUsername,tvPass,tvAddress;
    String adminName, adminNumber,adminEmail,adminUsername,adminPassword,adminAddress;
    private static final String PROFILE_URL =   "https://www.radioking.com/widgets/currenttrack.php?radio=11549&format=json";
    private static final String TAG_SUCCESS = "success";

    String url = "https://www.radioking.com/widgets/currenttrack.php?radio=11549&format=json";
    List<Object> list;
    Gson gson;
    ListView postList;
    Map<String,Object> mapPost;
    Map<String,Object> mapTitle;
    int postID;
    String postTitle[];


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_radio, container, false);
        b=(FloatingActionButton)v.findViewById(R.id.floatingActionButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //View rootView = inflater.inflate(R.layout.fragment_radio, container, false);
        play = (FloatingActionButton) inflater.inflate(R.layout.fragment_radio, container, false).findViewById(R.id.floatingActionButton);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        progressDialog = new ProgressDialog(getActivity());




        //BUTTON
        View v = inflater.inflate(R.layout.fragment_radio, container, false);
        play = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        Artiste = (TextView) v.findViewById(R.id.artistetext);
        Titre = (TextView) v.findViewById(R.id.titretext);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Write here anything that you wish to do on click of FAB
                Snackbar.make(view, "en dévelopement...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                /*try {
                    JSONArray myarr = json.getJSONArray("addminObject");

                    for(int i =0; i<myarr.length();i++){
                        JSONObject s = myarr.getJSONObject(i);

                        String adminName = s.getString("CompleteName");
                        String adminNumber = s.getJSONObject("1").getString("Number");

                        Artiste.setText(adminName);
                        Titre.setText(adminNumber);
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/

                //set nom
                // Artiste.setText(titre);
                // Titre.setText(artiste);






                if (!PlayPause){
                    //change logo

                    if (initialStage){
                        //initalise
                        new Player().execute("https://listen.radioking.com/radio/11549/stream/61915");

                    } else {
                        if (!mediaPlayer.isPlaying())
                            //lance
                            mediaPlayer.start();
                    }
                    // met sur play
                    PlayPause = true;
                   // play.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                }
                else {
                    //change logo

                    if (!mediaPlayer.isPlaying()){
                        // stop
                        mediaPlayer.stop();}
                    //met sur stop

                    PlayPause = false;
                   // play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
                }
            }
        });

        //affiche

        return v;
        //return inflater.inflate(R.layout.fragment_radio, container, false);
    }

    class Player extends AsyncTask<String, Void, Boolean>{
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;
            try {
                mediaPlayer.setDataSource("https://listen.radioking.com/radio/11549/stream/61915");
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initialStage = true;
                        PlayPause = true;
                        //changeiconlogo
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.prepare();
                prepared = true;
            }
            catch (Exception e){
                progressDialog.setMessage("error...");
                progressDialog.show();
                prepared = false;
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (progressDialog.isShowing()){
                progressDialog.cancel();
            }

            mediaPlayer.start();
            initialStage = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("chargement...");
            progressDialog.show();
        }
    }
}
