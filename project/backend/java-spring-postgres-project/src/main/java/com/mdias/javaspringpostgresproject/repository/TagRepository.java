package com.mdias.javaspringpostgresproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

	void deleteByProjectStarred(ProjectStarred project);

}
