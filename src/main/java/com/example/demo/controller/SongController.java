package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.entity.Song;
import com.example.demo.service.SongService;


@Controller
public class SongController {
	@Autowired
    SongService service;
	@PostMapping("/addSong")
    public String addSong(@ModelAttribute Song song) {
		boolean songStatus=service.songExist(song.getName());
		if(songStatus==false) {
			service.addSong(song);
			System.out.println("song Added");
		}
		else {
			System.out.println("song already exists");
		}
    	
    	return "adminHome";
    }
	
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songsList=service.fetchAllSongs();
		model.addAttribute("songs",songsList);
		System.out.println(songsList);
		return "displaySongs";
	}
	
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premiumUser=false;
		if(premiumUser==true) {
		List<Song> songsList=service.fetchAllSongs();
		model.addAttribute("songs",songsList);
		return "displaySongs";
	}
		else {
			return "makePayment";
		}
	}
	
	
	
}
