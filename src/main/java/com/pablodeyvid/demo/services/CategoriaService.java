package com.pablodeyvid.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablodeyvid.demo.domain.Categoria;
import com.pablodeyvid.demo.repositories.CategoriaRepository;
import com.pablodeyvid.demo.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	@Transactional
	public Categoria findById(Integer id) {
		Optional<Categoria> opional = repo.findById(id);

		if (opional.orElse(null) != null) {
			Hibernate.initialize(opional.orElse(null).getProdutos());
		}

		return opional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Transactional
	public List<Categoria> getAll() {
		List<Categoria> lista = repo.findAll();
		for (Categoria c : lista) {
			Hibernate.initialize(c.getProdutos());
		}
		return lista;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		try {
			findById(obj.getId());
		} catch (ObjectNotFoundException e) {
			throw e;
		} catch (Exception e) {
		}

		return repo.save(obj);
	}
}
