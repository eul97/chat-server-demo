package eul97.webSocketDemo.controller;

import eul97.webSocketDemo.dto.ChatRoomRequestDto;
import eul97.webSocketDemo.dto.ChatRoomResponseDto;
import eul97.webSocketDemo.entity.ChatRoom;
import eul97.webSocketDemo.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/")
    public String chat(Model model) {
        model.addAttribute("rooms", chatRoomService.getAllChatRoom());

        return "rooms";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable Long id, Model model) throws Exception {

        ChatRoom room = chatRoomService.getChatRoom(id);
        model.addAttribute("room",room);
        return "room";
    }

    @GetMapping("/new")
    public String make(Model model) {
        ChatRoomRequestDto request = new ChatRoomRequestDto();
        model.addAttribute("form", request);
        return "newRoom";
    }

    @PostMapping("room/new")
    public String makeRoom(ChatRoomRequestDto requestDto) {
        chatRoomService.createChatRoom(requestDto.getName());

        return "redirect:/";
    }
}
