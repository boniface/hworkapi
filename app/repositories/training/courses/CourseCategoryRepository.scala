package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.CourseCategory

/**
 * Created by gavin.ackerman on 2016-11-08.
 */
class CourseCategoryRepository extends CassandraTable[CourseCategoryRepository, CourseCategory] {

  object courseCategoryId extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)

  object organisationId extends StringColumn(this) with PartitionKey[String]


  override def fromRow(r: Row): CourseCategory = {
    CourseCategory(
      courseCategoryId(r),
      name(r),
      organisationId(r)
    )
  }
}

object CourseCategoryRepository extends CourseCategoryRepository with RootConnector {
  override lazy val tableName = "coursecategory"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course: CourseCategory): Future[ResultSet] = {
    insert
      .value(_.courseCategoryId, course.courseCategoryId)
      .value(_.name, course.name)
      .value(_.organisationId, course.organisationId)
      .future()
  }

  def getAllCourseCategory: Future[Seq[CourseCategory]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseCategoryById(id: String): Future[Option[CourseCategory]] = {
    select.where(_.courseCategoryId eqs id).one()
  }
}
