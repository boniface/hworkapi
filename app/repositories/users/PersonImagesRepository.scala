package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonImages

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonImagesRepository extends CassandraTable[PersonImagesRepository,PersonImages]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personImageId extends StringColumn(this) with PrimaryKey[String]
  object url extends StringColumn(this)
  object description extends StringColumn(this)
  object mime extends StringColumn(this)
  object size extends OptionalStringColumn(this)
  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): PersonImages = {
    PersonImages(organisationId(r), userId(r), personImageId(r), url(r), description(r), mime(r), size(r), date(r))
  }
}

object PersonImagesRepository extends PersonImagesRepository with RootConnector {
  override lazy val tableName = "personimages"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personImages: PersonImages): Future[ResultSet] = {
    insert
      .value(_.organisationId, personImages.organisationId)
      .value(_.userId, personImages.userId)
      .value(_.personImageId, personImages.personImageId)
      .value(_.url, personImages.url)
      .value(_.description, personImages.description)
      .value(_.mime, personImages.mime)
      .value(_.size, personImages.size)
        .value(_.date, personImages.date)
      .future()
  }

  def findById(organisationId: String, userId: String, personImageId: String):Future[Option[PersonImages]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personImageId eqs personImageId).one()
  }
  def findAll: Future[Seq[PersonImages]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonImages(organisationId: String): Future[Seq[PersonImages]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personImageId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personImageId eqs personImageId).future()
  }
}
