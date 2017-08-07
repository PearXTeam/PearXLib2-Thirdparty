package ru.pearx.lib.thirdparty;

import java.util.List;

/*
 * Created by mrAppleXZ on 07.08.17 12:39.
 */
public class GoogleImageSearchResult
{
    public Queries queries;
    public SearchInformation searchInformation;
    public List<Item> items = null;

    public static class Queries
    {
        public List<Page> request = null;
        public List<Page> nextPage = null;
    }

    public static class Page
    {
        public String title;
        public String totalResults;
        public String searchTerms;
        public Integer count;
        public Integer startIndex;
        public String inputEncoding;
        public String outputEncoding;
        public String safe;
        public String cx;
        public String searchType;
    }

    public static class SearchInformation
    {
        public Double searchTime;
        public String formattedSearchTime;
        public String totalResults;
        public String formattedTotalResults;
    }

    public static class Item
    {
        public String kind;
        public String title;
        public String htmlTitle;
        public String link;
        public String displayLink;
        public String snippet;
        public String htmlSnippet;
        public String mime;
        public Image image;
    }

    public static class Image
    {
        public String contextLink;
        public Integer height;
        public Integer width;
        public Integer byteSize;
        public String thumbnailLink;
        public Integer thumbnailHeight;
        public Integer thumbnailWidth;
    }
}