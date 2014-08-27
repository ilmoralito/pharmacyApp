class UrlMappings {

	static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
        // apply constraints here
      }
    }

    "/"(controller:"login")
    "500"(view:'/error')
	}
}
