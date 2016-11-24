package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.TargetGroup

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class TargetGroupRepository extends CassandraTable[TargetGroupRepository, TargetGroup] {

  object targetGroupId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): TargetGroup = {
    TargetGroup(
      targetGroupId(r),
      name(r),



    )
  }
}

object TargetGroupRepository extends TargetGroupRepository with RootConnector {
  override lazy val tableName = "targetgroup"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(sub: TargetGroup): Future[ResultSet] = {
    insert
      .value(_.targetGroupId, sub.targetGroupId)
      .value(_.name, sub.name)



      .future()
  }
  def getAllTargetGroup: Future[Seq[TargetGroup]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getTargetGroupById(id: String): Future[Option[TargetGroup]] = {
    select.where(_.targetGroupId eqs id).one()
  }


}
