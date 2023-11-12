package be.tsapasmi.factorymanagement.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class) // TODO implement
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    @CreatedBy
//    protected String createdBy;
//
//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    protected LocalDateTime createdDate;
//
//    @LastModifiedBy
//    protected String lastModifiedBy;
//
//    @LastModifiedDate
//    protected LocalDateTime lastModifiedDate;
}
