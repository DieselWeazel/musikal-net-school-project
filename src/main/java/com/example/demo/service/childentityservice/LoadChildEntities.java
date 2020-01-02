package com.example.demo.service.childentityservice;

import java.util.List;

public interface LoadChildEntities<V, X> {
  List<X> loadAllChildEntities(V v);
}
