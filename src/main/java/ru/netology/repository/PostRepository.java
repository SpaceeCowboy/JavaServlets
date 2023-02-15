package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class PostRepository {
  private final ConcurrentHashMap<Long,Post> rep = new ConcurrentHashMap<>();
  private final AtomicLong count = new AtomicLong();

  public ConcurrentHashMap<Long,Post> all() {
    return rep;
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(rep.get(id));
  }

  public Post save(Post post) {
    if(post.getId() == 0){
      post.setId(count.incrementAndGet());
      return rep.put(post.getId(),post);
    }else if(post.getId() != 0){
      return rep.put(post.getId(),post);
    }
    return null;
  }


  public void removeById(long id) {
    rep.remove(id);
  }
}
