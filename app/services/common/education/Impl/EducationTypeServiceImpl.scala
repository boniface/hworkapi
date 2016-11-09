package services.common.education.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.education.EducationType
import repositories.common.education.EducationTypeRepository
import services.Service
import services.common.education.EducationTypeService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
class EducationTypeServiceImpl extends EducationTypeService with Service {
  override def createOrUpdate(educationType: EducationType): Future[ResultSet] = {
    EducationTypeRepository.save(educationType)
  }

  override def getEducationTypeById(educationType: String): Future[Option[EducationType]] = {
    EducationTypeRepository.getEducationTypeById(educationType)
  }

  override def getEducationTypes(educationType: String): Future[Seq[EducationType]] ={
    EducationTypeRepository.getEducationTypes(educationType)
  }
}
