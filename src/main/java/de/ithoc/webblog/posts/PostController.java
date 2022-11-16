package de.ithoc.webblog.posts;

import de.ithoc.webblog.PersistenceService;
import de.ithoc.webblog.communication.Inbound;
import de.ithoc.webblog.communication.Outbound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostMapper postMapper;
    private final PersistenceService persistenceService;

    public PostController(PostMapper postMapper, PersistenceService persistenceService) {
        this.postMapper = postMapper;
        this.persistenceService = persistenceService;
    }


    @PostMapping
    public ResponseEntity<Outbound> add(@RequestBody Inbound inbound) {

        PostDto postDto = postMapper.to(inbound);
        PostDto persistedPostDto = persistenceService.persist(postDto);
        Outbound outbound = postMapper.to(persistedPostDto);

        return new ResponseEntity<>(outbound, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Outbound>> list() {

        Collection<PostDto> postDtos = persistenceService.readAll();
        List<Outbound> outbounds = postDtos.stream().map(postMapper::to).toList();

        return new ResponseEntity<>(outbounds, HttpStatus.OK);
    }

}
