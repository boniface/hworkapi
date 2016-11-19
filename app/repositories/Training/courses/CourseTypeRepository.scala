package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.CourseType

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class CourseTypeRepository extends CassandraTable[CourseTypeRepository, CourseType] {

  object courseTypeId extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)



  override def fromRow(r: Row): CourseType = {
    CourseType(
      courseTypeId(r),
      name(r),

    )
  }
}

object CourseTypeRepository extends CourseTypeRepository with RootConnector {
  override lazy val tableName = "coursetype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course: CourseType): Future[ResultSet] = {
    insert
      .value(_.courseTypeId, course.courseTypeId)
      .value(_.name, course.name)
      .future()
  }

  def getAllCourseTypeGroups: Future[Seq[CourseType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseTypesById(id: String): Future[Option[CourseType]] = {
    select.where(_.courseTypeId eqs id).one()
  }

}
