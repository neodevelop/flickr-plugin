class UrlMappings {

	static mappings = {
		"/flickrSample"(controller:"flickr")

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
