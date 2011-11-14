package org.cloudfoundry.identity.uaa.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller that sends login info (e.g. prompts) to clients wishing to authenticate.
 *
 * @author Dave Syer
 */
@Controller
public class LoginInfoEndpoint {
	
	private List<Prompt> prompts = Arrays.asList(new Prompt("username", "text", "Username"), new Prompt("password", "hidden", "Password"));

	public void setPrompts(List<Prompt> prompts) {
		this.prompts = prompts;
	}

	@RequestMapping (value = "/login_info")
	@ResponseBody
	public Map<String,Object> loginInfo() {
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		for (Prompt prompt : prompts) {
			map.put(prompt.getName(), prompt.getDetails());
		}
		return Collections.<String,Object>singletonMap("prompts", map );
	}

}