package com.university.cd.first_project.repository;

import java.util.UUID;

public interface Identifiable {
    UUID getId();

    void setId(UUID id);
}
