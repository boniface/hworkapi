package factories.storage

import domain.storage.StorageUrl
import org.scalatest.FunSuite

/**
  * Created by SONY on 2016-09-28.
  */
class StorageUrlFactoryTest extends FunSuite{

  test("testCreateStorageUrl"){
    val storageU = new StorageUrlFactory;

    val values = Map("organisationId" -> "1",
      "fileResultsId" -> "SU7",
      "name" -> "StoU",
      "url" -> "https://sto.com"
    );

    val sUrl = storageU.createStorageUrl(values);

    assert(sUrl == StorageUrl(organisationId = "1",
      storageUrlId = "SU7",
      name = "StoU",
      url = "https://sto.com"
    ));
  }
}