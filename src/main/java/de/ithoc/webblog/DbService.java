package de.ithoc.webblog;

import de.ithoc.webblog.posts.Post;
import de.ithoc.webblog.posts.PostDto;
import de.ithoc.webblog.posts.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Profile("!dev")
public class DbService implements PersistenceService {

    private final PostRepository postRepository;

    public DbService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostDto persist(PostDto postDto) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post = postRepository.save(post);

        postDto.setId(post.getId().toString());

        return postDto;
    }

    @Override
    public Collection<PostDto> readAll() {

        return postRepository.findAll().stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId().toString());
            postDto.setTitle(post.getTitle());
            postDto.setContent(post.getContent());
            return postDto;
        }).toList();
    }

}
