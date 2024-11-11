package com.quad.ScanwordApp.model;


import com.quad.ScanwordApp.model.json.Cell;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "dictionary_id",nullable = false)
    private Dictionary dictionary;

    @Builder.Default
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content",nullable = false)
    private List<Cell> content = new ArrayList<>();

    @Column(name = "is_created",nullable = false)
    private boolean isCreated;

    @Column(name = "number_of_hints",nullable = false)
    private int numberOfHints;
}
