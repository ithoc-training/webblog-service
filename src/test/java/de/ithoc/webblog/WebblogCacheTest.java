package de.ithoc.webblog;

import de.ithoc.webblog.posts.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class WebblogCacheTest {

    private WebblogCache webblogCache;


    @BeforeEach
    public void setUp() {
        webblogCache = new WebblogCache();
    }


    @Test
    void save() {
        String uuid = UUID.randomUUID().toString();

        webblogCache.save(create(uuid));

        assertNotNull(webblogCache.findById(uuid.toString()));
    }

    @Test
    void findById() {
        List<PostDto> postDtos = create(3);
        String uuid = postDtos.get(1).getId();
        postDtos.forEach(post -> webblogCache.save(post));

        PostDto postDto = webblogCache.findById(uuid);

        assertNotNull(postDto);
    }

    @Test
    void findAll() {
        List<PostDto> postDtos = create(3);
        postDtos.forEach(post -> webblogCache.save(post));

        Collection<PostDto> all = webblogCache.findAll();

        assertEquals(3, all.size());
    }

    @Test
    void delete() {
        List<PostDto> postDtos = create(3);
        String uuid = postDtos.get(1).getId();
        postDtos.forEach(post -> webblogCache.save(post));

        webblogCache.delete(uuid);

        assertEquals(2, webblogCache.findAll().size());
    }


    private PostDto create(String id) {
        String text = id;
        PostDto postDto = new PostDto();
        postDto.setId(text);
        postDto.setTitle(text + "-" + text);
        postDto.setContent(text + "-" + text + "-" + text + "-" + text);
        return postDto;
    }

    private List<PostDto> create(int size) {
        List<PostDto> postDtos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            postDtos.add(create("" + i));
        }
        return postDtos;
    }

}