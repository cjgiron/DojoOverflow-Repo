package com.codingdojo.dojooverflow.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Tag;
import com.codingdojo.dojooverflow.repositories.TagRepository;


@Service
public class TagService {
	
	private final TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	public Tag createTag(Tag t) {
		return tagRepository.save(t);
	}
	
	public Tag findTag(Long id) {
		Optional<Tag> optionalTag = tagRepository.findById(id);
		if(optionalTag.isPresent()) {
			return optionalTag.get();
		} else {
			return null;
		}
	}
}
