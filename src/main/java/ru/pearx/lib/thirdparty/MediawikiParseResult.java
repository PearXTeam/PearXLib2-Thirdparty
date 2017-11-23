package ru.pearx.lib.thirdparty;

import com.google.gson.annotations.SerializedName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by mrAppleXZ on 11.08.17 21:36.
 */
public class MediawikiParseResult
{
    public ParseResult parse;
    public Error error;

    public static class ParseResult
    {
        public String title;
        public int revid;
        public Map<String, String> text;
        public List<Category> categories;

        public String extractSummary(String page)
        {
            Document doc = Jsoup.parse(text.get(page));

            Element el = doc.getElementsByTag("body").get(0);
            List<Node> toRem = new ArrayList<>();
            boolean rem = false;
            for(Node nd : el.childNodes())
            {
                if (!rem && nd instanceof Element)
                {
                    Element e = (Element) nd;
                    if (e.tagName().equals("table") && e.id().equals("toc"))
                        rem = true;
                }
                if (rem)
                    toRem.add(nd);
            }
            for(Node nd : toRem)
                nd.remove();

            JsoupUtils.filterRecursive(el, (node) ->
            {
                if(node instanceof Element)
                {
                    Element elem = (Element) node;
                    return !elem.tagName().equals("table") && !elem.id().startsWith("cite_ref") && !elem.classNames().contains("thumb");
                }
                return true;
            });
            return el.text();
        }
    }

    public static class Category
    {
        public String sortkey;
        @SerializedName("*")
        public String all;
    }

    public static class Error
    {
        public String code;
        public String info;
    }
}
