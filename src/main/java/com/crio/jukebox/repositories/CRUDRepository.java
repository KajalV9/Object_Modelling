package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T,ID> {
    public T save(T entity);
    public List<T> findAll();
    public Optional<T> findById(ID id);
    boolean existsById(ID id);
    public void deleteById(ID id);
}
