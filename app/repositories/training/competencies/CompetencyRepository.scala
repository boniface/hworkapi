package repositories.training.competencies
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.competencies.Competency

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CompetencyRepository  extends CassandraTable[CompetencyRepository,Competency]{
  object compencyId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object competencyTypeId extends StringColumn(this)

  override def fromRow(r: Row): Competency = {
    Competency(compencyId(r),name(r),competencyTypeId(r))
  }
}

object CompetencyRepository extends CompetencyRepository with RootConnector {
  override lazy val tableName = "competency"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(competency: Competency): Future[ResultSet] = {
    insert
      .value(_.compencyId, competency.compencyId)
      .value(_.name, competency.name)
      .value(_.competencyTypeId, competency.competencyTypeId)
      .future()
  }

  def findById(compencyId: String):Future[Option[Competency]] = {
    select.where(_.compencyId eqs compencyId).one()
  }
  def findAll: Future[Seq[Competency]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getCompetency(compencyId:String): Future[Seq[Competency]] = {
    select.where(_.compencyId eqs compencyId)fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(compencyId:String): Future[ResultSet] = {
    delete.where(_.compencyId eqs compencyId).future()
  }
}
