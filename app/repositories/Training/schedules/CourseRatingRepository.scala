package repositories.Training.schedules
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.schedules.CourseRating
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
class CourseRatingRepository extends CassandraTable[CourseRatingRepository, CourseRating] {


  object organisationId extends StringColumn(this) with PartitionKey[String]

  object scheduledCourseId extends StringColumn(this) with PrimaryKey[String]

  object rating extends IntColumn(this)

  object comment extends StringColumn(this) with PartitionKey[String]



  override def fromRow(r: Row): CourseRating = {
    CourseRating(
      organisationId(r),
      scheduledCourseId(r),
      rating(r),
      comment(r),

    )
  }
}

object CourseRatingRepository extends CourseRatingRepository with RootConnector {
  override lazy val tableName = "courserating"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(course: CourseRating): Future[ResultSet] = {
    insert
      .value(_.organisationId, course.organisationId)
      .value(_.scheduledCourseId, course.scheduledCourseId)
      .value(_.rating, course.rating)
      .value(_.comment, course.comment)

      .future()
  }

  def getAllCourseRating: Future[Seq[CourseRating]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseRatingById(id: String): Future[Option[CourseRating]] = {
    select.where(_.scheduledCourseId eqs id).one()
  }


}
