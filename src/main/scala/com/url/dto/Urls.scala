package com.url.dto

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl.{Row, ConsistencyLevel, ResultSet, StringColumn}

import scala.concurrent.Future

/**
 * @author felipey.
 */
class Urls extends CassandraTable[ConcreteUrls, UrlJson] {

    object id extends StringColumn(this)

    object shortUrl extends StringColumn(this)

    object longUrl extends StringColumn(this)

    def fromRow(row: Row): Url = {
        Url(
            id(row),
            shortUrl(row),
            longUrl(row)
        )
    }
}

abstract class ConcreteUrls extends Urls with RootConnector {

    def store(url: UrlJson): Future[ResultSet] = {
        insert.value(_.id, url.id)
            .value(_.shortUrl, url.shortUrl)
            .value(_.longUrl, url.longUrl)
            .consistencyLevel_=(ConsistencyLevel.QUORUM)
            .future()
    }

    def getById(id: String): Future[Option[UrlJson]] = {
        select.where(_.id eqs id).one()
    }

}
