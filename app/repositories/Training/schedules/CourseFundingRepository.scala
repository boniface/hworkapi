package repositories.Training.schedules

import conf.connection.DataConnection
import domain.training.schedules.CourseFunding
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class CourseFundingRepository extends CassandraTable[CourseFundingRepository, CourseFunding] {


  object scheduledCourseId extends StringColumn(this) with PartitionKey[String]

  object fundingSourcesId extends StringColumn(this) with PrimaryKey[String]

  object amount extends BigDecimalColumn(this)

  object currencyId extends StringColumn(this)  with PartitionKey[String]



  override def fromRow(r: Row): CourseFunding = {
    CourseFunding(
      scheduledCourseId(r),
      fundingSourcesId(r),
      amount(r),
      currencyId(r),

    )
  }
}

object CourseFundingRepository extends CourseFundingRepository with RootConnector {
  override lazy val tableName = "coursefunding"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(course: CourseFunding): Future[ResultSet] = {
    insert
      .value(_.scheduledCourseId, course.scheduledCourseId)
      .value(_.fundingSourcesId, course.fundingSourcesId)
      .value(_.amount, course.amount)
      .value(_.currencyId,course.currencyId)

      .future()
  }

  def getAllCourseFunding: Future[Seq[CourseFunding]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseFundingById(id: String): Future[Option[CourseFunding]] = {
    select.where(_.fundingSourcesId eqs id).one()
  }


}
