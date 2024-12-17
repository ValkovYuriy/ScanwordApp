package com.quad.ScanwordApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "draft_scanword")
public class DraftScanword {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "scanword_id",nullable = false)
    private Scanword scanword;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Builder.Default
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content")
    private List<Cell> content = new ArrayList<>();


    @Column(name = "is_solved")
    private Boolean solved;


    @Column(name = "number_of_hints")
    private Integer numberOfHints;


    @Column(name = "preview")
    private byte[] preview;
}
