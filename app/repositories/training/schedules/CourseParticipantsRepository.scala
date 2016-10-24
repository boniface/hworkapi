package repositories.training.schedules
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.schedules.CourseParticipants

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseParticipantsRepository extends CassandraTable[CourseParticipantsRepository,CourseParticipants]{
  object scheduledCourseId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this)  with PrimaryKey[String]

  override def fromRow(r: Row): CourseParticipants = {
    CourseParticipants(scheduledCourseId(r),userId(r))
  }
}

object CourseParticipantsRepository extends CourseParticipantsRepository with RootConnector {
  override lazy val tableName = "courseparticipants"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseParticipants: CourseParticipants): Future[ResultSet] = {
    insert
      .value(_.scheduledCourseId, courseParticipants.scheduledCourseId)
      .value(_.userId, courseParticipants.userId)
      .future()
  }

  def findById(scheduledCourseId: String, userId: String):Future[Option[CourseParticipants]] = {
    select.where(_.scheduledCourseId eqs scheduledCourseId). and(_.userId eqs userId).one()
  }
  def findAll: Future[Seq[CourseParticipants]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getCourseParticipants(scheduledCourseId: String): Future[Seq[CourseParticipants]] = {
    select.where(_.scheduledCourseId eqs scheduledCourseId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(scheduledCourseId:String,userId: String): Future[ResultSet] = {
    delete.where(_.scheduledCourseId eqs scheduledCourseId). and(_.userId eqs userId).future()
  }
}
