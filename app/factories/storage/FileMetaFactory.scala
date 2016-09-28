package factories.storage

import domain.storage.FileMeta

/**
  * Created by SONY on 2016-09-28.
  */
class FileMetaFactory
{
  def createFileMeta(values: Map[String, String]): FileMeta=
  {
    FileMeta(organisationId = values("organisationId"), fileName = values("fileName"), contentType = values("contentType"))
  }

}
