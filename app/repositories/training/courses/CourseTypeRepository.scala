package repositories.training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.CourseType

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseTypeRepository extends CassandraTable[CourseTypeRepository,CourseType]{
  object courseTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): CourseType = {
    CourseType(courseTypeId(r),name(r))
  }
}

object CourseTypeRepository extends CourseTypeRepository with RootConnector {
  override lazy val tableName = "coursetype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseType: CourseType): Future[ResultSet] = {
    insert
      .value(_.courseTypeId, courseType.courseTypeId)
      .value(_.name, courseType.name)
      .future()
  }

  def findById(courseTypeId: String):Future[Option[CourseType]] = {
    select.where(_.courseTypeId eqs courseTypeId).one()
  }
  def findAll: Future[Seq[CourseType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(courseTypeId:String): Future[ResultSet] = {
    delete.where(_.courseTypeId eqs courseTypeId).future()
  }
}
