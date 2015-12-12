package com.url.commons.utils.cassandra

import java.net.InetAddress

import com.datastax.driver.core.Cluster
import com.typesafe.config.ConfigFactory
import com.websudos.phantom.connectors.{KeySpace, SessionProvider}
import com.websudos.phantom.dsl.Session

import scala.collection.JavaConversions._


/**
 * Connector to a Cassandra cluster
 *
 * @author felipey.
 */
trait CassandraConnector extends SessionProvider {

    val config = ConfigFactory.load()

    implicit val space: KeySpace = Connector.keyspace

    val cluster = Connector.cluster

    implicit lazy val session: Session = Connector.session
}

object Connector {
    val config = ConfigFactory.load()

    val hosts = config.getStringList("cassandra.host")
    val inets = hosts.map(InetAddress.getByName)

    val keyspace: KeySpace = KeySpace(config.getString("cassandra.keyspace"))

    val cluster =
        Cluster.builder()
            .addContactPoints(inets)
            .withCredentials(config.getString("cassandra.username"), config.getString("cassandra.password"))
            .build()

    val session: Session = cluster.connect(keyspace.name)
}
