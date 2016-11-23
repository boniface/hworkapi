package repositories.users
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserAddress

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class UserAddressRepository extends CassandraTable[UserAddressRepository,UserAddress]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object userAddressId extends StringColumn(this) with PrimaryKey[String]
  object addressTypeId extends StringColumn(this)
  object details extends MapColumn[String, String] (this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)



  override def fromRow(r: Row): UserAddress = {
    UserAddress(organisationId(r), userId(r), userAddressId(r), addressTypeId(r), details(r), date(r), state(r))
  }
}

object UserAddressRepository extends UserAddressRepository with RootConnector {
  override lazy val tableName = "useraddress"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userAddress: UserAddress): Future[ResultSet] = {
    insert
      .value(_.organisationId, userAddress.organisationId)
      .value(_.userId, userAddress.userId)
      .value(_.userAddressId, userAddress.userAddressId)
      .value(_.addressTypeId, userAddress.addressTypeId)
      .value(_.details, userAddress.details)
      .value(_.date, userAddress.date)
      .value(_.state, userAddress.state)
      .future()
  }
  def findById(organisationId: String, userId: String, userAddressId: String):Future[Option[UserAddress]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.userAddressId eqs userAddressId).one()
  }
  def findAll: Future[Seq[UserAddress]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getUserAddress(organisationId: String): Future[Seq[UserAddress]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }
  def deleteById(organisationId:String, userId: String, userAddressId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.userAddressId eqs userAddressId).future()
  }
}
