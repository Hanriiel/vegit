package vegit.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name="tag")
@Entity
public class Tag {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="tag_id")
private Long id;

@Column(name="tag_name", unique = true)
private String tagName;

 @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<ProductTag> productTags = new HashSet<>();

@OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<RecipeTag> recipeTags = new HashSet<>();

public Tag(){

}

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
