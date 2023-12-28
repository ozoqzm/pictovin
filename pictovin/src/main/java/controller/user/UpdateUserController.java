package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
   		String updateId = UserSessionUtils.getLoginUserId(request.getSession());

   		log.debug("UpdateForm Request : {}", updateId);
    		
    	UserManager manager = UserManager.getInstance();
		User user = manager.findUser(updateId);
		
		String updateName = request.getParameter("name");
		String updateEmail = request.getParameter("email");
		
		manager.update(updateId, updateName, updateEmail);
		return "redirect:/album/list";
    }
}