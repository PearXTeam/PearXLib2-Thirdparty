package ru.pearx.lib.thirdparty;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 * Created by mrAppleXZ on 21.11.17 18:52.
 */
public final class JsoupUtils
{
    public static void filterRecursive(Element elem, Predicate<Node> filter)
    {
        List<Node> toRem = new ArrayList<>();

        for(Node nd : elem.childNodes())
             if(!filter.test(nd))
                 toRem.add(nd);

        for(Node nd : toRem)
            nd.remove();

        for(Element el : elem.children())
        {
            filterRecursive(el, filter);
        }
    }
}
