package com.url.commons.utils.cassandra

import com.url.dto.ConcreteUrls
import com.websudos.phantom.connectors.{KeySpaceDef, ContactPoint, ContactPoints}

/**
 * Object that connects to a Cassandra cluster
 *
 * @author felipey.
 */
object Connector {

    val hosts = Seq("127.0.0.1")


    val Connector = ContactPoints(hosts).keySpace("url")


}

object Defaults {
    val connector = ContactPoint.local.keySpace("url")
}

class UrlShortnerDatabase(val keyspace: KeySpaceDef) extends com.websudos.phantom.db.DatabaseImpl(keyspace) {
    object users extends ConcreteUrls with keyspace.Connector
}

object UrlShortnerDatabase extends UrlShortnerDatabase(Defaults.connector)
