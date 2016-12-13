package repositories.training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.CourseStatus

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseStatusRepository extends CassandraTable[CourseStatusRepository,CourseStatus]{
  object courseId extends StringColumn(this) with PartitionKey[String]
  object status extends StringColumn(this)
  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): CourseStatus = {
    CourseStatus(courseId(r),status(r),date(r))
  }
}

object CourseStatusRepository extends CourseStatusRepository with RootConnector {
  override lazy val tableName = "coursestatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseStatus: CourseStatus): Future[ResultSet] = {
    insert
      .value(_.courseId, courseStatus.courseId)
      .value(_.status, courseStatus.status)
      .value(_.date, courseStatus.date)
      .future()
  }

  def getCourseStatusById(courseId: String):Future[Option[CourseStatus]] = {
    select.where(_.courseId eqs courseId).one()
  }
  def getAllCourseCourseStatus: Future[Seq[CourseStatus]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getCourseStatus(courseId: String): Future[Seq[CourseStatus]] = {
    select.where(_.courseId eqs courseId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(courseId:String): Future[ResultSet] = {
    delete.where(_.courseId eqs courseId).future()
  }
}
