package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.FriendList;
import com.example.casestudyteam2.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/friend-list")
public class FriendListController {
    @Autowired
    private IFriendListService friendListService;

    @GetMapping
    public ResponseEntity<Iterable<FriendList>> showAllFriendList(){
        Iterable<FriendList> friendLists = friendListService.findAll();
        return new ResponseEntity<>(friendLists, HttpStatus.OK);
    }

    @GetMapping("/{idFriendList}")
    public ResponseEntity<Optional<FriendList>> showDetailFriendList(@PathVariable("idFriendList")Long id){
        Optional<FriendList> friendList = friendListService.findById(id);
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FriendList> createNewFriendList(@RequestBody FriendList friendList){
        FriendList friendList1 = friendListService.save(friendList);
        if (friendList1 == null){
            new ResponseEntity<>(friendList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    @GetMapping("/accept")
    public void acceptFriendRequest(@RequestParam("userFromId") Long userFromId, @RequestParam("userToId")Long userToId){
        friendListService.acceptFriend(userToId,userFromId);
    }

    @GetMapping("/block")
    public void blockFriendRequest(@RequestParam("userFromId") Long userFromId, @RequestParam("userToId")Long userToId){
        friendListService.blockFriend(userToId, userFromId);
    }

    @GetMapping("/{idUserFrom}/{idUserTo}/checkFriendShip")
    public ResponseEntity<String> getFriendShip(@PathVariable("idUserFrom")Long idUserFrom, @PathVariable("idUserTo") Long idUserTo){
        String status = friendListService.checkFriendsStatus(idUserFrom, idUserTo);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

//    @GetMapping("/{idUserFrom}/{idUserTo}/deleteFriendStatus")
//    public ResponseEntity<String> unFriend(@PathVariable("idUserFrom")Long idUserFrom, @PathVariable("idUserTo")Long idUserTo){
//        friendListService.deleteByUserFrom_IdUserAndUserTo_IdUser(idUserFrom, idUserTo);
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }

    @GetMapping("/{idUserFrom}/{idUserTo}/blockFriend")
    public ResponseEntity<String> blockFriend(@PathVariable("idUserFrom")Long idUserFrom,@PathVariable("idUserTo")Long idUserTo){
        friendListService.blockFriend(idUserFrom,idUserTo);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/{idUserFrom}/{idUserTo}/addFriend")
    public ResponseEntity<String> addFriend(@PathVariable("idUserFrom")Long idUserFrom, @PathVariable("idUserTo")Long idUserTo){
        friendListService.addFriend(idUserFrom,idUserTo);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
