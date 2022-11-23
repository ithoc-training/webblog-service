package de.ithoc.webblog;

import de.ithoc.webblog.posts.PostDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@Profile("!dev")
public class DbService implements PersistenceService {

    @Value("${db.username}")
    private String dbUsername;

    private final WebblogCache webblogCache;

    public DbService(WebblogCache webblogCache) {
        this.webblogCache = webblogCache;
    }


    @Override
    public PostDto persist(PostDto postDto) {

        postDto.setId(UUID.randomUUID().toString());
        postDto.setTitle("Username");
        postDto.setContent(dbUsername);

        webblogCache.save(postDto);

        return postDto;
    }

    @Override
    public Collection<PostDto> readAll() {

        return webblogCache.findAll();
    }

}
