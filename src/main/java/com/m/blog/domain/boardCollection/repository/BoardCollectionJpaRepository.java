package com.m.blog.domain.boardCollection.repository;

import com.m.blog.domain.boardCollection.entity.BoardCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BoardCollectionJpaRepository extends JpaRepository<BoardCollection, Integer> {
    BoardCollection findById(int id);
    List<BoardCollection> findAll();

    @Query(
    value = "select bc.id as board_collection_id, bc.name as board_collection_name\n" +
            "\t, b.id as board_id, b.name as board_name, count(*) as posting_count from \n" +
            "board_collection bc\n" +
            "join board b\n" +
            "on 1=1\n" +
            "and bc.id = b.board_collection_id\n" +
            "join posting p\n" +
            "on 1=1\n" +
            "and b.board_collection_id = p.board_collection_id\n" +
            "and b.id = p.board_id\n" +
            "group by \n" +
            "bc.id, bc.name, b.id, b.name, b.description, b.created_time, b.updated_time ", nativeQuery = true)
    List<Object[]> getMenuDtos();
}
