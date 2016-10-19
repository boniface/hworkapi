package repositories.training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.CourseTargetGroups

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseTargetGroupsRepository extends CassandraTable[CourseTargetGroupsRepository,CourseTargetGroups]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object courseId extends StringColumn(this)
  object targetGroupId extends StringColumn(this)

  override def fromRow(r: Row): CourseTargetGroups = {
    CourseTargetGroups(organisationId(r),courseId(r),targetGroupId(r))
  }
}

object CourseTargetGroupsRepository extends CourseTargetGroupsRepository with RootConnector {
  override lazy val tableName = "coursetargetgroups"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseTargetGroups: CourseTargetGroups): Future[ResultSet] = {
    insert
      .value(_.organisationId, courseTargetGroups.organisationId)
      .value(_.courseId, courseTargetGroups.courseId)
      .value(_.targetGroupId, courseTargetGroups.targetGroupId)
      .future()
  }

  def findById(organisationId: String):Future[Option[CourseTargetGroups]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[CourseTargetGroups]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
