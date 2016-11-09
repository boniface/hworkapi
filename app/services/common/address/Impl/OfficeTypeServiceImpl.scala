package services.common.address.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.address.OfficeType
import repositories.common.address.OfficeTypeRepository
import services.Service
import services.common.address.OfficeTypeService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
class OfficeTypeServiceImpl extends OfficeTypeService with Service{

  override def createOrUpdate(officeType: OfficeType): Future[ResultSet] = {
    OfficeTypeRepository.save(officeType)
  }

  override def getOfficeTypeById(officeType: String): Future[Option[OfficeType]] = {
    OfficeTypeRepository.getOfficeTypeById(officeType)
  }

  override def getOfficeTypes(officeType: String): Future[Seq[OfficeType]] = {
    OfficeTypeRepository.getOfficeTypes(officeType)
  }
}
