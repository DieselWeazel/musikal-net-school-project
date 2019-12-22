package com.example.demo.service.duplicateservice;

import com.example.demo.model.MusicEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckIfDuplicate {

   public boolean isDuplicate(MusicEntity musicEntity, MusicEntity newMusicEntity) {
       return (musicEntity.getEntityTitle().equals(newMusicEntity.getEntityTitle()));
   }
}
