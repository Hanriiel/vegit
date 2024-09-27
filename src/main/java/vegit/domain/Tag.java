package vegit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tag {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

private String tagName;

public Tag(String tagName) {
    this.tagName = tagName;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getTagName() {
    return tagName;
}

public void setTagName(String tagName) {
    this.tagName = tagName;
}

}
