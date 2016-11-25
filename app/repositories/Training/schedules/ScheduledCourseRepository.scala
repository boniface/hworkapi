package repositories.Training.schedules

import conf.connection.DataConnection
import domain.training.schedules.ScheduledCourse
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
class ScheduledCourseRepository extends CassandraTable[ScheduledCourseRepository, ScheduledCourse] {


  object organisationId extends StringColumn(this) with PartitionKey[String]

  object courseId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object scheduledCourseId extends StringColumn(this) with PartitionKey[String]

  object venue extends StringColumn(this)

  object courseCapacity extends IntColumn(this)

  object creditHours extends IntColumn(this)

  object startDate extends DateTimeColumn(this)

  object  endDate extends DateTimeColumn(this)

  object locationId extends StringColumn(this) with PartitionKey[String]

  object dateScheduled extends DateTimeColumn(this)

  override def fromRow(r: Row): ScheduledCourse = {
    ScheduledCourse(
      organisationId(r),
      courseId(r),
      scheduledCourseId(r),
      venue(r),
      courseCapacity(r),
      creditHours(r),
      startDate(r),
      endDate(r),
      locationId(r),
      dateScheduled(r)
    )
  }
}

object ScheduledCourseRepository extends ScheduledCourseRepository with RootConnector {
  override lazy val tableName = "schedule"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(sched: ScheduledCourse): Future[ResultSet] = {
    insert
      .value(_.organisationId, sched.organisationId)
      .value(_.courseId, sched.courseId)
      .value(_.scheduledCourseId, sched.scheduledCourseId)
      .value(_.venue, sched.venue)
      .value(_.courseCapacity,sched.courseCapacity)
      .value(_.creditHours, sched.creditHours)
      .value(_.startDate, sched.startDate)
      .value(_.endDate, sched.endDate)
      .value(_.locationId, sched.locationId)
      .value(_.dateScheduled, sched.dateScheduled)
      .future()
  }

  def getAllScheduledCourse: Future[Seq[ScheduledCourse]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getScheduledCourseById(id: String): Future[Option[ScheduledCourse]] = {
    select.where(_.courseId eqs id).one()
  }


}
