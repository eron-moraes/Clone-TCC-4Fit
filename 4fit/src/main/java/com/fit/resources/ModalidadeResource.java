package com.fit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fit.domain.Modalidade;
import com.fit.domain.dtos.ModalidadeDTO;
import com.fit.services.ModalidadeService;


@RestController
@RequestMapping(value = "/modalidade")
public class ModalidadeResource {

	@Autowired
	private ModalidadeService modalidadeService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ModalidadeDTO>findById(@PathVariable Integer id) {
		Modalidade obj = modalidadeService.findById(id);
		return ResponseEntity.ok().body(new ModalidadeDTO(obj));
	}

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity findAll() {
		return ResponseEntity.ok().body(modalidadeService.findAll());
	}

	@PostMapping
	public ResponseEntity<ModalidadeDTO> create() {
		return null;
	}
}
