package repositories.Training.schedules

import conf.connection.DataConnection
import domain.training.schedules.CourseParticipants

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class CourseParticipantsRepository extends CassandraTable[CourseParticipantsRepository, CourseParticipants] {


  object userId extends StringColumn(this) with PartitionKey[String]

  object scheduledCourseId extends StringColumn(this) with PrimaryKey[String]





  override def fromRow(r: Row): CourseParticipants = {
    CourseParticipants(
      scheduledCourseId(r),
      userId(r),

    )
  }
}

object CourseParticipantsRepository extends CourseParticipantsRepository with RootConnector {
  override lazy val tableName = "courseparticipants"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(course: CourseParticipants): Future[ResultSet] = {
    insert
      .value(_.scheduledCourseId, course.scheduledCourseId)
      .value(_.userId, course.userId)


      .future()
  }

  def getAllCourseParticipants: Future[Seq[CourseParticipants]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseParticipantsById(id: String): Future[Option[CourseParticipants]] = {
    select.where(_.scheduledCourseId eqs id).one()
  }


}
