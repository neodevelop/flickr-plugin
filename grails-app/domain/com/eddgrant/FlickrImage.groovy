/**
 * Stores the salient attributes of a Flickr Image.
 *
 * @author egrant
 *
 */
package com.eddgrant

class FlickrImage {

  static constraints = {
  
  }

  /**
   * The 'standard' image URL, used by {@link FlickrImage#getUrl()}.
   */
  private static final String IMAGE_URL_STANDARD = "http://farm{farm-id}.static.flickr.com/{server-id}/{id}_{secret}.jpg"

  /**
   * The 'suffixed' image URL, used by {@link FlickrImage#getUrl(SizeSuffix)}.
   */
  private static final String IMAGE_URL_SUFFIXED = "http://farm{farm-id}.static.flickr.com/{server-id}/{id}_{secret}_{size-suffix}.jpg"

  /**
   * Token identifying the farm-id.
   */
  private static final String FARM_ID_TOKEN = "{farm-id}"

  /**
   * Token identifying the server-id.
   */
  private static final String SERVER_ID_TOKEN = "{server-id}"

  /**
   * Token identifying the image id.
   */
  private static final String ID_TOKEN = "{id}"

  /**
   * Token identifying the image secret.
   */
  private static final String SECRET_TOKEN = "{secret}"

  /**
   * Token identifying the size suffix.
   */
  private static final String SIZE_SUFFIX = "{size-suffix}"

  String mediaId

  String farmId

  String serverId

  String secret

  String title

  boolean publicMedia
 
  public String getUrl() {
    String url = new String(IMAGE_URL_STANDARD)
    url = url.replace(FARM_ID_TOKEN, farmId())
    url = url.replace(SERVER_ID_TOKEN, serverId())
    url = url.replace(ID_TOKEN, mediaId())
    url = url.replace(SECRET_TOKEN, secret())
    return url
  }

  public String getUrl(SizeSuffix sizeSuffix) {
    String url = new String(IMAGE_URL_SUFFIXED)
    url = url.replace(FARM_ID_TOKEN, farmId())
    url = url.replace(SERVER_ID_TOKEN, serverId())
    url = url.replace(ID_TOKEN, mediaId())
    url = url.replace(SECRET_TOKEN, secret())
    url = url.replace(SIZE_SUFFIX, sizeSuffix.getSuffix())
    return url
  }
}

enum SizeSuffix {
  SMALL_SQUARE("s"),
  THUMBNAIL("t"),
  SMALL("m"),
  LARGE("b")

  private String suffix

  SizeSuffix(String suffix) {
    this.suffix = suffix
  }

  public String getSuffix() {
    return suffix
  }
}


