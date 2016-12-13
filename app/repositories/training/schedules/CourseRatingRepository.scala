package repositories.training.schedules
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.schedules.CourseRating

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseRatingRepository extends CassandraTable[CourseRatingRepository,CourseRating]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object scheduledCourseId extends StringColumn(this)  with PrimaryKey[String]
  object rating extends IntColumn(this)
  object comment extends StringColumn(this)

  override def fromRow(r: Row): CourseRating = {
    CourseRating(organisationId(r),scheduledCourseId(r),rating(r), comment(r))
  }
}

object CourseRatingRepository extends CourseRatingRepository with RootConnector {
  override lazy val tableName = "courserating"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseRating: CourseRating): Future[ResultSet] = {
    insert
      .value(_.organisationId, courseRating.organisationId)
      .value(_.scheduledCourseId, courseRating.scheduledCourseId)
      .value(_.rating, courseRating.rating)
      .value(_.comment, courseRating.comment)
      .future()
  }

  def getCourseRatingById(organisationId: String, scheduledCourseId: String):Future[Option[CourseRating]] = {
    select.where(_.organisationId eqs organisationId). and(_.scheduledCourseId eqs scheduledCourseId).one()
  }
  def getAllCourseRating: Future[Seq[CourseRating]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getCourseRating(organisationId: String): Future[Seq[CourseRating]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String,scheduledCourseId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.scheduledCourseId eqs scheduledCourseId).future()
  }
}
