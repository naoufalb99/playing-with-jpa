package com.naoufalb.movies.domain;

import com.naoufalb.movies.holder.ColumnNames;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
public abstract class AbstractEntity implements Serializable {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue
    @Column(name = ColumnNames.ID, updatable = false, nullable = false)
    UUID id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = ColumnNames.CREATED_AT)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = ColumnNames.UPDATED_AT)
    LocalDateTime updatedAt;
}
