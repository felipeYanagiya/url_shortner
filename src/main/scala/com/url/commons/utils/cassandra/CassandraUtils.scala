package com.url.commons.utils.cassandra

import com.datastax.driver.core.{ConsistencyLevel, ResultSet, Session, Statement}
import com.websudos.phantom.dsl.Row

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Helper class to handle consistency level operations from Cassandra statements
 *
 * @author felipey.
 */
object CassandraUtils {


    implicit class ExecuteStatement(statement: Statement) {

        /**
         * Execute a statement from a session with a specific consistency level
         *
         * @param consistencyLevel
         * @param session
         * @return
         */
        def runWith(consistencyLevel: ConsistencyLevel)(implicit session: Session): Future[ResultSet] = {
            statement.setConsistencyLevel(consistencyLevel)
            Future(session.execute(statement))
        }

        /**
         * Execute a statement from a session with a specific consistency level to get a single [[Row]]
         *
         * @param consistencyLevel
         * @param session
         * @return
         */
        def getOneWith(consistencyLevel: ConsistencyLevel)(implicit session: Session): Row = {
            statement.setConsistencyLevel(consistencyLevel)
            session.execute(statement).one()
        }

    }


}
