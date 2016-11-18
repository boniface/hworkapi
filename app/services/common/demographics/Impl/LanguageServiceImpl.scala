package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.Language
import repositories.common.demographics.LanguageRepository
import services.Service
import services.common.demographics.LanguageService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class LanguageServiceImpl extends LanguageService with Service{

  override def createOrUpdate(language: Language): Future[ResultSet] = {
    LanguageRepository.save(language)
  }

  override def getLanguageById(id: String): Future[Option[Language]] = {
    LanguageRepository.getLanguageById(id)
  }

  override def getLanguages(language: String): Future[Seq[Language]] = {
    LanguageRepository.getLanguages(language)
  }
}
