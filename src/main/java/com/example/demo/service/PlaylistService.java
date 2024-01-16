package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Playlist;

public interface PlaylistService {

	public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylist();

}
