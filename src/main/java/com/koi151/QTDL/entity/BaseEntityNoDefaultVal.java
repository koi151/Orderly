package com.koi151.QTDL.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntityNoDefaultVal implements Serializable {

    @Serial
    private static final long serialVersionUID = 4746498586807182115L;

    @Column(name = "createdAt", columnDefinition = "timestamp(0)", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "timestamp(0)", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;
}