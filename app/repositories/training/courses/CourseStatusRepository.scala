package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.CourseStatus

/**
 * Created by gavin.ackerman on 2016-11-08.
 */
class CourseStatusRepository extends CassandraTable[CourseStatusRepository, CourseStatus] {

  object courseId extends StringColumn(this) with PrimaryKey[String]

  object status extends StringColumn(this)

  object date extends DateColumn(this)


  override def fromRow(r: Row): CourseStatus = {
    CourseStatus(
      courseId (r),
      status(r),
      date(r)
    )
  }
}

object CourseStatusRepository extends CourseStatusRepository with RootConnector {
  override lazy val tableName = "coursestatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course: CourseStatus): Future[ResultSet] = {
    insert
      .value(_.courseId, course.courseId)
      .value(_.status, course.status)
      .value(_.date, course.date)
      .future()
  }

  def getAllCourseCourseStatus: Future[Seq[CourseStatus]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseStatussById(id: String): Future[Option[CourseStatus]] = {
    select.where(_.courseId eqs id).one()
  }


}
