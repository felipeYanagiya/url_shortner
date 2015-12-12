package com.url.dto

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Row, StringColumn}
import com.websudos.phantom.keys.PrimaryKey

/**
 * Define commons fields for url table
 *
 * @author felipey.
 */
trait GenericUrlModel {
    def model: InnerGeneric

    trait InnerGeneric extends CassandraTable[InnerGeneric, Url] {
        object id extends StringColumn(this) with PrimaryKey[String]

        object shortUrl extends StringColumn(this)

        object longUrl extends StringColumn(this)

        override def fromRow(row: Row): Url = {
            Url(
                id(row),
                shortUrl(row),
                longUrl(row)
            )
        }
    }
}

class UrlModel extends GenericUrlModel {
    object model extends InnerGeneric {
        override val tableName = "url"
    }
}
