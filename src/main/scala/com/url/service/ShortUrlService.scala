package com.url.service

import com.url.dto.{Url, CompleteUrl}

import scalaj.http.Base64

/**
 * Service to short an url address
 *
 * @author felipey.
 */
class ShortUrlService {

    def validateUrl(longUrl: String): Unit = {
        if (longUrl == null) {
            throw new IllegalArgumentException
        }
        //TODO validate input url format

    }

    def getUrlJson(longUrl: String): CompleteUrl = {
        validateUrl(longUrl)

        val encodedId = Base64.encodeString(longUrl)
        val shortUrl = generateShortId(longUrl, encodedId)

        CompleteUrl(longUrl, shortUrl, encodedId, null)
    }

    def generateShortId(longUrl: String, encodedId : String): String = {
        //TODO clean this by using constants or think on a better solution
        val urls = longUrl.split('.')
        val firstHalf = urls(1).substring(0, 0)
        val secondHalf = urls(2).substring(0, 1)
        urls(0) + "." + firstHalf + "." + secondHalf + "/" + Base64.encodeString(longUrl)
    }

}
