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
  object scheduledCourseId extends StringColumn(this)
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

  def findById(organisationId: String):Future[Option[CourseRating]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[CourseRating]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
