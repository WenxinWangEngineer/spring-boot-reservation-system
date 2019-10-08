package com.tiffanywang.happy.data.repository;

import com.tiffanywang.happy.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
    Room findByNumber(String number);
    
    Iterable<Room> findAll();
    
}
