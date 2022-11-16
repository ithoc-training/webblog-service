package de.ithoc.webblog;

import de.ithoc.webblog.posts.PostDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Profile("!dev")
public class DbService implements PersistenceService {

    @Override
    public PostDto persist(PostDto postDto) {
        return null;
    }

    @Override
    public Collection<PostDto> readAll() {
        return null;
    }

}
