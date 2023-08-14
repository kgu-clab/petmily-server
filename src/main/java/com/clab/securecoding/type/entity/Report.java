package com.clab.securecoding.type.entity;

import com.clab.securecoding.type.etc.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "writer")
    private User writer;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "board")
    private Board board;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    private String title;

    private String content;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
