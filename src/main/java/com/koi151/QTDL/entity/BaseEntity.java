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
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4746498586807182115L;

    @CreatedDate
    @Column(name = "ngayTao", columnDefinition = "TIMESTAMP(0)",
        nullable = false, updatable = false)
    private LocalDateTime ngayTao;

    @Column(name = "ngaySua", columnDefinition = "TIMESTAMP(0)", insertable = false)
    @LastModifiedDate
    private LocalDateTime ngaySua;

    @Column(name = "deleted")
    private boolean daXoa = false;
}