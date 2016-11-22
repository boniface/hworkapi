package repositories.position

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.position.PositionDesignation

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
sealed class PositionDesignationRepository extends CassandraTable[PositionDesignationRepository, PositionDesignation] {


  object positionId extends StringColumn(this) with PartitionKey[String]

  object positionDesignationId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object designationId extends StringColumn(this)

  object state extends StringColumn(this)


  override def fromRow(r: Row): PositionDesignation = {
    PositionDesignation(
      positionId(r),
      positionDesignationId(r),
      date(r),
      designationId(r),
      state(r)
    )
  }
}

object PositionDesignationRepository extends PositionDesignationRepository with RootConnector {
  override lazy val tableName = "designation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(designation: PositionDesignation): Future[ResultSet] = {
    insert
      .value(_.positionId, designation.positionId)
      .value(_.positionDesignationId, designation.positionDesignationId)
      .value(_.date, designation.date)
      .value(_.designationId, designation.designationId)
      .value(_.state, designation.state)
      .future()
  }

  def getDesignationById(positionId: String, positionDesignationId: String): Future[Option[PositionDesignation]] = {
    select.where(_.positionId eqs positionId).and(_.positionDesignationId eqs positionDesignationId).one()
  }

  def getPositionDesignations(positionId: String): Future[Seq[PositionDesignation]] = {
    select.where(_.positionId eqs positionId).fetchEnumerator() run Iteratee.collect()
  }

}
