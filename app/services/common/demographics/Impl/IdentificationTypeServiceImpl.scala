package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.IdentificationType
import repositories.common.demographics.IdentificationTypeRepository
import services.Service
import services.common.demographics.IdentificationTypeService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/28.
  */
class IdentificationTypeServiceImpl extends IdentificationTypeService with Service{
  override def createOrUpdate(identificationType: IdentificationType): Future[ResultSet] = {
    IdentificationTypeRepository.save(identificationType)
  }

  override def getIdentificationTypeById(id: String): Future[Option[IdentificationType]] = {
    IdentificationTypeRepository.getIdentificationTypeById(id)
  }

  override def getIdentificationTypes(id: String): Future[Seq[IdentificationType]] = {
    IdentificationTypeRepository.getIdentificationTypes(id)
  }
}
