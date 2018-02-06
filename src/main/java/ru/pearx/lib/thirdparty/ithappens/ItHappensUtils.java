package ru.pearx.lib.thirdparty.ithappens;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.pearx.lib.thirdparty.JsoupUtils;

import java.io.IOException;
import java.util.Random;

/*
 * Created by mrAppleXZ on 06.02.18 13:35.
 */
public final class ItHappensUtils
{
    public static final String RANDOM_URL = "http://ithappens.me/random";

    public static String getRandom(Random rand) throws IOException
    {
        Document doc = Jsoup.connect(RANDOM_URL).get();
        Elements elems = doc.getElementsByClass("story");
        Element elem = elems.get(rand.nextInt(elems.size()));

        StringBuilder sb = new StringBuilder();
        sb.append(elem.getElementsByTag("h2").get(0).text()).append(System.lineSeparator());
        sb.append("===").append(System.lineSeparator());
        JsoupUtils.toText(sb, elem.getElementsByClass("text").get(0));
        return sb.toString();
    }
}
