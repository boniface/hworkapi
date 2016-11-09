package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.Language
import services.common.demographics.Impl.LanguageServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait LanguageService {

  def createOrUpdate(language:Language): Future[ResultSet]

  def getLanguageById(language: String): Future[Option[Language]]

  def getLanguages(language: String): Future[Seq[Language]]

}

object LanguageService{
  def apply: LanguageService = new LanguageServiceImpl()
}