package de.ithoc.webblog;

import de.ithoc.webblog.posts.PostDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@Profile("dev")
public class InMemService implements PersistenceService {

    private Long sequence = 0L;
    private final WebblogCache webblogCache;

    public InMemService(WebblogCache webblogCache) {
        this.webblogCache = webblogCache;
    }


    @Override
    public PostDto persist(PostDto postDto) {

        sequence += 1;
        postDto.setId(sequence.toString());

        webblogCache.save(postDto);

        return postDto;
    }

    @Override
    public Collection<PostDto> readAll() {
        return webblogCache.findAll();
    }

}
