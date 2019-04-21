package pers.suqirong.ssm_demo.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pers.suqirong.ssm_demo.dao.UserDao;
import pers.suqirong.ssm_demo.domain.User;

@Controller
@RequestMapping("user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserDao userDao;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		System.out.println("userId:" + userId);
		User user = userDao.selectByPrimaryKey(userId);
		model.addAttribute("user", user);
		return "index";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseMessage<User> add(@RequestBody User user) {
		System.out.println(user);
		if(userDao.insert(user)==1) {
			return new ResponseMessage<User>(user);
		}else {
			return new ResponseMessage<User>(500);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseMessage<User> delete(@RequestParam("userId") Integer userId) {
		System.out.println("userId=" + userId);
		if(userDao.deleteByPrimaryKey(userId)==1) {
			return new ResponseMessage<User>(200);
		}else {
			return new ResponseMessage<User>(500);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseMessage<User> get(@RequestParam("userId") Integer userId) {
		System.out.println(userId);
		User user = userDao.selectByPrimaryKey(userId);
		if(user==null) {
			return new ResponseMessage<User>(200, "没有对应记录");
		}else {
			return new ResponseMessage<User>(user);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseMessage<User> update(@RequestBody User user, @RequestParam(value="userId",required = false) Integer userId) {
		System.out.println(userId);
		System.out.println(user);
		if(userId == null) {
			if(user.getId() == null) {
				return new ResponseMessage<User>(400, "参数错误");
			}
		}else {
			user.setId(userId);
		}
		if(userDao.updateByPrimaryKeySelective(user)==1) {
			return new ResponseMessage<User>(user);
		}else {
			return new ResponseMessage<User>(500);
		}
	}
}