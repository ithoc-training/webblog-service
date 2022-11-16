package de.ithoc.webblog;

import de.ithoc.webblog.posts.PostDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WebblogCache {

    private final Map<String, PostDto> cache = new HashMap<>();

    public void save(PostDto postDto) {
        cache.put(postDto.getId(), postDto);
    }

    public PostDto findById(String id) {
        return cache.get(id);
    }

    public Collection<PostDto> findAll() {
        return cache.values();
    }

    public void delete(String id) {
        cache.remove(id);
    }

}
