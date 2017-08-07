package ru.pearx.lib.thirdparty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.pearx.lib.PXL;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * Created by mrAppleXZ on 07.08.17 12:11.
 */
public class GoogleApiUser
{
    public static final Gson GSON = new GsonBuilder().create();

    //I'm using multiple keys because sometimes one key stops working for the few minutes, but another key keeps working.
    private String[] apiKeys;

    public GoogleApiUser(String[] apiKeys)
    {
        this.apiKeys = apiKeys;
    }

    public String[] getApiKeys()
    {
        return apiKeys;
    }

    public void setApiKeys(String[] apiKeys)
    {
        this.apiKeys = apiKeys;
    }

    public GoogleImageSearchResult searchImages(String query, String context) throws IOException
    {
        for(int i = 0; i < apiKeys.length; i++)
        {
            String key = apiKeys[i];
            StringBuilder sb = new StringBuilder();
            sb.append("https://www.googleapis.com/customsearch/v1?key=");
            sb.append(PXL.encodeUrl(key));
            sb.append("&q=");
            sb.append(PXL.encodeUrl(query));
            sb.append("&searchType=image");
            sb.append("&alt=json");
            sb.append("&cx=");
            sb.append(PXL.encodeUrl(context));
            try
            {
                URLConnection conn = new URL(sb.toString()).openConnection();
                try(InputStream str = conn.getInputStream())
                {
                    try(InputStreamReader rdr = new InputStreamReader(str))
                    {
                        return GSON.fromJson(rdr, GoogleImageSearchResult.class);
                    }
                }
            }
            catch (IOException e)
            {
                if((i + 1) < apiKeys.length)
                {
                    continue;
                }
                throw e;
            }
        }
        return null;
    }
}
