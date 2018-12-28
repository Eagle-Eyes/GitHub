package ir.app_service.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class AppPageRequest {

    private int page = 0;
    private int size = 10;

    public PageRequest getPageable() {
        return PageRequest.of(this.page, this.size);
    }
}
