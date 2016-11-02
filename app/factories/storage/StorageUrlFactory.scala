package factories.storage

import domain.storage.StorageUrl

/**
  * Created by SONY on 2016-09-28.
  */
class StorageUrlFactory
{
  def createStorageUrl(values: Map[String, String]): StorageUrl=
  {
    StorageUrl(organisationId = values("organisationId"), storageUrlId = values("storageUrlId"),
      name = values("name"), url = values("url"))
  }

}
