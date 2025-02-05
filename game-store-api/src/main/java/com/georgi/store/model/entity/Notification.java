package com.georgi.store.model.entity;

import com.georgi.store.model.enums.NotificationLevel;
import com.georgi.store.model.enums.NotificationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity{

    private String message;
    private String receiver;
    @Enumerated(EnumType.STRING)
    private NotificationLevel level;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
}
