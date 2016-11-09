package repositories.contacts

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/10/31.
 */
//company: String,
//entityId: String,
//id: String,
//postalAddress: Map[String, String],
//physicalAddress: Map[String, String],
//contactNumber: Map[String, String],
//postalCode: Map[String, String],
//emailAddress: Map[String, String],
//state: String,
//lastupdate: Date
sealed class ContactsRepository{/* extends CassandraTable[ContactsRepository, CompanyContacts] {

  object company extends StringColumn(this) with PartitionKey[String]

  object entityId extends StringColumn(this) with PrimaryKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object postalAddress extends MapColumn[String, String](this)

  object physicalAddress extends MapColumn[String, String](this)

  object contactNumber extends MapColumn[String, String](this)

  object emailAddress extends MapColumn[String, String](this)

  object state extends StringColumn(this)

  object lastupdate extends DateColumn(this)


  override def fromRow(r: Row): CompanyContacts = {
    CompanyContacts(
      company(r),
      entityId(r),
      id(r),
      postalAddress(r),
      physicalAddress(r),
      contactNumber(r),
      emailAddress(r),
      state(r),
      lastupdate(r)
    )

  }
}

object ContactsRepository extends ContactsRepository with RootConnector {
  override lazy val tableName = "ccontacts"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(contact: CompanyContacts): Future[ResultSet] = {
    insert
      .value(_.id, contact.id)
      .value(_.entityId, contact.entityId)
      .value(_.company, contact.company)
      .value(_.contactNumber, contact.contactNumber)
      .value(_.emailAddress, contact.emailAddress)
      .value(_.physicalAddress, contact.physicalAddress)
      .value(_.postalAddress, contact.postalAddress)
      .value(_.lastupdate, contact.lastupdate)
      .value(_.state, contact.state)
      .future()
  }

  def findById(company: String, entityId:String, id: String): Future[Option[CompanyContacts]] = {
    select.where(_.company eqs company).and(_.entityId eqs entityId).and(_.id eqs id).one()
  }

  def findAll(company: String): Future[Seq[CompanyContacts]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }

  def findEntityContacts(company: String, entityId:String): Future[Seq[CompanyContacts]] = {
    select.where(_.company eqs company).and(_.entityId eqs entityId).fetchEnumerator() run Iteratee.collect()
  }*/
}
