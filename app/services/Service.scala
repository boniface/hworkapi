package services

import conf.connection.DataConnection

/**
 * Created by hashcode on 2015/11/07.
 */
trait Service {
  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session
}
