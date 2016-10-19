package repositories.training.schedules
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.schedules.{ CourseInstructors}

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseInstructorsRepository extends CassandraTable[CourseInstructorsRepository,CourseInstructors]{
  object scheduledCourseId extends StringColumn(this) with PartitionKey[String]
  object TrainingInstructorId extends StringColumn(this)

  override def fromRow(r: Row): CourseInstructors = {
    CourseInstructors(scheduledCourseId(r),TrainingInstructorId(r))
  }
}

object CourseInstructorsRepository extends CourseInstructorsRepository with RootConnector {
  override lazy val tableName = "courseinstructors"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseInstructors: CourseInstructors): Future[ResultSet] = {
    insert
      .value(_.scheduledCourseId, courseInstructors.scheduledCourseId)
      .value(_.TrainingInstructorId, courseInstructors.TrainingInstructorId)
      .future()
  }

  def findById(scheduledCourseId: String):Future[Option[CourseInstructors]] = {
    select.where(_.scheduledCourseId eqs scheduledCourseId).one()
  }
  def findAll: Future[Seq[CourseInstructors]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(scheduledCourseId:String): Future[ResultSet] = {
    delete.where(_.scheduledCourseId eqs scheduledCourseId).future()
  }
}
