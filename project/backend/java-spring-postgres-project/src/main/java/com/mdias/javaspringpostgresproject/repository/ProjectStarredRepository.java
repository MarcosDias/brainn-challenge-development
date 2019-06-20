package com.mdias.javaspringpostgresproject.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.domain.User;

@Repository
public interface ProjectStarredRepository extends JpaRepository<ProjectStarred, Long> {

	void deleteByUser(User user);

	Page<ProjectStarred> findByUser(User user, Pageable pageRequest);

	Optional<ProjectStarred> findByIdAndUser(Long projectId, User user);

}
