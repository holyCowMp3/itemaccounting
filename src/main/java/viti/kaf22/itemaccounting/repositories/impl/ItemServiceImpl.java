package viti.kaf22.itemaccounting.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import viti.kaf22.itemaccounting.models.ItemModel;
import viti.kaf22.itemaccounting.repositories.ItemRepository;
import viti.kaf22.itemaccounting.repositories.interfaces.ItemService;
import viti.kaf22.itemaccounting.repositories.interfaces.ItemTypeService;

public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;


    @Override
    public ItemModel getOne(long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public ItemModel getByBase64Key(String key) {
        return itemRepository.findByBase64KeyOfObject(key);
    }
}
