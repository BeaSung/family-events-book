package com.eventsbook.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Friend {
    @Column(name = "friend_name", nullable = false)
    private String name;

    @Column(name = "relationship_with_friend")
    private String relationshipWithFriend;

    public String getName() {
        return name;
    }

    public Optional<String> getRelationshipWithFriend() {
        return Optional.ofNullable(relationshipWithFriend);
    }
}
