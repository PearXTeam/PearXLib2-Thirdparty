package ru.pearx.lib.thirdparty.mediawiki;

/*
 * Created by mrAppleXZ on 12.08.17 14:11.
 */
public class MediawikiException extends Exception
{
    private MediawikiParseResult.Error e;

    public MediawikiException(MediawikiParseResult.Error e)
    {
        super(e.info);
        this.e = e;
    }

    public MediawikiParseResult.Error getError()
    {
        return e;
    }

    public void setError(MediawikiParseResult.Error e)
    {
        this.e = e;
    }
}
