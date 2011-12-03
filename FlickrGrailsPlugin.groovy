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

class FlickrGrailsPlugin {
    def version = "0.2"
    def grailsVersion = "1.3.7 > *"
    def dependsOn = [:]
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "José Juan Reyes Zuñiga"
    def authorEmail = "neodevelop@gmail.com"
    def title = "Flickr Plugin"
    def description = '''\\
This plugin is intended to show some flickr photos based in a tag...
'''
    def documentation = "http://grails.org/plugin/flickr-plugin"
}
