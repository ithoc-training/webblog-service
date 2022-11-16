package de.ithoc.webblog.posts;

import de.ithoc.webblog.communication.Inbound;
import de.ithoc.webblog.communication.Outbound;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto to(Inbound source);
    Outbound to(PostDto postDto);

}
