package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.CourseSubjects

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class CourseSubjectsRepository extends CassandraTable[CourseSubjectsRepository, CourseSubjects] {

  object subjectId extends StringColumn(this) with PrimaryKey[String]

  object courseId extends StringColumn(this) with PartitionKey[String]

  object organisationId extends StringColumn(this) with PartitionKey[String]


  override def fromRow(r: Row): CourseSubjects = {
    CourseSubjects(
      subjectId (r),
      courseId(r),
      organisationId(r)
    )
  }
}

object CourseSubjectsRepository extends CourseSubjectsRepository with RootConnector {
  override lazy val tableName = "coursesubjects"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course: CourseSubjects): Future[ResultSet] = {
    insert
      .value(_.subjectId, course.subjectId)
      .value(_.courseId, course.courseId)
      .value(_.organisationId, course.organisationId)
      .future()
  }

  def getAllCourseCourseSubjects: Future[Seq[CourseSubjects]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseSubjectsById(id: String): Future[Option[CourseSubjects]] = {
    select.where(_.subjectId eqs id).one()
  }

}
