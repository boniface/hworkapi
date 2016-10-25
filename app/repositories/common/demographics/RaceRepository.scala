package repositories.common.demographics

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.Race

import scala.concurrent.Future

sealed class RaceRepository extends CassandraTable[RaceRepository,Race]{
  object raceId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): Race = {
    Race(raceId(r),name(r))
  }
}

object RaceRepository extends RaceRepository with RootConnector {
  override lazy val tableName = "race"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(race: Race): Future[ResultSet] = {
    insert
      .value(_.raceId, race.raceId)
      .value(_.name, race.name)
      .future()
  }

  def findById(raceId: String):Future[Option[Race]] = {
    select.where(_.raceId eqs raceId).one()
  }
  def findAll: Future[Seq[Race]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(raceId:String): Future[ResultSet] = {
    delete.where(_.raceId eqs raceId).future()
  }
}
