package com.test.socialLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.socialLogin.entity.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
