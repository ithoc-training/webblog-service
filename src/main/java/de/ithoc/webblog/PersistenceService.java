package de.ithoc.webblog;

import de.ithoc.webblog.posts.PostDto;

import java.util.Collection;

public interface PersistenceService {

    PostDto persist(PostDto postDto);
    Collection<PostDto> readAll();

}
