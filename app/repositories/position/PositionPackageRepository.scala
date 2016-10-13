package repositories.position

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.position.PositionPackage

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
class PositionPackageRepository extends CassandraTable[PositionPackageRepository, PositionPackage] {


  object positionId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object gradeId extends StringColumn(this)

  object notchId extends StringColumn(this)

  object date extends DateColumn(this) with PrimaryKey[Date] with ClusteringOrder[Date] with Descending

  object state extends StringColumn(this)

  override def fromRow(r: Row): PositionPackage = {
    PositionPackage(
      positionId(r),
      id(r),
      gradeId(r),
      notchId(r),
      date(r),
      state(r)
    )
  }
}

object PositionPackageRepository extends PositionPackageRepository with RootConnector {
  override lazy val tableName = "ppackages"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(pospackage: PositionPackage): Future[ResultSet] = {
    insert
      .value(_.positionId, pospackage.positionId)
      .value(_.id, pospackage.id)
      .value(_.gradeId, pospackage.gradeId)
      .value(_.notchId, pospackage.notchId)
      .value(_.date, pospackage.date)
      .value(_.state, pospackage.state)
      .future()
  }

  def getPositionPackage(positionId: String, id: String): Future[Option[PositionPackage]] = {
    select.where(_.positionId eqs positionId).and(_.id eqs id).one()
  }

  def getPositionPackages(positionId: String): Future[Seq[PositionPackage]] = {
    select.where(_.positionId eqs positionId).fetchEnumerator() run Iteratee.collect()
  }

}
