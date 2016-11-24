package repositories.training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.CourseSubjects

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseSubjectsRepository extends CassandraTable[CourseSubjectsRepository,CourseSubjects]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object courseId extends StringColumn(this) with PrimaryKey[String]
  object subjectId extends StringColumn(this) with PrimaryKey[String]

  override def fromRow(r: Row): CourseSubjects = {
    CourseSubjects(organisationId(r),courseId(r),subjectId(r))
  }
}

object CourseSubjectsRepository extends CourseSubjectsRepository with RootConnector {
  override lazy val tableName = "coursesubjects"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseSubjects: CourseSubjects): Future[ResultSet] = {
    insert
      .value(_.organisationId, courseSubjects.organisationId)
      .value(_.courseId, courseSubjects.courseId)
      .value(_.subjectId, courseSubjects.subjectId)
      .future()
  }

  def getCourseSubjectsById(organisationId: String, courseId: String, subjectId: String):Future[Option[CourseSubjects]] = {
    select.where(_.organisationId eqs organisationId). and (_.courseId eqs courseId). and (_.subjectId eqs subjectId).one()
  }
  def getAllCourseCourseSubjects: Future[Seq[CourseSubjects]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getCourseSubjects(organisationId: String): Future[Seq[CourseSubjects]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }
  def deleteById(organisationId:String, courseId: String, subjectId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and (_.courseId eqs courseId). and (_.subjectId eqs subjectId).future()
  }
}
