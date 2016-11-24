package repositories.Training.schedules
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.schedules.CourseInstructors

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class CourseInstructorsRepository extends CassandraTable[CourseInstructorsRepository, CourseInstructors] {


  object TrainingInstructorId extends StringColumn(this) with PartitionKey[String]

  object scheduledCourseId extends StringColumn(this) with PrimaryKey[String]





  override def fromRow(r: Row): CourseInstructors = {
    CourseInstructors(
      scheduledCourseId(r),
      TrainingInstructorId(r)

    )
  }
}

object CourseInstructorsRepository extends CourseInstructorsRepository with RootConnector {
  override lazy val tableName = "courseinstructors"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(course: CourseInstructors): Future[ResultSet] = {
    insert
      .value(_.scheduledCourseId, course.scheduledCourseId)
      .value(_.TrainingInstructorId, course.TrainingInstructorId)


      .future()
  }

  def getAllCourseInstructors: Future[Seq[CourseInstructors]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseInstructorsById(id: String): Future[Option[CourseInstructors]] = {
    select.where(_.scheduledCourseId eqs id).one()
  }


}
