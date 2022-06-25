package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IFriendListRepository extends JpaRepository<FriendList, Long> {

//    @Query(value = "DELETE FROM friend_list WHERE user_from_id_user = :userFromId AND user_to_id_user = :userToId", nativeQuery = true)
//    void deleteByUserFrom_IdUserAndUserTo_IdUser(Long idUserFrom, Long idUserTo);

    void deleteByUserFrom_IdAndUserTo_Id(Long idUserFrom, Long idUSerTo);

    @Query(value = "select * from friend_list where status = 'friend' and (user_from_id_user =:idUser or user_to_id_user = :idUser)", nativeQuery = true)
    Iterable<FriendList> findFriendListByIdUser(@Param("idUser") Long idUser);


    @Modifying
    @Query(value = "UPDATE friend_list SET status = 'friend' WHERE user_from_id = :userFromId AND user_to_id = :userToId", nativeQuery = true)
    void acceptFriendRequest(@Param("userFromId") Long userFromId, @Param("userToId") Long userToId);

    @Modifying
    @Query(value = "UPDATE friend_list SET status = 'block' WHERE user_from_id = :idUserFrom AND user_to_id = :idUserTo", nativeQuery = true)
    void blockFriendRequest(@Param("idUserFrom") Long idUserFrom, @Param("idUserTo") Long idUserTo);

    @Query(value = "select status from friend_list where user_from_id =:idUserFrom and user_to_id = :idUserTo", nativeQuery = true)
    String checkFriendsStatus(@Param("idUserFrom") Long idUserFrom, @Param("idUserTo") Long idUserTo);

    @Query(value = "select * from friend_list where user_to_id_user = :idUser and status = 'pending'", nativeQuery = true)
    Iterable<FriendList> findAllPendingByIdUser(@Param("idUser") Long idUser);

    @Modifying
    @Query(value = "INSERT INTO friend_list (status, user_from_id, user_to_id) VALUES ('pending', :idUserFrom, :idUserTo)", nativeQuery = true)
    void addFriend(@Param("idUserFrom") Long idUserFrom, @Param("idUserTo") Long idUserTo);



}
