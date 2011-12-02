/* Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Stores the salient attributes of a Flickr Image.
 *
 * @author egrant
 *
 */
package mx.org.grails

/**
 * TODO: Add a method which returns the page URL of the media, see:
 * 1: http://www.bram.us/2008/01/12/my-priceless-flickr-tip-how-to-find-the-original-flickr-photo-url-and-user-from-a-static-flickr-image-url/
 * and
 * 2: http://www.flickr.com/photos/83399230@N00/2177060015/ (just an example page url)
 * 
 * TODO: Add another method which takes is to the 'all sizes' page for the media.
 * 
 * @author egrant
 *
 */
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
 
  def getUrl() {
    String url = new String(IMAGE_URL_STANDARD)
    url = url.replace(FARM_ID_TOKEN, farmId)
    url = url.replace(SERVER_ID_TOKEN, serverId)
    url = url.replace(ID_TOKEN, mediaId)
    url = url.replace(SECRET_TOKEN, secret)
    return url
  }

  def getUrl(SizeSuffix sizeSuffix) {
    String url = new String(IMAGE_URL_SUFFIXED)
    url = url.replace(FARM_ID_TOKEN, farmId)
    url = url.replace(SERVER_ID_TOKEN, serverId)
    url = url.replace(ID_TOKEN, mediaId)
    url = url.replace(SECRET_TOKEN, secret)
    url = url.replace(SIZE_SUFFIX, sizeSuffix.getSuffix())
    return url
  }
}