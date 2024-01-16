package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PlaylistController {
	@Autowired
	SongService songService;       //already fetchAllSongs() is contains in Songserive .here we are utilizing it without writing another time.
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList =songService.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "createPlaylist";
	}
	
	@Autowired
    PlaylistService playlistService;
	@PostMapping("addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//update playlist table
		playlistService.addPlaylist(playlist);
		
		//update song table
		List<Song> songList=playlist.getSongs();
		for(Song s:songList) {
			s.getPlaylists().add(playlist);
			//update song object in db
			songService.updateSong(s);
		}
		
		return "adminHome";
	}
	
	@GetMapping("/viewPlaylist")
	public String getMethodName(Model model) {
		List<Playlist> allPlaylist= playlistService.fetchAllPlaylist();
		model.addAttribute("allPlaylist",allPlaylist);
		return "displayPlaylist";
	}
	
	
}
