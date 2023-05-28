package com.defaults.marketplace.productsearch.models.entities;

import com.defaults.marketplace.productsearch.models.enumerations.SocialMediaType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SocialMediaType type;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "provider_id")
    private Integer providerId;

}

