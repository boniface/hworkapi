package repositories.training.competencies
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.competencies.CompetencyType

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CompetencyTypeRepository extends CassandraTable[CompetencyTypeRepository,CompetencyType]{
  object competencyTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): CompetencyType = {
    CompetencyType(competencyTypeId(r),name(r))
  }
}

object CompetencyTypeRepository extends CompetencyTypeRepository with RootConnector {
  override lazy val tableName = "competencyType"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(competencyType: CompetencyType): Future[ResultSet] = {
    insert
      .value(_.competencyTypeId, competencyType.competencyTypeId)
      .value(_.name, competencyType.name)
      .future()
  }

  def findById(competencyTypeId: String):Future[Option[CompetencyType]] = {
    select.where(_.competencyTypeId eqs competencyTypeId).one()
  }
  def findAll: Future[Seq[CompetencyType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(competencyTypeId:String): Future[ResultSet] = {
    delete.where(_.competencyTypeId eqs competencyTypeId).future()
  }
}
