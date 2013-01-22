package com.swj.msgr.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swj.msgr.api.model.Login;
import com.swj.msgr.api.resolver.LoginParam;
import com.swj.msgr.commons.exception.RequestException;
import com.swj.msgr.commons.model.ErrorCode;
import com.swj.msgr.member.exception.MemberNotExistException;
import com.swj.msgr.member.model.Member;
import com.swj.msgr.member.service.MemberService;
import com.swj.msgr.message.exception.NotExistRoomMemberException;
import com.swj.msgr.message.model.Message;
import com.swj.msgr.message.model.Room;
import com.swj.msgr.message.model.RoomMember;
import com.swj.msgr.message.service.MessageService;
import com.swj.msgr.message.service.RoomService;

@Controller
@RequestMapping("/message")
public class MessageController {

	private final static Logger logger = Logger
			.getLogger(MessageController.class);

	@Resource
	RoomService roomService;

	@Resource
	MemberService memberService;

	@Resource
	MessageService messageService;

	@RequestMapping("/read")
	@ResponseBody
	public List<Message> listMessage(
			@LoginParam Login login, 
			@RequestParam("rid") long rid, 
			@RequestParam(value="msid", required=false) Long msid,
			@RequestParam(value="cnt", required=false) Integer cnt
			) {

		Member member = login.getMember();
		
		if (!isRoomMember(member.getMid(), rid))
			throw new NotExistRoomMemberException();

		return messageService.listMessage(rid, msid, cnt);
	}

	@RequestMapping("/write")
	@ResponseBody
	public Message sendMessage(@LoginParam Login login, long rid, String msg) {

		Member member = login.getMember();

		if (!isRoomMember(member.getMid(), rid))
			throw new NotExistRoomMemberException();

		return messageService.setMessage(member, rid, msg);
	}
	
	@RequestMapping("/myroom")
	@ResponseBody
	public List<RoomMember> getMyroom(@LoginParam Login login) {
		return roomService.getRoomMemberMember(login.getMember().getMid());
	}

	@RequestMapping("/dash")
	@ResponseBody
	public long dash(@LoginParam Login login,
			@RequestParam("tomid") long toMemberId,
			@RequestParam(value="rid", required=false) Long rid) {

		logger.debug(toMemberId);

		Member toMember = memberService.getMember(toMemberId);
		if (toMember == null)
			throw new MemberNotExistException();
		
		if (login.getMember().getMid() == toMemberId)
			throw new RequestException(ErrorCode.FAIL_TARGET_MEMBER_IS_YOU);
		
		if (rid != null && isRoomMember(login.getMember().getMid(), rid)){
			if (!isRoomMember(toMemberId, rid))
			roomService.setRoomMember(toMember, rid, login.getMember());
			return rid;
		}
		
		List<RoomMember> myroom = roomService.getRoomMemberMember(login.getMember().getMid());
		
		Map<Long, Integer> myroomMap = new HashMap<Long, Integer>();
		
		List<Long> toRoom = new ArrayList<Long>();
		
		for (RoomMember roomMember : myroom) {
			if (roomMember.getMid() == toMemberId)
				toRoom.add(roomMember.getRid());
			
			if (myroomMap.containsKey(roomMember.getRid()))
				myroomMap.put(roomMember.getRid(), myroomMap.get(roomMember.getRid()) + 1);
			else
				myroomMap.put(roomMember.getRid(), 1);
		}
		

		for (Long roomid : toRoom) {
			if (myroomMap.containsKey(roomid) && myroomMap.get(roomid) == 1)
				return roomid;
		}
		

		Room room = roomService.setRoom(login.getMember(), "dash");

		roomService.setRoomMember(toMember, room.getRid(), login.getMember());

		return room.getRid();
	}

	private boolean isRoomMember(long mid, long rid) {
		List<RoomMember> roomMember = roomService.getRoomMember(rid, null);
		for (RoomMember rm : roomMember) {
			if (rm.getMid() == mid)
				return true;
		}
		return false;
	}

}
