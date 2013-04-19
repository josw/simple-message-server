package com.swj.msgr.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swj.msgr.api.model.Login;
import com.swj.msgr.api.model.ResultMember;
import com.swj.msgr.api.model.ResultMemberList;
import com.swj.msgr.api.model.ResultValue;
import com.swj.msgr.api.resolver.LoginParam;
import com.swj.msgr.commons.exception.RequestException;
import com.swj.msgr.commons.model.StatusCd;
import com.swj.msgr.member.exception.MemberAlreadyFriendException;
import com.swj.msgr.member.exception.MemberNotExistException;
import com.swj.msgr.member.exception.MemberNotFriendException;
import com.swj.msgr.member.model.Friend;
import com.swj.msgr.member.model.Member;
import com.swj.msgr.member.service.FriendService;
import com.swj.msgr.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final static Logger logger = Logger.getLogger(MemberController.class);
	
	@Resource
	MemberService memberService;
	
	@Resource
	FriendService friendService;
	
	@Value("#{configProperties['file.path']}")
	String filePath;
	
	@RequestMapping("/wait")
	@ResponseBody
	public ResultMemberList getWaitList(@LoginParam Login login, @RequestParam(required=false) Integer page) {
		
		ResultMemberList resultMemberList = new ResultMemberList();
		
		page = (page==null)?0:page;
		
		resultMemberList.setPage(page);
		
		List<Member> waitMemberList = memberService.getWaitList(page);
		
		if (waitMemberList.isEmpty())
			resultMemberList.setMembers(new ArrayList<Member>());
		else
			resultMemberList.setMembers(waitMemberList);
		
		return resultMemberList;
	}
	
	@RequestMapping("/wait/around")
	@ResponseBody
	public ResultMemberList getWaitAroundList(@LoginParam Login login, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer distance) {
		
		ResultMemberList resultMemberList = new ResultMemberList();
		
		page = (page==null)?0:page;
		
		resultMemberList.setPage(page);
		
		List<Member> waitMemberList = memberService.getWaitListAround(login.getMember(), distance, page);
		
		if (waitMemberList.isEmpty())
			resultMemberList.setMembers(new ArrayList<Member>());
		else
			resultMemberList.setMembers(waitMemberList);
		
		return resultMemberList;
	}
	
	@RequestMapping("/prove")
	@ResponseBody
	public Login proveRtoken(@LoginParam Login login) {
		return login;
	}
	

	
	@RequestMapping(value="/update/image")
	@ResponseBody
	public Map<String, Object> updateProfile(@RequestParam("afile") Part file, HttpServletRequest req, @LoginParam Login login) {
		

		//InputStream inputStream = file.getInputStream()
		
		String orgName = getFileName(file);
		
		logger.debug (">>>>>>>>>>>>>>>" + orgName);
		
		
		Pattern p = Pattern.compile("\\.(png|jpg|gif)$");
		Matcher m = p.matcher(orgName);

		String ext = "";
		if (m.find()) {
			ext = m.group();
		}
		
		if (StringUtils.isBlank(ext))
			throw new RequestException();
		
		int rem = (int) (login.getMid() % 1000);
		
		
		StringBuilder dir = new StringBuilder(req.getSession().getServletContext().getRealPath("/img/"));
		dir.append("/").append(rem);
		
		//.append("/").append(login.getMid()).append(ext);
		
		StringBuilder filename = new StringBuilder();
		filename.append(login.getMid()).append(ext);
	
		File dstFile = new File (dir.toString());
		
		logger.debug (dir.toString());
				
		try {
			boolean ok = dstFile.mkdirs();
			
			logger.debug(ok);
			
			file.write(dir.toString() + "/" + filename.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("filename", filename.toString());
		result.put("filepath", "/img/" + rem);
		result.put("mid", login.getMid());

		return result;
	}
	
	@RequestMapping("/friends")
	@ResponseBody
	public ResultMemberList getFriends(@LoginParam Login login, @RequestParam(required=false) Integer page) {
		
		ResultMemberList resultMemberList = new ResultMemberList();
		
		page = (page==null)?0:page;
		
		resultMemberList.setPage(page);
		
		List<Friend> friends = friendService.getFriends(login.getMember(), page);

		if (friends.isEmpty()) {
			resultMemberList.setMembers(new ArrayList<Member>());
			return resultMemberList;
		}
		
		
		List<Long> frList = new ArrayList<Long>();
		for (Friend fr : friends) {
			frList.add(fr.getFriend_mid());
		}
		
		resultMemberList.setMembers(memberService.getMembersIn(frList));
		
		return resultMemberList;
	}
	
	@RequestMapping("/friends/add")
	@ResponseBody
	public ResultValue addFriend(@LoginParam Login login, @RequestParam("mid") long friend_mid) {
		
		logger.debug (friend_mid);
		
		Member friend = memberService.getMember(friend_mid);
		
		logger.debug (friend);
		
		if (friend == null || !friend.getStatus().equals(StatusCd.OK.toString()))
			throw new MemberNotExistException();

		if (friendService.getFriend(login.getMember(), friend_mid) != null)
			throw new MemberAlreadyFriendException();
		

		return new ResultValue(friendService.addFriend(login.getMember(), friend_mid));
	}
	
	@RequestMapping("/friends/delete")
	@ResponseBody
	public ResultValue deleteFriend(@LoginParam Login login, @RequestParam("mid") int friend_mid) {
		
		Member friend = memberService.getMember(friend_mid);
		
		if (friend == null)
			throw new MemberNotExistException();
		
		if (friendService.getFriend(login.getMember(), friend_mid) == null)
			throw new MemberNotFriendException();
		
		return new ResultValue(friendService.deleteFriend(login.getMember(), friend_mid));
	}
	
	@RequestMapping("/{mid}/info")
	@ResponseBody
	public ResultMember getMember(@LoginParam Login login, @PathVariable("mid") long mid) {
		
		ResultMember resultMember = new ResultMember();
		resultMember.setIsfriend(friendService.getFriend(login.getMember(), mid) != null);
		resultMember.setMember(memberService.getMember(mid));
		
		return resultMember;
		
	}
	
	
	private String getFileName(Part part) {
		  String partHeader = part.getHeader("content-disposition");

		  for (String cd : partHeader.split(";")) {
		    if (cd.trim().startsWith("filename")) {
		      return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
		    }
		  }
		  return null;
	}

}
