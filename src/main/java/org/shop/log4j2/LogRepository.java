package org.shop.log4j2;

import org.shop.entities.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<SysLog,Long> {
}
