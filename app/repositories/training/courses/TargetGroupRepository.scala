package repositories.training.courses
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.TargetGroup

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class TargetGroupRepository extends CassandraTable[TargetGroupRepository,TargetGroup]{
  object targetGroupId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): TargetGroup = {
    TargetGroup(targetGroupId(r),name(r))
  }
}

object TargetGroupRepository extends TargetGroupRepository with RootConnector {
  override lazy val tableName = "targetgroup"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(targetGroup: TargetGroup): Future[ResultSet] = {
    insert
      .value(_.targetGroupId, targetGroup.targetGroupId)
      .value(_.name, targetGroup.name)
      .future()
  }

  def getTargetGroupById(targetGroupId: String):Future[Option[TargetGroup]] = {
    select.where(_.targetGroupId eqs targetGroupId).one()
  }
  def getAllTargetGroup: Future[Seq[TargetGroup]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTargetGroup(targetGroupId: String): Future[Seq[TargetGroup]] = {
    select.where(_.targetGroupId eqs targetGroupId).fetchEnumerator() run Iteratee.collect()
  }


  def deleteById(targetGroupId:String): Future[ResultSet] = {
    delete.where(_.targetGroupId eqs targetGroupId).future()
  }
}
