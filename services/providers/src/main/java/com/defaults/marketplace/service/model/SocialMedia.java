package com.defaults.marketplace.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "social_media")
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "media_id")
    private int id;

    @Column(name = "media_name", nullable = false)
    private String mediaName;

    @Column(name = "link", nullable = false)
    private String link;
}
