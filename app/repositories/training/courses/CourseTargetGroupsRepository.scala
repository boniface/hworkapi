package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.CourseTargetGroups

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class CourseTargetGroupsRepository  extends CassandraTable[CourseTargetGroupsRepository, CourseTargetGroups] {

  object targetGroupId extends StringColumn(this) with PrimaryKey[String]

  object courseId extends StringColumn(this) with PartitionKey[String]

  object organisationId extends StringColumn(this) with PartitionKey[String]


  override def fromRow(r: Row): CourseTargetGroups = {
    CourseTargetGroups(
      targetGroupId (r),
      courseId(r),
      organisationId(r)
    )
  }
}

object CourseTargetGroupsRepository extends CourseTargetGroupsRepository with RootConnector {
  override lazy val tableName = "coursetargets"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course: CourseTargetGroups): Future[ResultSet] = {
    insert
      .value(_.targetGroupId, course.targetGroupId)
      .value(_.courseId, course.courseId)
      .value(_.organisationId, course.organisationId)
      .future()
  }

  def getAllCourseCourseTargetGroups: Future[Seq[CourseTargetGroups]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseTargetGroupsById(id: String): Future[Option[CourseTargetGroups]] = {
    select.where(_.targetGroupId eqs id).one()
  }

}
