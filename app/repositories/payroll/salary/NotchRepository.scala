package repositories.payroll.salary

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.salary.Notch

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/01/23.
  */
class NotchRepository extends CassandraTable[NotchRepository, Notch] {

  object gradeId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)

  object amount extends BigDecimalColumn(this)

  override def fromRow(r: Row): Notch = {
    Notch(
      gradeId(r),
      id(r),
      name(r),
      amount(r)
    )
  }
}

object NotchRepository extends NotchRepository with RootConnector {
  override lazy val tableName = "notches"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(notch: Notch): Future[ResultSet] = {
    insert
      .value(_.gradeId, notch.gradeId)
      .value(_.id, notch.id)
      .value(_.name, name.name)
      .value(_.amount, notch.amount)
      .future()
  }

  def getNotchById(gradeId: String, id: String): Future[Option[Notch]] = {
    select.where(_.gradeId eqs gradeId).and(_.id eqs id).one()
  }

  def getGradeNotches(gradeId: String): Future[Seq[Notch]] = {
    select.where(_.gradeId eqs gradeId).fetchEnumerator() run Iteratee.collect()
  }

}
