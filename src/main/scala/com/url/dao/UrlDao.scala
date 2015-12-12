package com.url.dao

import com.url.commons.utils.cassandra.CassandraConnector
import com.url.dto.{UrlModel, Url}
import com.websudos.phantom.dsl._
import com.url.commons.utils.cassandra.CassandraUtils._

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try


/**
 * Dao for url object
 *
 * @author felipey.
 */
trait UrlDao extends CassandraConnector {

    object dao {
        val urlModel = new UrlModel

        def createTable = {
            Await.ready(urlModel.model.create.ifNotExists().future, 5.seconds)
        }

        def getById(id: String): Future[Option[Url]] = {
            val r = urlModel
                .model
                .select
                .where(_ => urlModel.model.id eqs id)
                .statement()
                .getOneWith(ConsistencyLevel.QUORUM)

            Future(Try(urlModel.model.fromRow(r)).toOption)
        }

        def saveOrUpdate(url: Url): Future[ResultSet] = {
            urlModel.model.insert
                .value(_.id, url.id)
                .value(_.shortUrl, url.shortUrl)
                .value(_.longUrl, url.longUrl)
                .statement()
                .runWith(ConsistencyLevel.QUORUM)
        }

    }


}
