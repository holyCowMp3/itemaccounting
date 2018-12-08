package viti.kaf22.itemaccounting.repositories.interfaces;

import viti.kaf22.itemaccounting.models.ItemModel;

public interface ItemService {

    ItemModel getOne(long id);
    ItemModel getByBase64Key(String key);

}
