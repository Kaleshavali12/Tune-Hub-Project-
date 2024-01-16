package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Playlist;
import com.example.demo.repository.PlaylistRepository;
@Service
public class PlaylistServiceImplementation implements PlaylistService{
    @Autowired
	PlaylistRepository repo;
	@Override
	public void addPlaylist(Playlist playlist) {
	     repo.save(playlist);	
		
	}
	@Override
	public List<Playlist> fetchAllPlaylist() {
		
		return repo.findAll();
	}

}
