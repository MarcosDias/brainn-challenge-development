package com.mdias.javaspringpostgresproject.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.domain.User;

@Repository
public interface ProjectStarredRepository extends JpaRepository<ProjectStarred, Long> {

	void deleteByUser(User user);

}
