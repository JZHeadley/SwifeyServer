package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@SuppressWarnings("unused")
@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    override fun findAll(): MutableList<User>?

    @Query(value = "Select * from Users where userId = ?1", nativeQuery = true)
    fun findById(userId: String): User?

    @Query(value = "SELECT * FROM users " +
            "WHERE firstName LIKE CONCAT('%',?1,'%') " +
            "OR lastName LIKE CONCAT('%', ?1,'%')", nativeQuery = true)
    fun findUserLike(searchString: String): List<User>

//        @Modifying
//    @Query(value = "insert into users values(:userId,:firstName,:lastName,:dob,sysdate,numSwipes,:phoneId)", nativeQuery = true)
//    @Transactional
//    fun save(userId: String, firstName: String, lastName: String, dob: Date, numSwipes: Int, phoneId: Int): Int


    @Modifying
    @Transactional
    @Query(value = "update users set messagingId = ?2 where userID=?1'", nativeQuery = true)
    fun setUserMessagingId(userId: String, messagingId: String)

    @Query(value = "SELECT u.* " +
            "FROM followings f " +
            "JOIN users u ON f.followerId = u.userID " +
            "where f.userId = ?1", nativeQuery = true)
    fun findFollowersOfUser(userId: String): List<User>
}