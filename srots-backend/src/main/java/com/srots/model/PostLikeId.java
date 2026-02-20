package com.srots.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor // Lombok generates the empty constructor
@AllArgsConstructor // Lombok generates the (String, String) constructor
public class PostLikeId implements Serializable {
    private String postId;
    private String userId;

}