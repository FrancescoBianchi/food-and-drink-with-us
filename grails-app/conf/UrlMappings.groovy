class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" {
			controller = "Guest"
			action = "list"
		}
        "500"(view:'/error')
	}
}
