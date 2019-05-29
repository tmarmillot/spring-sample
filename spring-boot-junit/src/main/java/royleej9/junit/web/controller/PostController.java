package royleej9.junit.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@RequestMapping(value = "/test-post", method = RequestMethod.POST)
	public Map<String, Object> testPost() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", "id-12");

		return result;
	}
}
