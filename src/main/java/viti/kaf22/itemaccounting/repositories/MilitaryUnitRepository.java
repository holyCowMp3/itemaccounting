package viti.kaf22.itemaccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viti.kaf22.itemaccounting.models.MilitaryUnit;

@Repository
public interface MilitaryUnitRepository extends JpaRepository<MilitaryUnit,Long> {
}
