package fr.antithseradio.antithseradio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.github.slashrootv200.exoplayerfragment.BaseExoPlayerActivity;
import com.github.slashrootv200.exoplayerfragment.ExoErrorEvent;
import com.github.slashrootv200.exoplayerfragment.ExoPlayerFragment;
import com.github.slashrootv200.exoplayerfragment.ExoVideoEndedEvent;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private PlayerView playerView;
    private SimpleExoPlayer player;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_radio:
                    setTitle(R.string.app_name);
                    mTextMessage.setText(R.string.title_radio);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new radioFragment()).commit();
                    return true;
                case R.id.navigation_news:
                    setTitle(R.string.title_news);
                    mTextMessage.setText(R.string.title_news);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new newsFragment()).commit();
                    return true;
                case R.id.navigation_podcast:
                    setTitle(R.string.title_podcast);
                    mTextMessage.setText(R.string.title_podcast);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new podcastFragment()).commit();
                    return true;
                case R.id.navigation_more:
                    setTitle(R.string.title_more);
                    mTextMessage.setText(R.string.title_more);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new moreFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new radioFragment()).commit();




       /* playerView=findViewById(R.id.player_view);

        player= ExoPlayerFactory.newSimpleInstance( this,
                new DefaultTrackSelector());
        playerView.setPlayer(player);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,"ExoPlayer"));

        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("https://r2---sn-25glen7e.googlevideo.com/videoplayback?signature=B225B7FD619D06C74359E66D41E945A0811645AB.AFE1C6D794F09B98A76EA1DF99D58FCCCA52E789&fvip=2&sparams=aitags%2Cclen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Ckeepalive%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cotfp%2Cpl%2Crequiressl%2Csource%2Cexpire&itag=136&ei=IABBXIj8O8ySmLAP1MihiAw&key=yt6&pl=42&mime=video%2Fmp4&source=youtube&c=WEB&otfp=1&keepalive=yes&expire=1547785345&aitags=133%2C134%2C135%2C136%2C160&lmt=1531194202984590&mn=sn-25glen7e%2Csn-25ge7nsl&requiressl=yes&ipbits=0&mm=31%2C29&id=o-AJIoEOoBXsH4k57ZnJyUnw_jSpop12JsckCdbmB3GfOz&gir=yes&initcwndbps=738750&ip=2a01%3Ae0a%3Af9%3A5f0%3Ac0fd%3A35ee%3A9a91%3Aaafd&clen=12757657&mv=m&dur=794.320&mt=1547763652&ms=au%2Crdu&alr=yes&cpn=EfsuJj6VlE4MPfXt&cver=2.20190115&rn=370&rbuf=38703")); //URL MEDIA

        player.prepare(mediaSource);
        player.setPlayWhenReady(false);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        if (id == R.id.menu_option){
            Intent myintent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(myintent);
        }

        return super.onOptionsItemSelected(item);
    }

}
