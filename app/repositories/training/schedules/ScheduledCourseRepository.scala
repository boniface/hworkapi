package repositories.training.schedules
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.schedules.{CourseRating, ScheduledCourse}

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class ScheduledCourseRepository extends CassandraTable[ScheduledCourseRepository,ScheduledCourse]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object courseId extends StringColumn(this)  with PrimaryKey[String]
  object scheduledCourseId extends StringColumn(this)  with PrimaryKey[String]
  object venue extends StringColumn(this)
  object courseCapacity extends IntColumn(this)
  object creditHours extends IntColumn(this)
  object startDate extends DateTimeColumn(this)
  object endDate extends DateTimeColumn(this)
  object locationId extends StringColumn(this)
  object dateScheduled extends DateTimeColumn(this)

  override def fromRow(r: Row): ScheduledCourse = {
    ScheduledCourse(organisationId(r),courseId(r),scheduledCourseId(r), venue(r), courseCapacity(r),
      creditHours(r), startDate(r), endDate(r), locationId(r), dateScheduled(r))
  }
}

object ScheduledCourseRepository extends ScheduledCourseRepository with RootConnector {
  override lazy val tableName = "courserating"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(scheduledCourse: ScheduledCourse): Future[ResultSet] = {
    insert
      .value(_.organisationId, scheduledCourse.organisationId)
      .value(_.courseId, scheduledCourse.courseId)
      .value(_.scheduledCourseId, scheduledCourse.scheduledCourseId)
      .value(_.venue, scheduledCourse.venue)
      .value(_.courseCapacity, scheduledCourse.courseCapacity)
      .value(_.creditHours, scheduledCourse.creditHours)
      .value(_.endDate, scheduledCourse.endDate)
      .value(_.locationId, scheduledCourse.locationId)
      .value(_.dateScheduled, scheduledCourse.dateScheduled)
      .future()
  }

  def getScheduledCourseById(organisationId: String, courseId:String, scheduledCourseId: String):Future[Option[ScheduledCourse]] = {
    select.where(_.organisationId eqs organisationId). and(_.courseId eqs courseId). and(_.scheduledCourseId eqs scheduledCourseId).one()
  }
  def getAllScheduledCourse: Future[Seq[ScheduledCourse]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getScheduledCourse(organisationId: String): Future[Seq[ScheduledCourse]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, courseId:String,scheduledCourseId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.courseId eqs courseId).and(_.scheduledCourseId eqs scheduledCourseId).future()
  }
}
