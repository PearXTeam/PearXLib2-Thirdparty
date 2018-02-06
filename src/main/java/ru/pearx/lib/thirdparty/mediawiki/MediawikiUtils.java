package ru.pearx.lib.thirdparty.mediawiki;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.pearx.lib.URIBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/*
 * Created by mrAppleXZ on 11.08.17 21:50.
 */
public class MediawikiUtils
{
    public static final Gson GSON = new GsonBuilder().create();

    public static MediawikiParseResult parsePage(String apiUrl, String pageName) throws IOException, MediawikiException, URISyntaxException
    {
        try(InputStream str = new URIBuilder(apiUrl)
                .putQuery("action", "parse")
                .putQuery("page", pageName)
                .putQuery("format", "json")
                .putQuery("redirects", "true").buildUrl().openStream())
        {
            try(InputStreamReader rdr = new InputStreamReader(str))
            {
                MediawikiParseResult res = GSON.fromJson(rdr, MediawikiParseResult.class);
                if(res.error != null)
                    throw  new MediawikiException(res.error);
                return res;
            }
        }
    }
}
