package repositories.training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.Course

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseRepository extends CassandraTable[CourseRepository,Course]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object courseId extends StringColumn(this)
  object name extends StringColumn(this)
  object courseCategoryId extends StringColumn(this)
  object courseCode extends StringColumn(this)
  object trainingInstitutionId extends StringColumn(this)
  object courseObjective extends StringColumn(this)
  object courseTypeId extends StringColumn(this)
  object criteriaId extends StringColumn(this)
  object description extends StringColumn(this)


  override def fromRow(r: Row): Course = {
    Course(organisationId(r), courseId(r),name(r), courseCategoryId(r),courseCode(r),trainingInstitutionId(r),courseObjective(r),
      courseTypeId(r), criteriaId(r), description(r))
  }
}

object CourseRepository extends CourseRepository with RootConnector {
  override lazy val tableName = "course"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course: Course): Future[ResultSet] = {
    insert
      .value(_.organisationId, course.organisationId)
      .value(_.courseId, course.courseId)
      .value(_.name, course.name)
      .value(_.courseCategoryId, course.courseCategoryId)
      .value(_.courseCode, course.courseCode)
      .value(_.trainingInstitutionId, course.trainingInstitutionId)
      .value(_.courseObjective, course.courseObjective)
      .value(_.courseTypeId, course.courseTypeId)
      .value(_.criteriaId, course.criteriaId)
      .value(_.description, course.description)
      .future()
  }

  def findById(organisationId: String):Future[Option[Course]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[Course]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
