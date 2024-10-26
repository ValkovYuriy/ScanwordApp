package com.quad.ScanwordApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "scanword")
public class Scanword {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "width",nullable = false)
    private int width;

    @Column(name = "height",nullable = false)
    private int height;

    @Column(name = "preview",nullable = false)
    private byte[] preview;

    @Builder.Default
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content",nullable = false)
    private List<Cell> content = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "scanwords")
    @Builder.Default
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "scanword_dictionaries",
            joinColumns = @JoinColumn(name = "scanword_id"),
            inverseJoinColumns = @JoinColumn(name = "dictionary_id")
    )
    private List<Dictionary> dictionaries = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "scanword_melodies",
            joinColumns = @JoinColumn(name = "scanword_id"),
            inverseJoinColumns = @JoinColumn(name = "melody_id")
    )
    private List<Melody> melodies = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "scanword_images",
            joinColumns = @JoinColumn(name = "scanword_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images = new ArrayList<>();

    @Column(name = "is_created",nullable = false)
    private boolean isCreated;
}
