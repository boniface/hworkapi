package services.users

import com.websudos.phantom.dsl._
import domain.users.UserLanguage
import services.users.impl.UserLanguageServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserLanguageService {
  def createOrupdate(personLanguage : UserLanguage):Future[ResultSet]

  def getPersonLanguageById(organisationId : String,userId: String, personLanguageid: String): Future[Option[UserLanguage]]
  def getAllPersonLanguages(person: String):Future[Seq[UserLanguage]]
}

object UserLanguageService{
  def apply: UserLanguageService = new UserLanguageServiceImpl()
}
