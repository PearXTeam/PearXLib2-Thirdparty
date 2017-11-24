package ru.pearx.lib.thirdparty;

import com.google.gson.annotations.SerializedName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import java.util.*;
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
            doc.outputSettings().prettyPrint(true);

            Element el = doc.getElementsByTag("body").get(0);

            {
                Element toc = el.getElementById("toc");
                if(toc != null)
                {
                    List<Node> toRem = new ArrayList<>();
                    boolean rem = false;
                    for (Node nd : toc.parent().childNodes())
                    {
                        if (nd instanceof Element && ((Element) nd).id().equals("toc"))
                            rem = true;
                        if (rem)
                            toRem.add(nd);
                    }
                    for (Node nd : toRem)
                        nd.remove();
                }
            }

            List<String> distags = Arrays.asList("thumb", "infobox");
            JsoupUtils.filterRecursive(el, (node) ->
            {
                if(node instanceof Element)
                {
                    Element elem = (Element) node;
                    return !elem.tagName().equals("table") && !elem.id().startsWith("cite_ref") && Collections.disjoint(elem.classNames(), distags);
                }
                return true;
            });
            return JsoupUtils.toText(new StringBuilder(), el).toString();
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
