package royleej9.junit.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {

	@RequestMapping(value = "/test-get", method = RequestMethod.GET)
	public Map<String, Object> testGet() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", "test123");

		return result;
	}
}
