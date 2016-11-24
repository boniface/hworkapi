package services.users

import com.websudos.phantom.dsl._
import domain.users.PersonLanguage
import services.users.impl.PersonLanguageServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonLanguageService {
  def createOrupdate(personLanguage : PersonLanguage):Future[ResultSet]

  def getPersonLanguageById(organisationId : String,userId: String, personLanguageid: String): Future[Option[PersonLanguage]]
  def getAllPersonLanguages(person: String):Future[Seq[PersonLanguage]]
}

object PersonLanguageService{
  def apply: PersonLanguageService = new PersonLanguageServiceImpl()
}