package ru.pearx.lib.thirdparty.bashim;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.pearx.lib.thirdparty.JsoupUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;

/*
 * Created by mrAppleXZ on 06.02.18 13:05.
 */
public class BashImUtils
{
    public static final String RANDOM_URL = "http://bash.im/random";

    public static String getRandomQuote(Random rand) throws IOException
    {
        Document doc = Jsoup.connect(RANDOM_URL).get();
        Elements elems = doc.getElementsByClass("text");
        return JsoupUtils.toText(new StringBuilder(), elems.get(rand.nextInt(elems.size()))).toString();
    }
}
