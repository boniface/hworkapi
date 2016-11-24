package repositories.position

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.position.PositionFunding

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/18/2016.
  */
class PositionFundingRepository extends CassandraTable[PositionFundingRepository, PositionFunding] {

  //organisationId: String, positionId: String, fundingSourcesId: String, date: DateTime

  object organisationId extends StringColumn(this) with PartitionKey[String]

  object positionId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object fundingSourcesId extends StringColumn(this)

  override def fromRow(r: Row): PositionFunding = {
    PositionFunding(
      positionId(r),
      organisationId(r),
      fundingSourcesId(r),
      date(r)
    )
  }

}

  object PositionFundingRepository extends PositionFundingRepository with RootConnector {

    override lazy val tableName = "posfund";

    override implicit def space: KeySpace = DataConnection.keySpace

    override implicit def session: Session = DataConnection.session

    def save(event: PositionFunding): Future[ResultSet] =
      insert.value(_.positionId, event.positionId)
        .value(_.organisationId, event.organisationId)
        .value(_.fundingSourcesId, event.fundingSourcesId)
        .value(_.date, event.date)
        .future()


    def getPositionFunding(positionId: String, organisationId: String): Future[Option[PositionFunding]] = {
      select.where(_.positionId eqs positionId).and(_.organisationId eqs organisationId).one()

    }

    def getPositionEvents(positionId: String): Future[Seq[PositionFunding]] = {
      select.where(_.positionId eqs positionId).fetchEnumerator() run Iteratee.collect()
    }


  }