package viti.kaf22.itemaccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viti.kaf22.itemaccounting.models.ItemModel;
@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {


    ItemModel findByBase64KeyOfObject(String base64);


}
