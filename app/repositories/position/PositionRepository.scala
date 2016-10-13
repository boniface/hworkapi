package repositories.position

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.position.Position

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/01/09.
  */
class PositionRepository extends CassandraTable[PositionRepository, Position] {

  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object code extends StringColumn(this)

  object title extends StringColumn(this)

  object jobId extends StringColumn(this)

  object positionType extends StringColumn(this)

  object description extends StringColumn(this)

  object supervisorId extends StringColumn(this)

  object state extends StringColumn(this)

  object date extends DateColumn(this)

  override def fromRow(r: Row): Position = {
    Position(
      company(r),
      id(r),
      code(r),
      title(r),
      jobId(r),
      positionType(r),
      description(r),
      supervisorId(r),
      state(r),
      date(r)
    )
  }
}

object PositionRepository extends PositionRepository with RootConnector {
  override lazy val tableName = "positions"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(position: Position): Future[ResultSet] = {
    insert
      .value(_.company, position.company)
      .value(_.id, position.id)
      .value(_.code, position.code)
      .value(_.title, position.title)
      .value(_.jobId, position.jobId)
      .value(_.positionType, position.positionType)
      .value(_.description, position.description)
      .value(_.state, position.state)
      .value(_.date, position.date)
      .future()
  }

  def getPositionById(company: String, id: String): Future[Option[Position]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

  def getCompanyPositions(company: String): Future[Seq[Position]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }

}
