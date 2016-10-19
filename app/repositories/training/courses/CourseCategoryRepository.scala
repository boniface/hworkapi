package repositories.training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.CourseCategory

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseCategoryRepository  extends CassandraTable[CourseCategoryRepository,CourseCategory]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object courseCategoryId extends StringColumn(this)
  object name extends StringColumn(this)

  override def fromRow(r: Row): CourseCategory = {
    CourseCategory(organisationId(r),courseCategoryId(r),name(r))
  }
}

object CourseCategoryRepository extends CourseCategoryRepository with RootConnector {
  override lazy val tableName = "coursecategory"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseCategory: CourseCategory): Future[ResultSet] = {
    insert
      .value(_.organisationId, courseCategory.organisationId)
      .value(_.courseCategoryId, courseCategory.courseCategoryId)
      .value(_.name, courseCategory.name)
      .future()
  }

  def findById(organisationId: String):Future[Option[CourseCategory]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[CourseCategory]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
