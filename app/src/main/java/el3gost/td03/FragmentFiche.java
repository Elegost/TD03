package el3gost.td03;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentFiche extends Fragment
{
    private WebView webView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_fiche, container, false);
        webView = (WebView) view.findViewById(R.id.webView);

        Bundle args = getArguments();
        try {
            String data = args.getString("data");
            if (data != null)
                chargePlanete(data);
        }
        catch (Exception ex)
        {
        }
        return view;
    }

    public void chargePlanete(String data){
        webView.loadUrl("https://wikipedia.org/wiki/" + data + "_(planet)");
        Toast.makeText(getContext() , "https://wikipedia.org/wiki/" + data + "_(planet)", Toast.LENGTH_LONG).show();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
