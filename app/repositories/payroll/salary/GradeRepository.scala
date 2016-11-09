package repositories.payroll.salary

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.salary.Grade

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
class GradeRepository extends CassandraTable[GradeRepository, Grade] {


  object organisationId extends StringColumn(this) with PartitionKey[String]

  object gradeId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object name extends StringColumn(this)

  object numberOfNotches extends IntColumn(this)

  object lowerAmount extends BigDecimalColumn(this)

  object topAmount extends BigDecimalColumn(this)

  object currencyId extends StringColumn(this)

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object notes extends StringColumn(this)



  override def fromRow(r: Row): Grade = {
    Grade(
      organisationId(r),
      gradeId(r),
      name(r),
      numberOfNotches(r),
      lowerAmount(r),
      topAmount(r),
      currencyId(r),
      date(r),
      notes(r)
    )
  }
}

object GradeRepository extends GradeRepository with RootConnector {
  override lazy val tableName = "grades"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(grade: Grade): Future[ResultSet] = {
    insert
      .value(_.organisationId, grade.organisationId)
      .value(_.gradeId, grade.gradeId)
      .value(_.name, grade.name)
      .value(_.numberOfNotches, grade.numberOfNotches)
      .value(_.lowerAmount, grade.lowerAmount)
      .value(_.topAmount, grade.topAmount)
      .value(_.currencyId, grade.currencyId)
      .value(_.date, grade.date)
      .value(_.notes, grade.notes)
      .future()
  }

  def getGradeById(organisationId: String, gradeId: String): Future[Option[Grade]] = {
    select.where(_.organisationId eqs organisationId).and(_.gradeId eqs gradeId).one()
  }

  def getCompanyGrades(organisationId: String): Future[Seq[Grade]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

}
